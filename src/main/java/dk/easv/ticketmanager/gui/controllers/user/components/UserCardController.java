package dk.easv.ticketmanager.gui.controllers.user.components;

import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class UserCardController
{

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
  private ImageView profileImage;



   private UserModel userModel;


  public void setUserModel(UserModel userModel)
  {
    this.userModel = userModel;
    this.txtUserEmail.textProperty().bind(userModel.emailProperty());
    this.txtUserRole.textProperty().bind(userModel.roleProperty().asString());
    this.txtUserFirstName.textProperty().bind(userModel.nameProperty());
    this.txtUserLastName.textProperty().bind(userModel.lastNameProperty());
    profileImage.imageProperty().bind(userModel.imageProperty());
  }

  @FXML
  private void onClickOpenEditUser(ActionEvent actionEvent)
  {
  }

  @FXML
  private void onClickDeleteUser(ActionEvent actionEvent)
  {
  }
}
