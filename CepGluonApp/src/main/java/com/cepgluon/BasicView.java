package com.cepgluon;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

public class BasicView extends View {

    private SearchCEP cep;

    public BasicView() {

        cep = new SearchCEP();
        Label label = new Label("Consulta CEP - Gluon");
        Label label2 = new Label("Esse aplicativo tem por intuito realizar a busca\n sobre os dados de um determinado CEP");
        Label label3 = new Label("Para efetuar a busca, informe no campo abaixo\n o valor do CEP que se deseja.");
        TextField campoCEP = new TextField();
        campoCEP.setMaxHeight(400);
        campoCEP.setMaxWidth(200);
        campoCEP.setPromptText("Informe o CEP");
        campoCEP.setAlignment(Pos.CENTER);
        Button button = new Button("Buscar");
        button.setMaxHeight(200);
        button.setMaxWidth(100);
        button.setOnAction((ActionEvent event) -> {
            String valorCEP = campoCEP.getText();
            if (valorCEP.equals("")) {
                Alert alert = createAlert("Campo em branco",
                        "Informe um valor no campo designado para o CEP!");
                alert.show();
            } else {
                String valor = cep.getValues("https://viacep.com.br/ws/" + valorCEP + "/json/");
                try {
                    JSONObject json = new JSONObject(valor);
                    if (json.length() == 1) {
                        Alert alert = createAlert("CEP Invalido",
                                "Por favor informe um CEP válido no campo designado!");
                        alert.show();
                        campoCEP.setText("");
                    } else {
                        setCenter(new ResultView(valor));
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();                    
                }
            }
        });

        VBox controls = new VBox(20.0, label, label2, label3, campoCEP, button);
        controls.setAlignment(Pos.CENTER);

        setCenter(controls);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("Consulta CEP - PDM");
    }

    private Alert createAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        return alert;
    }

}
