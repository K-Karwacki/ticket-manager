package dk.easv.ticketmanager.gui.controllers.main;


import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.AuthenticationService;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginWindowController
{
    private final String SHOW_ICON = "images/icons/show.png";
    private final String HIDE_ICON = "images/icons/hide.png";
    private String currentIcon = SHOW_ICON;
    private final UserSession userSession = UserSession.getInstance();

  @FXML private Label errorLabel;
  @FXML private TextField textFieldUsername;
  @FXML private TextField textFieldPassword;
  @FXML private Hyperlink forgotPasswordLink;
  @FXML private ImageView imgShowPasswordIndicator;

  AuthenticationService authenticationService = AuthenticationService.getInstance();

    private void userNotFoundMessage(){
        errorLabel.setText("User not found");
    }
    private void wrongPasswordMessage(){
        errorLabel.setText("Wrong password");
    }
    private void blankInputMessage(){
        errorLabel.setText("Please enter an email and password");
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

            User authenticatedUser = authenticationService.authenticateUser(inputUsername, inputPassword);
            if (authenticatedUser != null) {
                userSession.setUser(authenticatedUser);
                goToMainPage();
            } else {
                errorLabel.setText("Invalid username or password");
            }
        } catch (Exception e) {
            errorLabel.setText("An error occurred during login");
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        textFieldUsername.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                textFieldPassword.requestFocus();
            }
        });
        textFieldPassword.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                onClickLogin();
            }
        });
    }

  private void goToMainPage() throws IOException {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticketmanager/fxml/main/main.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Main Page");
      stage.setResizable(false);
      stage.setMaximized(false);
      stage.show();

      Stage currentStage = (Stage) textFieldPassword.getScene().getWindow();
      currentStage.close();
  }


    @FXML
    public void onClickForgotPassword(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Recovery");
        alert.setHeaderText(null);
        alert.setContentText("Please check your email for instructions to reset your password.");
        alert.showAndWait();
    }
    @FXML
    private void toggleShowPassword(){
        if(currentIcon.equals(HIDE_ICON)) {
            currentIcon = SHOW_ICON;
            imgShowPasswordIndicator.setImage(new Image(String.valueOf(Main.class.getResource(SHOW_ICON))));
            textFieldPassword.setAccessibleRole(AccessibleRole.TEXT_FIELD);
        }
        else {
            currentIcon = HIDE_ICON;
            imgShowPasswordIndicator.setImage(new Image(String.valueOf(Main.class.getResource(HIDE_ICON))));
            textFieldPassword.setAccessibleRole(AccessibleRole.PASSWORD_FIELD);
        }
    }
}
