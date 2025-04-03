package dk.easv.ticketmanager.gui.controllers.user.components;

import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UserCardController
{

   private UserModel userModel;


  public void setUserModel(UserModel userModel)
  {
    this.userModel = userModel;
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
