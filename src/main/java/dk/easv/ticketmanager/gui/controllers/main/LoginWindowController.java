package dk.easv.ticketmanager.gui.controllers.main;


import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.components.MenuComponentController;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.IOException;


public class LoginWindowController
{
    private final ViewManager viewManager;
    private final UserSession userSession;
    private AuthenticationService authenticationService;


  @FXML private CheckBox showPasswordCheckBox;
  @FXML private Label errorLabel;
  @FXML private TextField textFieldUsername;
  @FXML private PasswordField passwordFieldPassword;
  @FXML private TextField textFieldPassword;


  public LoginWindowController(){
    viewManager = ViewManager.INSTANCE;
    userSession = UserSession.getInstance();

  }

  public void setServices(
      AuthenticationService authenticationService)
  {
    this.authenticationService = authenticationService;
  }

  @FXML private void initialize(){
    passwordFieldPassword.setOnKeyPressed(event->{
      if(event.getCode() == KeyCode.ENTER){
        onClickLogin();
      }
    });

    textFieldPassword.setManaged(false);

    showPasswordCheckBox.setOnAction(actionEvent -> togglePasswordVisibility());

    // Sync values between PasswordField and TextField
    passwordFieldPassword.textProperty().bindBidirectional(textFieldPassword.textProperty());
  }
  @FXML
    private void onClickLogin() {
        try {
            String inputUsername = textFieldUsername.getText().trim();
            String inputPassword = textFieldPassword.getText();

            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                blankInputMessage();
                return;
            }

            if (!authenticationService.authenticateUser(inputUsername, inputPassword)) {
              errorLabel.setText("Invalid email or password");
              return;
            }
            goToMainPage();
        } catch (Exception e) {
            errorLabel.setText("An error occurred during login");
            e.printStackTrace();
        }
    }
  private void userNotFoundMessage(){
      errorLabel.setText("User not found");
  }
  private void wrongPasswordMessage(){
      errorLabel.setText("Wrong password");
  }
  private void blankInputMessage(){
      errorLabel.setText("Please enter an email and password");
  }

  private void goToMainPage(){
      viewManager.showStage(FXMLPath.MAIN, "Main stage", true);
//      MenuComponentController menuComponentController = (MenuComponentController) FXMLManager.INSTANCE.getFXML(FXMLPath.MENU_COMPONENT).getValue();
//      menuComponentController.initialize();
  }

  @FXML
  private void onClickForgotPassword() {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Forgot Password");
      alert.setHeaderText(null);
      alert.setContentText("Please contact on of our administrators");
      alert.showAndWait();
  }

  private void togglePasswordVisibility() {
    if (showPasswordCheckBox.isSelected()) {
      textFieldPassword.setText(passwordFieldPassword.getText());
      textFieldPassword.setVisible(true);
      textFieldPassword.setManaged(true);
      passwordFieldPassword.setVisible(false);
      passwordFieldPassword.setManaged(false);
    } else {
      passwordFieldPassword.setText(textFieldPassword.getText());
      passwordFieldPassword.setVisible(true);
      passwordFieldPassword.setManaged(true);
      textFieldPassword.setVisible(false);
      textFieldPassword.setManaged(false);
    }
  }

    public void setAuthenticationService(AuthenticationService authenticationService, AuthenticationService authenticationService1, UserManagementService userManagementService) {
      this.authenticationService = authenticationService;
    }
}
