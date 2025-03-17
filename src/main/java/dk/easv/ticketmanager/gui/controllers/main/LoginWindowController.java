package dk.easv.ticketmanager.gui.controllers.main;


import dk.easv.ticketmanager.bll.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;


public class LoginWindowController
{
  @FXML private Label errorLabel;
  @FXML private TextField usernameField;
  @FXML private TextField passwordField;

  AuthenticationService authenticationService = AuthenticationService.getInstance();

  public void onClickLogin(ActionEvent actionEvent) throws InterruptedException
  {
    if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
      errorLabel.setText("Username and password cannot be empty.");
    }
  }

  @FXML
  private void goToMainPage(ActionEvent event) throws IOException {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticketmanager/fxml/main/coordinator.fxml"));
      Parent root = loader.load();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Main Page");
      stage.setResizable(false);
      stage.setMaximized(false);
      stage.show();

      Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      currentStage.close();
  }
}
