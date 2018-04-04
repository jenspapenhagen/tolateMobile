package eu.papenhagen.tolatemobile;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.SplashView;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;
import eu.papenhagen.tolatemobile.views.MainView;
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
    public static final String DELAY_VIEW = "DELAY_VIEW";
    public static final String APP_SIDE_MENU = "APP_SIDE_MENU";

    private static final StringProperty HOME_BUTTON_STYLE = new SimpleStringProperty("-fx-text-fill: white;");
    public static final Button HOME_BUTTON = MaterialDesignIcon.HOME.button(e -> MobileApplication.getInstance().switchView(Application.PRIMARY_VIEW));
    public static final Button ANNOUNCEMENT_BUTTON = MaterialDesignIcon.ANNOUNCEMENT.button(e -> MobileApplication.getInstance().showLayer(Application.APP_SIDE_MENU));

    @Override
    public void init() {

        //bind style string to home button
        HOME_BUTTON.styleProperty().bind(HOME_BUTTON_STYLE);


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
        
        addViewFactory(PRIMARY_VIEW, () -> new MainView(PRIMARY_VIEW).getView());
        addLayerFactory(APP_SIDE_MENU, () -> new SidePopupView(new NaviDrawer().getDrawer()));

    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE_GREY.assignTo(scene);

        scene.getStylesheets().add(Application.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(Application.class.getResourceAsStream("/icon.png")));
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop called");
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }
}
