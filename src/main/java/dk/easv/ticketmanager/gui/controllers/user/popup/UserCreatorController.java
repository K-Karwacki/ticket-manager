package dk.easv.ticketmanager.gui.controllers.user.popup;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.FieldValidator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

public class UserCreatorController extends UserFormController {

  @FXML
  private void initialize() {
    roleComboBox.getItems().setAll(authorizationService.getRoles());
    resetFieldStyles();
    setDefaultImage();
  }

  @FXML
  private void handleSubmit() {
    if(!FieldValidator.areAllFieldsFilled(formRoot)){
      System.out.println("fields empty ");
      return;
    }

    ImagePattern imagePattern = (ImagePattern) profilePictureView.getFill();

    if(!authorizationService.canAddUserWithRole(roleComboBox.getValue())){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setContentText("I'm sorry, You don't have permission to add a user with this role.");
      alert.show();
      return;
    }

    if(userManagementService.registerNewUser(firstNameField.getText(), lastNameField.getText(), roleComboBox.getValue().getId(), emailField.getText(), phoneField.getText(), passwordField.getText(), imagePattern.getImage()) != null){
      System.out.println("user created :)");
      ViewManager.INSTANCE.hidePopup(FXMLPath.USER_CREATOR_POPUP);
      resetFieldStyles();
      resetFields();
      setDefaultImage();
    }


  }

}
