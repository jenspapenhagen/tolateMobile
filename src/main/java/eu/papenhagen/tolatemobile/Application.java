package eu.papenhagen.tolatemobile;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Application extends MobileApplication {

    public static final String RESTLIST_VIEW = HOME_VIEW;
    public static final String MENU_LAYER = "SideMenu";

    @Override
    public void init() {
        addViewFactory(RESTLIST_VIEW, () -> new RestListView(RESTLIST_VIEW));

        NavigationDrawer navigationDrawer = new NavigationDrawer();
        NavigationDrawer.Item listItem = new NavigationDrawer.Item("List Viewer", MaterialDesignIcon.VIEW_LIST.graphic());
        navigationDrawer.getItems().addAll(listItem);
        navigationDrawer.selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            hideLayer(MENU_LAYER);
            if (newItem.equals(listItem)) {
                switchView(RESTLIST_VIEW);
            }
        });

        addLayerFactory(MENU_LAYER, () -> new SidePopupView(navigationDrawer));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE_GREY.assignTo(scene);

        scene.getStylesheets().add(Application.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(Application.class.getResourceAsStream("/icon.png")));
    }
}
