package com.gluoncep.views;

import com.gluoncep.GluonCep;
import com.gluoncep.infra.CEPRepo;
import com.gluoncep.infra.CepServiceImpl;
import com.gluoncep.service.CepService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;

public class PrimaryPresenter {

    @FXML
    private View primary;

    @FXML
    private TextField textFieldCep;
    
    private CepService cepService;

    public void initialize() {
        this.cepService = new CepServiceImpl();
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Consulta CEP - Gluon");
            }
        });
    }

    @FXML
    void buttonClick() {
        String url = "https://viacep.com.br/ws/" + textFieldCep.getText() + "/json/";
        String searchValues = cepService.searchValues(url);
        JSONObject object;
        try {
            object = new JSONObject(searchValues);
            CEPRepo.setCep(object);
            if (object.length() > 1) {
                MobileApplication.getInstance().switchView(GluonCep.SECONDARY_VIEW);
            } else {
                construirAlerta();
            }
        } catch (JSONException ex) {
            construirAlerta();
        }
    }

    private void construirAlerta() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CEP INVALIDO");
        alert.setContentText("Informe um cep valido, tente novamente!");
        alert.show();
        textFieldCep.setText("");
    }

}
