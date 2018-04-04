package eu.papenhagen.tolatemobile.view;

import com.gluonhq.charm.glisten.control.DropdownButton;
import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author jens papenhagen
 */
public class DelayPresenter implements Initializable {

    @FXML
    private TextField nameTextfield;
    @FXML
    private TextField resonTextField;
    @FXML
    private DropdownButton delaySelector;
    @FXML
    private CheckBox yellowLetter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
