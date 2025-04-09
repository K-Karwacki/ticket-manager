package dk.easv.ticketmanager.gui.controllers.user.popup;

import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.FieldValidator;
import javafx.fxml.FXML;
import javafx.scene.paint.ImagePattern;

public class UserEditorController extends UserFormController {
    private UserModel userModel;

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
        setFields();
    }
    private void setFields(){
        resetFieldStyles();
        roleComboBox.getItems().setAll(authorizationService.getRoles());
        this.firstNameField.setText(userModel.getName());
        this.lastNameField.setText(userModel.getLastName());
        this.emailField.setText(userModel.getEmail());
        this.phoneField.setText(userModel.getPhoneNumber());
        this.roleComboBox.getSelectionModel().select(userModel.getRole());
        this.profilePictureView.setFill(userModel.imagePatternProperty().get());
    }

    @FXML
    private void handleSubmit() {
        if(!FieldValidator.areAllFieldsFilled(formRoot)){
            System.out.println("fields empty ");
            return;
        }

        ImagePattern imagePattern = (ImagePattern) profilePictureView.getFill();

        userModel.setName(firstNameField.getText());
        userModel.setLastName(lastNameField.getText());
        userModel.setFullName(firstNameField.getText() + " " + lastNameField.getText());
        userModel.setEmail(emailField.getText());
        userModel.setPhoneNumber(phoneField.getText());
        userModel.setRole(roleComboBox.getSelectionModel().getSelectedItem());
        userModel.setPassword(passwordField.getText());
        userModel.setImage(imagePattern);

        userManagementService.updateUser(userModel);

        ViewManager.INSTANCE.hidePopup(FXMLPath.USER_EDITOR_POPUP);
    }
}
