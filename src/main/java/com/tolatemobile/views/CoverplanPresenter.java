/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tolatemobile.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import org.slf4j.LoggerFactory;

import com.tolatemobile.Application;
import com.tolatemobile.enitiy.CoverPlan;
import com.tolatemobile.rest.RestProvider;

/**
 *
 * @author jay
 */
public class CoverplanPresenter {

    @FXML
    private View coverplan;

    @FXML
    private ListView<CoverPlan> CoverPlanListView;

    private RestProvider rest;

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(CoverplanPresenter.class);

    public void initialize() {

        rest = new RestProvider();

        coverplan.setShowTransitionFactory(BounceInRightTransition::new);
        coverplan.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Vertretungsplan");
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Application.MENU_LAYER)));
            }
        });

        CoverPlanListView.setCellFactory(lv -> {
            return new ListCell<CoverPlan>() {
                @Override
                protected void updateItem(CoverPlan item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        String title = item.getTitle();
                        setText(title + " - " + item.getDescription());
                    } else {
                        setText(null);
                    }
                }
            };
        });

        //fill the List
        ObservableList<CoverPlan> lateness = FXCollections.observableList(rest.getCoverPlan());
        LOG.debug("size of ObservableList<CoverPlan> " + lateness.size());

        CoverPlanListView.setItems(lateness);
    }

}
