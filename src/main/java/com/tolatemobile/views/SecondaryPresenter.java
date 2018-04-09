package com.tolatemobile.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.DropdownButton;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.tolatemobile.Application;
import com.tolatemobile.enitiy.Delay;
import com.tolatemobile.rest.RestProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class SecondaryPresenter {

    @FXML
    private View secondary;
    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField resonTextField;
    @FXML
    private DropdownButton delaySelector;
    @FXML
    private CheckBox yellowLetter;
    @FXML
    private Button addButton;

    private RestProvider rest;

    private Delay delayItem;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDateTime today = LocalDateTime.now();

    public void initialize() {
        rest = new RestProvider();

        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        secondary.getLayers().add(new FloatingActionButton(MaterialDesignIcon.INFO.text,
                e -> System.out.println("Info")).getLayer());

        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Application.MENU_LAYER)));
                appBar.setTitleText("Add Delay");
//                appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e
//                        -> System.out.println("Favorite")));
            }
        });

        //binding the addbutton to the input of name/resone field
        addButton.disableProperty().bind(
                Bindings.createBooleanBinding(()
                        -> nameTextfield.getText().trim().isEmpty(), nameTextfield.textProperty()
                ).or(
                        Bindings.createBooleanBinding(()
                                -> resonTextField.getText().trim().isEmpty(), resonTextField.textProperty()
                        )
                ));

    }

    @FXML
    private void add(ActionEvent event) {
        if (!nameTextfield.getText().isEmpty() && !resonTextField.getText().isEmpty()) {
            int lastId = rest.lastId();

            //Delay(int id, String date, String name, int delaytime, String ursache, boolean entschuldigt) 
            delayItem = new Delay(
                    lastId,
                    today.format(formatter),
                    nameTextfield.getText(),
                    Integer.parseInt(delaySelector.getSelectedItem().getText()),
                    resonTextField.getText(),
                    yellowLetter.isSelected()
            );

            rest.addDelay(delayItem);

            nameTextfield.setText("");
            resonTextField.setText("");

            MobileApplication.getInstance().switchToPreviousView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Information");
            alert.setHeaderText(null);
            alert.setContentText("Both a title and description are required for this example.");
            alert.showAndWait();
        }
    }
}
