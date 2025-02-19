package dk.easv.ticketmanager.gui.controllers.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginWindowController
{
  @FXML private Label errorLabel;
  @FXML private TextField usernameField;
  @FXML private TextField passwordField;


  public void onClickLogin(ActionEvent actionEvent) throws InterruptedException
  {
    if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
      errorLabel.setText("Username and password cannot be empty.");
    }
  }
}
