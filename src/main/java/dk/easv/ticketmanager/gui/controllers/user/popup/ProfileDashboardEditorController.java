package dk.easv.ticketmanager.gui.controllers.user.popup;

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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.SETTINGS_DASHBOARD;

public class ProfileDashboardEditorController implements Initializable {

    @FXML private Circle profileCircle;
    @FXML private ImageView imageViewSelectedImage;
    @FXML private TextField txtFieldUserOldPassword, txtFieldUserNewPassword, txtFieldUserFirstName, txtFieldUserLastName, txtFieldUserEmail, txtFieldUserPhoneNumber;

    private UserManagementService userManagementService;

    public void setUserManagementService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    public ImageView getImageViewSelectedImage() {
        return imageViewSelectedImage;
    }

    public void setImageViewSelectedImage(ImageView imageViewSelectedImage) {
        this.imageViewSelectedImage = imageViewSelectedImage;
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

    public void chooseImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("Image Files", "*png", "*jpg", "*jpeg", "*bmp")
        );
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageViewSelectedImage.setImage(image);
            profileCircle.setFill(new ImagePattern(image));
            UserSession.INSTANCE.setProfileImage(profileCircle);
        }
    }

    public void updateUser(ActionEvent actionEvent) {
        UserModel currentUser = UserSession.INSTANCE.getLoggedUserModel();
        //Update basic user information
        currentUser.nameProperty().set(txtFieldUserFirstName.getText());
        currentUser.lastNameProperty().set(txtFieldUserLastName.getText());
        currentUser.emailProperty().set(txtFieldUserEmail.getText());
        currentUser.phoneNumberProperty().set(txtFieldUserPhoneNumber.getText());
        //Handle password change
        String oldPassword = txtFieldUserOldPassword.getText();
        String newPassword = txtFieldUserNewPassword.getText();

        if (newPassword != null && !newPassword.isEmpty()) {
            //If user entered a new password, verify the old one first
            RepositoryServiceFactory factory = new RepositoryServiceFactory();
            AuthenticationService authService = new AuthenticationServiceImpl(factory.getRepositoryService());

            try {
                boolean isAuthenticated = authService.authenticateUser(currentUser.getEmail(), oldPassword);
                if (isAuthenticated) {
                    String hashedNewPassword = PasswordHasher.hashPassword(newPassword);
                    currentUser.setPassword(hashedNewPassword);
                } else {
                    System.out.println("Wrong current password");
                    return;
                }
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return;
            }
        }

        try {
            userManagementService.updateUser(currentUser);
            System.out.println("User updated");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ViewManager.INSTANCE.switchDashboard(SETTINGS_DASHBOARD, "ProfileDashBoard");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserModel currentUser = UserSession.INSTANCE.getLoggedUserModel();

        UserSession.INSTANCE.setProfileImage(profileCircle);
        txtFieldUserFirstName.setText(currentUser.nameProperty().get());
        txtFieldUserLastName.setText(currentUser.nameProperty().get());
        txtFieldUserEmail.setText(currentUser.emailProperty().get());
        txtFieldUserPhoneNumber.setText(currentUser.phoneNumberProperty().get());
    }
}
