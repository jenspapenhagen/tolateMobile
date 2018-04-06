/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.papenhagen.tolatemobile.views;

import com.gluonhq.charm.glisten.animation.BounceInLeftTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import eu.papenhagen.tolatemobile.Application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import eu.papenhagen.tolatemobile.enitiy.Delay;
import eu.papenhagen.tolatemobile.rest.RestProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;

/**
 * FXML Controller class
 *
 * @author jay
 */
public class PrimaryPresenter {

    @FXML
    private View primary;

    @FXML
    private ListView<Delay> DelayListView;
    
    private RestProvider rest;

    public void initialize() {
        
        rest = new RestProvider();

        ObservableList<Delay> lateness = FXCollections.observableList(rest.getList());
        System.out.println("size of ObservableList<Latenes> " + lateness.size());

        // create a JavaFX ListView and populate it with the retrieved list
        DelayListView.setCellFactory(lv -> {
            return new ListCell<Delay>() {
                @Override
                protected void updateItem(Delay item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        setText(item.getName() + " - " + item.getUrsache());
                    } else {
                        setText(null);
                    }
                }
            };
        });
        DelayListView.setItems(lateness);
        
        primary.setShowTransitionFactory(BounceInLeftTransition::new);
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Hauptmenü");
                appBar.setNavIcon(Application.ANNOUNCEMENT_BUTTON);
                appBar.getActionItems().add(Application.HOME_BUTTON);
                
            }
        });
        
    }

}
