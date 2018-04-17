package com.tolatemobile.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.DropdownButton;
import com.gluonhq.charm.glisten.control.TextField;
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
import javafx.geometry.Insets;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private Label nameLabel;

    @FXML
    private Label resoneLable;

    @FXML
    private Label delayLable;

    @FXML
    private Label yelloeLetterLable;

    @FXML
    private CheckBox yellowLetterCheckBox;

    @FXML
    private Button addButton;

    @FXML
    private StackPane mainPane;

    @FXML
    private VBox vBox;

    @FXML
    private HBox hBox;

    private RestProvider rest;

    private Delay delayItem;

    private final int MAXMINUTES = 270;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final Pattern pattern = Pattern.compile("-?\\d+");

    private final LocalDateTime today = LocalDateTime.now();

    public void initialize() {
        rest = new RestProvider();

        mainPane.setPadding(new Insets(24));

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

        //change layout for responvice design bigger than 600px
        secondary.widthProperty().greaterThan(600).addListener(
                (obs, oldValue, newValue) -> {
                    if (!newValue) {
                        changeToSmallLayout();
                    } else {
                        changeToLargeLayout();
                    }
                });
        
        

    }

    public void changeToSmallLayout() {
        hBox.getChildren().clear();
        vBox.getChildren().clear();
        vBox.getChildren().addAll(nameLabel, nameTextfield, resoneLable,
                resonTextField, delayLable, delaySelector,
                yelloeLetterLable, yellowLetterCheckBox, addButton);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(vBox);
    }

    public void changeToLargeLayout() {
        hBox.getChildren().clear();
        vBox.getChildren().clear();

        //line 0
        HBox tempHBox = new HBox();
        tempHBox.setSpacing(5.0);
        tempHBox.getChildren().addAll(nameLabel, nameTextfield);

        //line 1
        HBox tempHBox1 = new HBox();
        tempHBox1.setSpacing(10.0);
        tempHBox1.getChildren().addAll(resoneLable, resonTextField);

        //line 2
        HBox tempHBox2 = new HBox();
        tempHBox2.setSpacing(5.0);
        tempHBox2.getChildren().addAll(delayLable, delaySelector);

        //line 3
        HBox tempHBox3 = new HBox();
        tempHBox3.setSpacing(5.0);
        tempHBox3.getChildren().addAll(yelloeLetterLable, yellowLetterCheckBox);

        //put lines under eatch other
        VBox tempVBox = new VBox();
        tempVBox.setSpacing(5.0);
        tempVBox.getChildren().addAll(tempHBox, tempHBox1, tempHBox2, tempHBox3, addButton);

        hBox.getChildren().add(tempVBox);
        
        HBox.setHgrow(nameTextfield, Priority.ALWAYS);
        HBox.setHgrow(resonTextField, Priority.ALWAYS);
        HBox.setHgrow(delaySelector, Priority.ALWAYS);
        HBox.setHgrow(tempVBox, Priority.ALWAYS);
        HBox.setHgrow(hBox, Priority.ALWAYS);

        mainPane.getChildren().clear();
        mainPane.getChildren().add(hBox);
    }

    @FXML
    private void add(ActionEvent event) {
        if (!nameTextfield.getText().isEmpty() && !resonTextField.getText().isEmpty()) {
            delayItem = new Delay(
                    (rest.lastDelayId() + 1),
                    today.format(formatter),
                    nameTextfield.getText(),
                    getMinutesFormDropdownButton(delaySelector.getSelectedItem()),
                    resonTextField.getText(),
                    yellowLetterCheckBox.isSelected());

            rest.addDelay(delayItem);

            nameTextfield.setText("");
            resonTextField.setText("");

            //switch back to homeview
            MobileApplication.getInstance().switchView(Application.HOME_VIEW);
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
