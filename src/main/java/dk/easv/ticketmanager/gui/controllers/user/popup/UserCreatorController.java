package dk.easv.ticketmanager.gui.controllers.user.popup;

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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

public class UserCreatorController {
  private AuthorizationService authorizationService;
  private UserManagementService userManagementService;
  private AuthenticationService authenticationService;
  private UserModel userModel = new UserModel();

  @FXML private VBox formRoot;
  @FXML private ComboBox<Role> roleComboBox;
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneField;
  @FXML private TextField emailField;
  @FXML private PasswordField passwordField;
  @FXML private Button browseButton, submitButton;
  @FXML private ImageView profilePictureView;
  @FXML private Label resultLabel;


  public void setServices(UserManagementService userManagementService, AuthorizationService authorizationService){
    this.userManagementService = userManagementService;
    this.authorizationService = authorizationService;

    roleComboBox.getItems().setAll(this.authorizationService.getRoles());
    System.out.println(this.authorizationService.getRoles());
  }

  @FXML
  private void initialize() {
    browseButton.setOnAction(e -> handleBrowseImage());
    submitButton.setOnAction(e -> handleSubmit());
  }

  private void handleBrowseImage() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Profile Picture");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
      Image image = new Image(selectedFile.toURI().toString());
      profilePictureView.setImage(image);
    }
  }


  private void handleSubmit() {
    if(!FieldValidator.areAllFieldsFilled(formRoot)){
      System.out.println("fields empty ");
      return;
    }

    if(profilePictureView == null){
      throw new RuntimeException("Load image");
    }
//
//    userModel.setName(firstNameField.getText().trim());
//    userModel.setLastName(lastNameField.getText().trim());
//    userModel.phoneNumberProperty().set(phoneField.getText().trim());
//    userModel.setEmail(emailField.getText().trim());
//    userModel.roleProperty().set(roleComboBox.getValue());
//    userModel.setPassword(passwordField.getText().trim());
//
    if(userManagementService.registerNewUser(firstNameField.getText(), lastNameField.getText(), roleComboBox.getValue().getId(), emailField.getText(), phoneField.getText(), passwordField.getText()) != null){
      System.out.println("user created :)");
      ViewManager.INSTANCE.hidePopup(FXMLPath.USER_CREATOR_POPUP);
    }


  }

  private void resetResultLabelColor() {
    resultLabel.setStyle("-fx-text-fill: black;");
  }

  private void showError(Control field) {
    if (field instanceof TextField) {
      ((TextField) field).setStyle("-fx-border-color: red;");
    } else if (field instanceof PasswordField) {
      ((PasswordField) field).setStyle("-fx-border-color: red;");
    } else if (field instanceof ComboBox) {
      ((ComboBox<?>) field).setStyle("-fx-border-color: red;");
    }
  }

  private void resetFieldStyles() {
    firstNameField.setStyle("-fx-border-color: transparent;");
    lastNameField.setStyle("-fx-border-color: transparent;");
    phoneField.setStyle("-fx-border-color: transparent;");
    emailField.setStyle("-fx-border-color: transparent;");
    passwordField.setStyle("-fx-border-color: transparent;");
    roleComboBox.setStyle("-fx-border-color: transparent;");
  }
}
