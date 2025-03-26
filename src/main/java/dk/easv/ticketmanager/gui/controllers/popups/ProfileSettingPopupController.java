package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.AuthenticationService;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSettingPopupController implements Initializable {


    @FXML
    private TextField txtFieldUserFirstName;
    @FXML
    private TextField txtFieldUserLastName;
    @FXML
    private TextField txtFieldUserEmail;
    @FXML
    private TextField txtFieldPhoneNumber;
    @FXML
    private TextField txtFieldOldPassword;
    @FXML
    private TextField txtFieldNewPassword;
    @FXML
    private ImageView imageViewSelectedImage;
    @FXML
    public Button btnSaveSettings;


    private User user = UserSession.getInstance().getUser();



    public void chooseImage(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldUserFirstName.setText(user.getFirstName());
        txtFieldUserLastName.setText(user.getLastName());
        txtFieldUserEmail.setText(user.getEmail());
        txtFieldPhoneNumber.setText(user.getPhoneNumber());
    }

    @FXML
    void updateUser(ActionEvent actionEvent) {
        String firstName = txtFieldUserFirstName.getText();
        String lastName = txtFieldUserLastName.getText();
        String email = txtFieldUserEmail.getText();
        String phoneNumber = txtFieldPhoneNumber.getText();

        // Ensure required fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Error", "Please fill in all required fields.");
            return;
        }
        
        // Update details
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    }
}
