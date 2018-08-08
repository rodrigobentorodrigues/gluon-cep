package com.cepgluon;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rodrigo
 */
public class ResultView extends View {

    public ResultView(String valor) {
        try {
            JSONObject object = new JSONObject(valor);

            Label label1 = new Label("Resultado da consulta pelo CEP: " + object.getString("cep"));
            Label label2 = new Label("Localidade: " + object.getString("localidade"));
            Label label3 = new Label("UF: " + object.getString("uf"));

            String log = object.getString("logradouro");
            Label label4, label5, label6;
            if (log.equals("")) {
                label4 = new Label("Logradouro inexistente para esse CEP");
            } else {
                label4 = new Label("Logradouro: " + log);
            }
            String comp = object.getString("complemento");
            if (comp.equals("")) {
                label5 = new Label("Complemento inexistente para esse CEP");
            } else {
                label5 = new Label("Complemento: " + comp);
            }
            String bairro = object.getString("bairro");
            if (comp.equals("")) {
                label6 = new Label("Bairro inexistente para esse CEP");
            } else {
                label6 = new Label("Bairro: " + bairro);
            }

            Button button = new Button("Voltar");
            button.setMaxHeight(200);
            button.setMaxWidth(100);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setCenter(new BasicView());
                }
            });

            VBox controls = new VBox(25, label1, label2,
                    label3, label4, label5, label6, button);
            controls.setAlignment(Pos.CENTER);

            setCenter(controls);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setTitleText("Consulta CEP - PDM");
    }

}
