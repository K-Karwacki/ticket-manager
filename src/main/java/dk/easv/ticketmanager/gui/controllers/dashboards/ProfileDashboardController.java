package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.ProfileSettingPopupController;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.PROFILE_SETTINGS_POPUP;


public class ProfileDashboardController implements Initializable
{
    @FXML
    private TextField txtFieldCurrentPassword;
    @FXML
    private TextField txtFieldNewPassword;
    @FXML
    private Button saveNewPasswordButton;
    @FXML
    private Circle profileCircle;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label profileFullName;
    @FXML
    private Label profileEmail;
    @FXML
    private Label profilePhoneNumber;
    @FXML
    private Button editProfileButton;
    private final FXMLManager fxmlManager= FXMLManager.getInstance();

   User user = UserSession.getInstance().getUser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (user.getImagePath() != null && !user.getImagePath().isEmpty()) {
            try {
                Image img = new Image("file:" + user.getImagePath(), false);
                ImagePattern pattern = new ImagePattern(img);
                profileCircle.setFill(pattern); // `profileImage` should be a `Circle`
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }
        profileFullName.setText(user.getFirst_name() + " " + user.getLast_name());
        profileEmail.setText(user.getEmail());
        profilePhoneNumber.setText(user.getPhoneNumber());
    }

    public void editUserInformation(ActionEvent actionEvent) {
        Pair<Parent, ProfileSettingPopupController> p = fxmlManager.loadFXML(PROFILE_SETTINGS_POPUP);
        Stage stage = new  Stage();
        stage.setTitle("Edit Profile");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }

    public void updatePassword(ActionEvent actionEvent) {
    }

    public void refreshUserProfile() {
        User updatedUser = UserSession.getInstance().getUser();
        profileFullName.setText(updatedUser.getFirstName() + " " + updatedUser.getLastName());
        profileEmail.setText(updatedUser.getEmail());
        profilePhoneNumber.setText(updatedUser.getPhoneNumber());
        // Reload profile image if changed
        if (updatedUser.getImagePath() != null && !updatedUser.getImagePath().isEmpty()) {
            try {
                Image img = new Image("file:" + updatedUser.getImagePath(), false);
                ImagePattern pattern = new ImagePattern(img);
                profileCircle.setFill(pattern);
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }
    }

}
