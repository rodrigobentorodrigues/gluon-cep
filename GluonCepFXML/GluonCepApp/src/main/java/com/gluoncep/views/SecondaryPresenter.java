package com.gluoncep.views;

import com.gluoncep.infra.CEPRepo;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondaryPresenter {

    @FXML
    private View secondary;
    @FXML
    private Label localidade;
    @FXML
    private Label logradouro;
    @FXML
    private Label uf;
    @FXML
    private Label complemento;
    @FXML
    private Label bairro;

    public void initialize() {
        try {
            JSONObject cep = CEPRepo.getCep();
            localidade.setText(cep.getString("localidade"));
            uf.setText(cep.getString("uf"));
            String log = cep.getString("logradouro");
            if (log.equals("")) {
                logradouro.setText("Logradouro inexistente para esse CEP");
            } else {
                logradouro.setText("Logradouro: " + log);
            }
            String comp = cep.getString("complemento");
            if (comp.equals("")) {
                complemento.setText("Complemento inexistente para esse CEP");
            } else {
                complemento.setText("Complemento: " + comp);
            }
            String b = cep.getString("bairro");
            if (b.equals("")) {
                bairro.setText("Bairro inexistente para esse CEP");
            } else {
                bairro.setText("Bairro: " + bairro);
            }
            secondary.setShowTransitionFactory(BounceInRightTransition::new);
            secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue) {
                    AppBar appBar = MobileApplication.getInstance().getAppBar();
                    appBar.setTitleText("Resultado");
                    appBar.getActionItems().add(MaterialDesignIcon.HOME.button(e
                            -> MobileApplication.getInstance().switchToPreviousView()));
                }
            });
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    void backPage(){
        MobileApplication.getInstance().switchToPreviousView();
    }
    
}
