package dk.easv.ticketmanager.gui.controllers.menu;

import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileDashboardController implements Initializable
{
    @FXML private Circle profileCircle;
    @FXML private ImageView profileImage;
    @FXML private Label txtFirstName, txtLastName, txtRole, txtEmail, txtPhoneNumber;

    UserSession userSession = UserSession.getInstance();
    UserModel currentUser = userSession.getLoggedUserModel();

    public ImageView getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }

    public Label getTxtFirstName() {
        return txtFirstName;
    }

    public void setTxtFirstName(Label txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public Label getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(Label txtLastName) {
        this.txtLastName = txtLastName;
    }

    public Label getTxtRole() {
        return txtRole;
    }

    public void setTxtRole(Label txtRole) {
        this.txtRole = txtRole;
    }

    public Label getTxtEmail() {
        return txtEmail;
    }

    public Label getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public void setTxtPhoneNumber(Label txtPhoneNumber) {
        this.txtPhoneNumber = txtPhoneNumber;
    }

    public void setTxtEmail(Label txtEmail) {
        this.txtEmail = txtEmail;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void editProfile(ActionEvent actionEvent) {
    }
}
