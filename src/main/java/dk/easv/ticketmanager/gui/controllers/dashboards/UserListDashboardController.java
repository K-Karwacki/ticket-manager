package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.AuthenticationService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.EditUserPopupController;
import dk.easv.ticketmanager.gui.controllers.popups.EventCreatorPopupController;
import dk.easv.ticketmanager.gui.controllers.popups.UserFormPopupController;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static dk.easv.ticketmanager.gui.FXMLPath.EDIT_USER_POPUP;
import static dk.easv.ticketmanager.gui.FXMLPath.USER_FORM_POPUP;


public class UserListDashboardController implements Initializable {
  @FXML
  private ListView<User> usersListView;
  private final UserDataModel userDataModel = new UserDataModel();
  private final FXMLManager fxmlManager = FXMLManager.getInstance();
  private final AuthenticationService authenticationService = AuthenticationService.getInstance();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    usersListView.setItems(userDataModel.getUsers());
  }

  @FXML
  public void addNewCoordinator() {
    Pair<Parent, UserFormPopupController> p = fxmlManager.loadFXML(USER_FORM_POPUP);
    Stage popupStage = new Stage();
    popupStage.setTitle("Add New User");

    Scene scene = new Scene(p.getKey(), 550, 550);
    popupStage.setScene(scene);
    popupStage.show();
  }

  @FXML
  public void onClickEditUser() {
    Pair<Parent, EditUserPopupController> p = fxmlManager.loadFXML(EDIT_USER_POPUP);
    Stage popupStage = new Stage();
    popupStage.setTitle("Edit User");

    Scene scene = new Scene(p.getKey(), 550, 550);
    popupStage.setScene(scene);
    popupStage.show();
  }

  @FXML
  public void onClickDeleteUser() {
    User selectedUser = usersListView.getSelectionModel().getSelectedItem();

    if (selectedUser == null) {
      showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a user to delete.");
      return;
    }

    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirm Deletion");
    confirmation.setHeaderText("Delete User");
    confirmation.setContentText("Are you sure you want to delete " +
            selectedUser.getFirstName() + " " + selectedUser.getLastName() + "?");

    confirmation.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        try {
          userDataModel.deleteUser(selectedUser);
          showAlert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully.");
        } catch (Exception e) {
          Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed to delete user", e);
          showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete user: " + e.getMessage());
        }
      }
    });
  }

  private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }
}