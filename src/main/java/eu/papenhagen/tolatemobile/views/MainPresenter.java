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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class MainPresenter implements Initializable {

    @FXML
    private View primary;
    
    @FXML
    private ListView<Delay> DelayListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        primary.setShowTransitionFactory(BounceInLeftTransition::new);
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(Application.ANNOUNCEMENT_BUTTON);
                appBar.getActionItems().add(Application.HOME_BUTTON);
                appBar.setTitleText("Hauptmenü");
            }
        });
        
        
        
        RestProvider rest = new RestProvider();
        
        ObservableList<Delay>lateness = FXCollections.observableList(rest.getList());
        System.out.println("size of ObservableList<Latenes> " + lateness.size());

        // create a JavaFX ListView and populate it with the retrieved list
        DelayListView.setItems(lateness);
        DelayListView.setCellFactory(lv -> {
            return new ListCell<Delay>() {
                @Override
                protected void updateItem(Delay tolate, boolean empty) {
                    super.updateItem(tolate, empty);

                    if (!empty) {
                        setText(tolate.getName() + " - " + tolate.getUrsache());
                    } else {
                        setText(null);
                    }
                }
            };
        });
    }    
    
}
