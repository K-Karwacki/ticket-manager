package dk.easv.ticketmanager.gui.controllers.main;


import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.FORGOTTEN_PASSWORD_VIEW;


public class LoginWindowController
{
    private final ViewManager viewManager;
    private final UserSession userSession;
    private AuthenticationService authenticationService;
    private boolean isPasswordVisible = false;
    private final Image VISIBLE_ICON = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icons/visible-icon.png")));
    private final Image INVISIBLE_ICON = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/icons/invisible-icon.png")));


  @FXML private CheckBox showPasswordCheckBox;
  @FXML private Label errorLabel;
  @FXML private TextField textFieldUsername;
  @FXML private PasswordField passwordFieldPassword;
  @FXML private TextField textFieldPassword;
  @FXML private ImageView imgEyeIcon;


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
      viewManager.showStage(FORGOTTEN_PASSWORD_VIEW, "Send Email", true);
  }

  @FXML
  private void togglePasswordVisibility() {
    if (!isPasswordVisible) {
      textFieldPassword.setText(passwordFieldPassword.getText());
      textFieldPassword.setVisible(true);
      textFieldPassword.setManaged(true);
      passwordFieldPassword.setVisible(false);
      passwordFieldPassword.setManaged(false);
      imgEyeIcon.setImage(INVISIBLE_ICON);
      isPasswordVisible = true;
    } else {
      passwordFieldPassword.setText(textFieldPassword.getText());
      passwordFieldPassword.setVisible(true);
      passwordFieldPassword.setManaged(true);
      textFieldPassword.setVisible(false);
      textFieldPassword.setManaged(false);
      imgEyeIcon.setImage(VISIBLE_ICON);
      isPasswordVisible = false;
    }
  }

    public void setAuthenticationService(AuthenticationService authenticationService, AuthenticationService authenticationService1, UserManagementService userManagementService) {
      this.authenticationService = authenticationService;
    }
}
