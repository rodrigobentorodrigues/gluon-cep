package com.gluoncep;

import com.gluoncep.views.PrimaryView;
import com.gluoncep.views.SecondaryView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GluonCep extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Secondary View";
    
    @Override
    public void init() {
        // Adicionando as views ao projeto, para que seja reconhecida em outras classes (para navegação)
        addViewFactory(PRIMARY_VIEW, () -> (View) new PrimaryView().getView());
        addViewFactory(SECONDARY_VIEW, () -> (View) new SecondaryView().getView());
    }

    @Override
    public void postInit(Scene scene) {
        // Setando a view inicial
        Swatch.BLUE.assignTo(scene);
        scene.getStylesheets().add(GluonCep.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(GluonCep.class.getResourceAsStream("/icon.png")));
    }
}
