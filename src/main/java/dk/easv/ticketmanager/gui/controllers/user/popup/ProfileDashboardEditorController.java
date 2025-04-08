package dk.easv.ticketmanager.gui.controllers.user.popup;

import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileDashboardEditorController implements Initializable {

    @FXML private Circle profileCircle;
    @FXML private ImageView imageViewSelectedImage;
    @FXML private TextField txtFieldUserOldPassword, txtFieldUserNewPassword, txtFieldUserFirstName, txtFieldUserLastName, txtFieldUserEmail, txtFieldUserPhoneNumber;

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
    }

    public void updateUser(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserModel currentUser = UserSession.getInstance().getLoggedUserModel();

        UserSession.getInstance().setProfileImage(profileCircle);
        txtFieldUserFirstName.setText(currentUser.nameProperty().get());
        txtFieldUserLastName.setText(currentUser.nameProperty().get());
        txtFieldUserEmail.setText(currentUser.emailProperty().get());
        txtFieldUserPhoneNumber.setText(currentUser.phoneNumberProperty().get());
    }
}
