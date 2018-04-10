package com.tolatemobile;

import com.tolatemobile.views.PrimaryView;
import com.tolatemobile.views.SecondaryView;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.SplashView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;
import com.tolatemobile.views.CoverplanView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Application extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "Verspätung hinzufügen";
    public static final String COVERPLAN_VIEW = "Verttretungsplan";
    public static final String MENU_LAYER = "Menu";

    private static final StringProperty HOME_BUTTON_STYLE = new SimpleStringProperty("-fx-text-fill: white;");
    public static final Button HOME_BUTTON = MaterialDesignIcon.HOME.button(e -> MobileApplication.getInstance().switchView(Application.PRIMARY_VIEW));
    public static final Button ANNOUNCEMENT_BUTTON = MaterialDesignIcon.ANNOUNCEMENT.button(e -> MobileApplication.getInstance().showLayer(Application.MENU_LAYER));

    @Override
    public void init() {
        //bind style string to home button
        HOME_BUTTON.styleProperty().bind(HOME_BUTTON_STYLE);

        addViewFactory(PRIMARY_VIEW, () -> (View) new PrimaryView().getView());
        addViewFactory(SECONDARY_VIEW, () -> (View) new SecondaryView().getView());
        addViewFactory(COVERPLAN_VIEW, () -> (View) new CoverplanView().getView());

        addViewFactory(MobileApplication.SPLASH_VIEW, () -> {

            ProgressIndicator loading = new ProgressIndicator();
            loading.setProgress(-1);

            Image image = new Image(Application.class.getResourceAsStream("/tolate.jpg"));
            ImageView imageView = new ImageView();
            imageView.setImage(image);

            VBox vb = new VBox();
            vb.getChildren().addAll(imageView, loading);
            vb.setAlignment(Pos.CENTER);

            SplashView splashView = new SplashView(HOME_VIEW, vb, Duration.seconds(2));
            splashView.setBottom(new Label("Test123"));
            return splashView;
        });
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(new DrawerManager().getDrawer()));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(Application.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(Application.class.getResourceAsStream("/icon.png")));
    }
}
