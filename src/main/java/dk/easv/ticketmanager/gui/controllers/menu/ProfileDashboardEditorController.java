package dk.easv.ticketmanager.gui.controllers.menu;

import dk.easv.ticketmanager.bll.services.EmailSenderService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryServiceFactory;
import dk.easv.ticketmanager.bll.services.implementations.AuthenticationServiceImpl;
import dk.easv.ticketmanager.bll.services.implementations.UserManagementServiceImpl;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import dk.easv.ticketmanager.utils.PasswordHasher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.SETTINGS_DASHBOARD;

public class ProfileDashboardEditorController implements Initializable {

    @FXML private Circle profileCircle;
    @FXML private ImageView imgViewEditIcon;
    @FXML private TextField txtFieldUserOldPassword, txtFieldUserNewPassword, txtFieldUserFirstName, txtFieldUserLastName, txtFieldUserEmail, txtFieldUserPhoneNumber;
    @FXML private StackPane stackPaneImageContainer;
    private UserManagementService userManagementService;
    private AuthenticationService authenticationService;

    public void setServices(UserManagementService userManagementService, AuthenticationService authenticationService) {
        this.userManagementService = userManagementService;
        this.authenticationService = authenticationService;
    }

    public TextField getTxtFieldUserFirstName() {
        return txtFieldUserFirstName;
    }

    public void setTxtFieldUserFirstName(TextField txtFieldUserFirstName) {
        this.txtFieldUserFirstName = txtFieldUserFirstName;
    }

    public TextField getTxtFieldUserLastName() {
        return txtFieldUserLastName;
    }

    public void setTxtFieldUserLastName(TextField txtFieldUserLastName) {
        this.txtFieldUserLastName = txtFieldUserLastName;
    }

    public TextField getTxtFieldUserEmail() {
        return txtFieldUserEmail;
    }

    public void setTxtFieldUserEmail(TextField txtFieldUserEmail) {
        this.txtFieldUserEmail = txtFieldUserEmail;
    }

    public TextField getTxtFieldUserPhoneNumber() {
        return txtFieldUserPhoneNumber;
    }

    public void setTxtFieldUserPhoneNumber(TextField txtFieldUserPhoneNumber) {
        this.txtFieldUserPhoneNumber = txtFieldUserPhoneNumber;
    }

    public TextField getTxtFieldUserOldPassword() {
        return txtFieldUserOldPassword;
    }

    public void setTxtFieldUserOldPassword(TextField txtFieldUserOldPassword) {
        this.txtFieldUserOldPassword = txtFieldUserOldPassword;
    }

    public TextField getTxtFieldUserNewPassword() {
        return txtFieldUserNewPassword;
    }

    public void setTxtFieldUserNewPassword(TextField txtFieldUserNewPassword) {
        this.txtFieldUserNewPassword = txtFieldUserNewPassword;
    }

    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profileCircle.setFill(new ImagePattern(image));
        }
    }

    @FXML
    private void updateUser(ActionEvent actionEvent) {
        UserModel currentUser = UserSession.INSTANCE.getLoggedUserModel();
        //Update basic user information
        currentUser.nameProperty().set(txtFieldUserFirstName.getText());
        currentUser.lastNameProperty().set(txtFieldUserLastName.getText());
        currentUser.emailProperty().set(txtFieldUserEmail.getText());
        currentUser.phoneNumberProperty().set(txtFieldUserPhoneNumber.getText());
        currentUser.setImage((ImagePattern) profileCircle.getFill());

        try {
            userManagementService.updateUser(currentUser);
            System.out.println("User updated");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewManager.INSTANCE.switchDashboard(SETTINGS_DASHBOARD, "ProfileDashBoard");
    }

    @FXML
    private void updatePassword(ActionEvent actionEvent) {
        UserModel currentUser = UserSession.INSTANCE.getLoggedUserModel();
        String oldPassword = txtFieldUserOldPassword.getText();
        String newPassword = txtFieldUserNewPassword.getText();
        if (newPassword != null && !newPassword.isEmpty()) {

            try {
                boolean isAuthenticated = authenticationService.authenticateUser(currentUser.getEmail(), oldPassword);
                if (isAuthenticated) {
                    currentUser.setPassword(newPassword);
                    userManagementService.updateUser(currentUser);
                    ViewManager.INSTANCE.switchDashboard(SETTINGS_DASHBOARD, "ProfileDashBoard");
                } else {
                    System.out.println("Wrong current password");
                    return;
                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDetails(UserModel currentUser) {
        txtFieldUserNewPassword.setText("");
        txtFieldUserOldPassword.setText("");
        profileCircle.setFill(currentUser.imagePatternProperty().getValue());
        txtFieldUserFirstName.setText(currentUser.nameProperty().get());
        txtFieldUserLastName.setText(currentUser.lastNameProperty().get());
        txtFieldUserEmail.setText(currentUser.emailProperty().get());
        txtFieldUserPhoneNumber.setText(currentUser.phoneNumberProperty().get());
    }
}
