package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ProfileDashBoardEditorController {
    @FXML private TextField txtFieldUserOldPassword, txtFieldUserNewPassword,txtFieldUserFirstName, txtFieldUserLastName, txtFieldUserEmail, txtFieldUserPhoneNumber;
    @FXML private Button btnSaveSettings;
    @FXML private ImageView imageViewSelectedImage;

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

    public ImageView getImageViewSelectedImage() {
        return imageViewSelectedImage;
    }

    public void setImageViewSelectedImage(ImageView imageViewSelectedImage) {
        this.imageViewSelectedImage = imageViewSelectedImage;
    }

    public void chooseImage(ActionEvent actionEvent) {
    }

    public void updateUser(ActionEvent actionEvent) {
    }
}
