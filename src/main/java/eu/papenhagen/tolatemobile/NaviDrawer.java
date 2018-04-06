/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.papenhagen.tolatemobile;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.ViewStackPolicy;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.control.NavigationDrawer.ViewItem;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.scene.Node;

import static eu.papenhagen.tolatemobile.Application.APP_SIDE_MENU;
import static eu.papenhagen.tolatemobile.Application.PRIMARY_VIEW;

/**
 *
 * @author jay
 */
public class NaviDrawer {

    private final NavigationDrawer drawer;

    public NaviDrawer() {
        this.drawer = new NavigationDrawer();
        NavigationDrawer.Header header = new NavigationDrawer.Header("Info: ", "Immer auf dem Laufenden.");
        drawer.setHeader(header);

        final Item primaryItem = new ViewItem("Liste", MaterialDesignIcon.HOME.graphic(), Application.PRIMARY_VIEW, ViewStackPolicy.SKIP);
        final Item secondaryItem = new ViewItem("Edit", MaterialDesignIcon.HOME.graphic(), Application.DELAY_VIEW, ViewStackPolicy.SKIP);
        
        drawer.getItems().addAll(primaryItem,secondaryItem);

        if (Platform.isDesktop()) {
            final Item quitItem = new Item("Quit", MaterialDesignIcon.EXIT_TO_APP.graphic());
            quitItem.selectedProperty().addListener((obs, ov, nv) -> {
                if (nv) {
                    Services.get(LifecycleService.class).ifPresent(LifecycleService::shutdown);
                }
            });
            drawer.getItems().add(quitItem);
        }

        drawer.addEventHandler(NavigationDrawer.ITEM_SELECTED, e -> MobileApplication.getInstance().hideLayer(APP_SIDE_MENU));

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
