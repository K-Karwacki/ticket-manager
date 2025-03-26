package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.AuthenticationService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.dashboards.ProfileDashboardController;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.SETTINGS_DASHBOARD;

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
    private AuthenticationService authService = AuthenticationService.getInstance();
    private UserDataModel userDataModel = new UserDataModel();

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
        String oldPassword = txtFieldOldPassword.getText();
        String newPassword = txtFieldNewPassword.getText();
        // Ensure required fields are filled
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Error", "Please fill in all required fields.");
            return;
        }
        // Handle password update if provided
        if (!newPassword.isEmpty()) {
            if (oldPassword.isEmpty()) {
                showAlert("Error", "Please enter your current password to change it.");
                return;
            }
            // Fetch the latest user data from database to ensure the latest password is used.
            User latestUser = userDataModel.getUserByEmail(user.getEmail());

            if (latestUser == null) {
                showAlert("Error", "User session is invalid. Please log in again.");
                return;
            }
            // Verify password using latest user data
            if (!authService.verifyPassword(oldPassword, latestUser.getPassword())) {
                showAlert("Error", "Incorrect current password.");
                return;
            }
            // Update the new password
            user.setPassword(newPassword);
        }
        // Update basic details
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        // Save updates to the databse
        userDataModel.editUser(user);
        // Refresh UserSession with updated user
        UserSession.getInstance().setUser(userDataModel.getUserByEmail(user.getEmail()));

        showAlert("Success", "Your profile has been updated!");
        // Trigger a UI update on the dashboard
        notifyDashboardUpdate();
        Stage stage = (Stage) txtFieldUserFirstName.getScene().getWindow();
        stage.close();
    }

    private void notifyDashboardUpdate() {
        Pair<Parent, ProfileDashboardController> p = FXMLManager.getInstance().getFXML(SETTINGS_DASHBOARD);
        p.getValue().refreshUserProfile();
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
