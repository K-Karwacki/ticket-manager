package dk.easv.ticketmanager.gui.controllers.menu;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.SceneManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.user.popup.ProfileDashboardEditorController;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.PROFILE_DASHBOARD_EDITOR;

public class ProfileDashboardController implements Initializable
{
    private UserModel currentUser;
    @FXML private Circle profileCircle;
    @FXML private Label txtFirstName, txtLastName, txtRole, txtEmail, txtPhoneNumber;

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

    public void editProfile(ActionEvent actionEvent) {
        Pair<Parent, ProfileDashboardEditorController> p = FXMLManager.INSTANCE.getFXML(PROFILE_DASHBOARD_EDITOR);
        p.getValue().setDetails(currentUser);
        ViewManager.INSTANCE.switchDashboard(PROFILE_DASHBOARD_EDITOR, "Profile Dashboard Editor");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDetails();
    }

    public void setDetails(){
        currentUser = UserSession.INSTANCE.getLoggedUserModel();
        UserSession.INSTANCE.setProfileImage(profileCircle);
        txtFirstName.textProperty().bind(currentUser.nameProperty());
        txtLastName.textProperty().bind(currentUser.lastNameProperty());
        txtRole.textProperty().bind(currentUser.roleProperty().asString());
        txtPhoneNumber.textProperty().bind(currentUser.phoneNumberProperty());
        txtEmail.textProperty().bind(currentUser.emailProperty());
    }
}
