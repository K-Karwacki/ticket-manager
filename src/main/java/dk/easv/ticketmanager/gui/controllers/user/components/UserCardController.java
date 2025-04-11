package dk.easv.ticketmanager.gui.controllers.user.components;

import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.user.popup.UserEditorController;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import dk.easv.ticketmanager.utils.ImageConverter;
import dk.easv.ticketmanager.utils.RoleType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.USER_EDITOR_POPUP;


public class UserCardController
{

  private static UserManagementService userManagementService;
  @FXML
  private Label txtUserFirstName;

  @FXML
  private Label txtUserLastName;

  @FXML
  private Label txtUserEmail;

  @FXML
  private Label txtUserPhoneNumber;

  @FXML
  private Label txtUserRole;

  @FXML
  private Circle profileImage;

  @FXML private Button btnEditUser, btnDeleteUser;



  private UserModel userModel;


  public void setUserModel(UserModel userModel)
  {
    this.userModel = userModel;
    this.txtUserEmail.textProperty().bind(userModel.emailProperty());
    this.txtUserRole.textProperty().bind(userModel.roleProperty().asString());
    this.txtUserFirstName.textProperty().bind(userModel.nameProperty());
    this.txtUserLastName.textProperty().bind(userModel.lastNameProperty());
    this.txtUserPhoneNumber.textProperty().bind(userModel.phoneNumberProperty());
    profileImage.fillProperty().bind(userModel.imagePatternProperty());
    if (UserSession.INSTANCE.getLoggedUserModel() != null) {
      System.out.println("USER ROLE : " + UserSession.INSTANCE.getLoggedUserModel().roleProperty().get().getName());
      if ("COORDINATOR".equals(UserSession.INSTANCE.getLoggedUserModel().roleProperty().get().getName())) {
        System.out.println("ROLE IS COORDINATOR");
        btnDeleteUser.setVisible(false);
        btnEditUser.setVisible(false);
      }
    }
  }

  @FXML
  private void onClickOpenEditUser(ActionEvent actionEvent)
  {
    Pair<Parent, UserEditorController> p = FXMLManager.INSTANCE.getFXML(USER_EDITOR_POPUP);
    p.getValue().setUserModel(userModel);
    ViewManager.INSTANCE.showPopup(USER_EDITOR_POPUP, "User Editor");
  }

  @FXML
  private void onClickDeleteUser(ActionEvent actionEvent)
  {
    userManagementService.deleteUser(this.userModel);
  }

  public void setServices(UserManagementService userManagementService) {
    UserCardController.userManagementService = userManagementService;
  }
}
