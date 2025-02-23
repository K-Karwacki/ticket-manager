package dk.easv.ticketmanager.gui.controllers.dashboards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserListDashboardController implements Initializable
{
  @FXML
  private ListView<String> usersListView;
  private ArrayList<String> users;

  public UserListDashboardController(){
    this.users = new ArrayList<>();

    for(int i=0; i<10; i++){
      users.add("User "+i);
    }
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    users.forEach(user->{
      usersListView.getItems().add(user);
    });
  }
}
