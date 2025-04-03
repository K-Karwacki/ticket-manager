package dk.easv.ticketmanager.gui.controllers.user.popup;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.FieldValidator;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

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


  public void setServices(UserManagementService userManagementService, AuthorizationService authorizationService, AuthenticationService authenticationService){
    this.userManagementService = userManagementService;
    this.authorizationService = authorizationService;
    this.authenticationService = authenticationService;

    roleComboBox.getItems().setAll(authorizationService.getRoles());
    System.out.println(authorizationService.getRoles());
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


  @FXML
  private void onClickSubmit(){

  }

  private void handleSubmit() {
    if(!FieldValidator.areAllFieldsFilled(formRoot)){
      return;
    }

    if(profilePictureView == null){
      throw new RuntimeException("Load image");
    }

    userModel.setFirstName(firstNameField.getText().trim());
    userModel.setLastName(lastNameField.getText().trim());
    userModel.phoneNumberProperty().set(phoneField.getText().trim());
    userModel.setEmail(emailField.getText().trim());
    userModel.roleProperty().set(roleComboBox.getValue());
    userModel.setPassword(passwordField.getText().trim());

    try{
      UserModel savedUser = authenticationService.registerNewUser(
          firstNameField.getText(), lastNameField.getText(), roleComboBox.getValue().getId(), emailField.getText(), phoneField.getText(),
          passwordField.getText());

      if(savedUser != null){
        userManagementService.addUser(savedUser);
      }
    }catch (Exception e ){
      e.printStackTrace();
    }


//    System.out.println(firstName + "" +lastName);
//    resetFieldStyles();
//    resultLabel.setText("");
//    resetResultLabelColor();
//
//    if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || role == null) {
//      resultLabel.setText("All fields need to be used.");
//      resultLabel.setStyle("-fx-text-fill: red;");  // Set error text color to red
//      if (firstName.isEmpty()) showError(firstNameField);
//      if (lastName.isEmpty()) showError(lastNameField);
//      if (email.isEmpty()) showError(emailField);
//      if (phone.isEmpty()) showError(phoneField);
//      if (password.isEmpty()) showError(passwordField);
//      if (role == null) showError(roleComboBox);
//      return;
//    }
//
//    if (!phone.matches("\\d{8,12}")) {
//      showError(phoneField);
//      resultLabel.setText("Phone number must be between 8 and 12 digits.");
//      resultLabel.setStyle("-fx-text-fill: red;");  // Set error text color to red
//      return;
//    }
//
//    try {
//      userModel.setEmail(email);
//      userModel.setFirstName(firstName);
//      userModel.setLastName(lastName);
//      userModel.roleProperty().set(role);
//      userModel.setPassword(password);
//      userModel.setPhoneNumber(phone);
//
//      if(userManagementService.createNewUser(userModel)){
//        System.out.println(userModel.getName());
//        resultLabel.setText("User successfully added.");
//        resultLabel.setStyle("-fx-text-fill: green;");
//        new Thread(() -> {
//          try {
//            Thread.sleep(2000);
//            javafx.application.Platform.runLater(() -> {
//              Stage stage = (Stage) submitButton.getScene().getWindow();
//              stage.close();
//            });
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//        }).start();
//      }
//    } catch (Exception ex) {
//      resultLabel.setText("Error: " + ex.getMessage());
//      resultLabel.setStyle("-fx-text-fill: red;");
//    }
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
