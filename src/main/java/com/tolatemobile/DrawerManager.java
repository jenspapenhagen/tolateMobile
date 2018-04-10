package com.tolatemobile;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import static com.tolatemobile.Application.COVERPLAN_VIEW;
import static com.tolatemobile.Application.MENU_LAYER;
import static com.tolatemobile.Application.PRIMARY_VIEW;
import static com.tolatemobile.Application.SECONDARY_VIEW;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class DrawerManager {

    private final NavigationDrawer drawer;

    public DrawerManager() {
        this.drawer = new NavigationDrawer();

        NavigationDrawer.Header header = new NavigationDrawer.Header("tolateMobile",
                "ITech School Helper",
                new Avatar(21, new Image(DrawerManager.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);

        final Item primaryItem = new ViewItem("Liste der Verspätungen", MaterialDesignIcon.HOME.graphic(), PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item secondaryItem = new ViewItem("Verspätung hinzufügen", MaterialDesignIcon.DASHBOARD.graphic(), SECONDARY_VIEW);
        final Item coverplanItem = new ViewItem("Vertretungsplan ", MaterialDesignIcon.ALARM.graphic(), COVERPLAN_VIEW);
        
        drawer.getItems().addAll(primaryItem, secondaryItem, coverplanItem);

        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }

        drawer.addEventHandler(NavigationDrawer.ITEM_SELECTED,
                e -> MobileApplication.getInstance().hideLayer(MENU_LAYER));

        MobileApplication.getInstance().viewProperty().addListener((obs, oldView, newView) -> updateItem(newView.getName()));
        updateItem(PRIMARY_VIEW);
    }

    private void updateItem(String nameView) {
        for (Node item : drawer.getItems()) {
            if (item instanceof ViewItem && ((ViewItem) item).getViewName().equals(nameView)) {
                drawer.setSelectedItem(item);
                break;
            }
        }
    }

    public NavigationDrawer getDrawer() {
        return drawer;
    }
}
