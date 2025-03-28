package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.ProfileImageService;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCardController implements Initializable {

    @FXML private Circle profileCircle;
    @FXML ImageView profileImage;
    @FXML Label txtUserFirstName, txtUserLastName, txtUserRole, txtUserEmail, txtUserPhoneNumber;
    @FXML Button btnEditUser, btnDeleteUser;

    ProfileImageService profileImageService = new ProfileImageService();
    User currentUser = UserSession.getInstance().getUser();

    public void editUser(ActionEvent actionEvent) {
    }

    public void deleteUser(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileImageService.setProfileImage(profileImage, profileCircle);
        txtUserFirstName.setText(currentUser.getFirstName());
        txtUserLastName.setText(currentUser.getLastName());
        txtUserRole.setText(currentUser.getRole().toString());
        txtUserEmail.setText(currentUser.getEmail());
        txtUserPhoneNumber.setText(currentUser.getPhoneNumber());
    }
}
