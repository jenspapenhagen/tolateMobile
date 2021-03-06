package com.tolatemobile.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import com.tolatemobile.Application;
import com.tolatemobile.enitiy.Delay;
import com.tolatemobile.rest.RestProvider;
import org.slf4j.LoggerFactory;

public class PrimaryPresenter {

    @FXML
    private View primary;

    @FXML
    private ListView<Delay> DelayListView;

    private RestProvider rest;

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(PrimaryPresenter.class);

    public void initialize() {
        rest = new RestProvider();

        primary.setShowTransitionFactory(BounceInRightTransition::new);
        primary.getLayers().add(new FloatingActionButton(MaterialDesignIcon.ADD.text, e
                -> MobileApplication.getInstance().switchView(Application.SECONDARY_VIEW)).getLayer());
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Verspätungen");
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Application.MENU_LAYER)));
                appBar.getActionItems().add(MaterialDesignIcon.ADD.button(e
                        -> MobileApplication.getInstance().switchView(Application.SECONDARY_VIEW)));
            }
        });

        // build a nice cellFactory
        DelayListView.setCellFactory(lv -> {
            return new ListCell<Delay>() {
                @Override
                protected void updateItem(Delay item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        setText(item.getName() + " - " + item.getUrsache() + " - " + item.getDelaytime());
                    } else {
                        setText(null);
                    }
                }
            };
        });
        //fill the List
        ObservableList<Delay> lateness = FXCollections.observableList(rest.getDelayList());
        LOG.debug("size of ObservableList<Latenes> " + lateness.size());

        DelayListView.setItems(lateness);
    }

}
