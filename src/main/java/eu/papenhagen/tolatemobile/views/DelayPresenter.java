package eu.papenhagen.tolatemobile.views;

import com.gluonhq.charm.glisten.animation.BounceInLeftTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.DropdownButton;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.layout.layer.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import eu.papenhagen.tolatemobile.Application;
import eu.papenhagen.tolatemobile.enitiy.Delay;
import eu.papenhagen.tolatemobile.rest.RestProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author jens papenhagen
 */
public class DelayPresenter {

    @FXML
    private View delay;

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField resonTextField;

    @FXML
    private DropdownButton delaySelector;

    @FXML
    private CheckBox yellowLetter;

    @FXML
    private AppBar appBarDelay;

    @FXML
    private Button addButton;

    private RestProvider rest;

    private Delay delayItem;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void initialize() {
        delay.setShowTransitionFactory(BounceInLeftTransition::new);

       
        
        delay.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                if (newValue) {
                    AppBar appBar = MobileApplication.getInstance().getAppBar();
                    appBar.setTitleText("Add the button");
                    //appBar.setNavIcon(Application.ANNOUNCEMENT_BUTTON);
                    appBar.getActionItems().add(Application.HOME_BUTTON);

                }
            }
        });

    }

    /**
     * this Methode get onAction on ADD button pressed
     */
    public void add() {
        if (!nameTextfield.getText().equals("") && !resonTextField.getText().equals("")) {

            rest = new RestProvider();
            int lastId = rest.lastId();

            LocalDateTime now = LocalDateTime.now();

            //Delay(int id, String date, String name, int delaytime, String ursache, boolean entschuldigt) 
            delayItem = new Delay(
                    lastId,
                    now.format(formatter),
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
