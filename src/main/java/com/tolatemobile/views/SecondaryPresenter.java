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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;

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

    private final int MAXMINUTES = 270;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Pattern pattern = Pattern.compile("-?\\d+");

    private final LocalDateTime today = LocalDateTime.now();

    public void initialize() {
        rest = new RestProvider();

        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setTitleText("Hinzufügen von Verspätung");
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(Application.MENU_LAYER)));
            }
        });

        //binding the addbutton to the input of name/resone field
        addButton.disableProperty().bind(
                Bindings.createBooleanBinding(()
                        -> nameTextfield.getText().trim().isEmpty(), nameTextfield.textProperty()
                ).or(Bindings.createBooleanBinding(()
                        -> resonTextField.getText().trim().isEmpty(), resonTextField.textProperty()
                )));

    }

    @FXML
    private void add(ActionEvent event) {
        if (!nameTextfield.getText().isEmpty() && !resonTextField.getText().isEmpty()) {
            int lastId = rest.lastId();

            //parsing the delaytime form "in 5min" to 
            //Delay(int id, String date, String name, int delaytime, String ursache, boolean entschuldigt) 
            delayItem = new Delay(
                    lastId,
                    today.format(formatter),
                    nameTextfield.getText(),
                    getMinutesFormDropdownButton(delaySelector.getSelectedItem()),
                    resonTextField.getText(),
                    yellowLetter.isSelected()
            );

            rest.addDelay(delayItem);

            nameTextfield.setText("");
            resonTextField.setText("");

            MobileApplication.getInstance().switchToPreviousView();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Name und Ursache nicht genannt");
            alert.setHeaderText(null);
            alert.setContentText("Bitte geben Sie Name und Ursache an.");
            alert.showAndWait();
        }
    }

    /**
     * parsing the MenuItems to int of minutes 
     * 
     * @param item the selected MenuItem form the DropdownButton
     * @return int of Minutes
     */
    private int getMinutesFormDropdownButton(MenuItem item) {
        Matcher matcher = pattern.matcher(item.getText());

        if (!matcher.find()) {
            return MAXMINUTES;
        } else {
            int tempInt = Integer.parseInt(matcher.group());
            if (tempInt < 5) {
                return tempInt * 60;
            }
            return tempInt;
        }

    }
}
