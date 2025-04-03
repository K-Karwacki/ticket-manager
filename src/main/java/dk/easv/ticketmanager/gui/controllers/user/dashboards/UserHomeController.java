package dk.easv.ticketmanager.gui.controllers.user.dashboards;

import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.components.EventCardController;
import dk.easv.ticketmanager.gui.controllers.user.components.UserCardController;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.util.List;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.USER_CARD_COMPONENT;

public class UserHomeController
{
  private UserManagementService userManagementService;


  @FXML private FlowPane userListRoot;

  public void setServices(UserManagementService userManagementService){
    this.userManagementService = userManagementService;
    ObservableSet<UserModel> userModelObservableList = userManagementService.getUserListModel().getUsersObservable();

    userModelObservableList.addListener((SetChangeListener<UserModel>) change ->{
      if(change.wasAdded() || change.wasRemoved()){
          loadUserCards();
      }
    });

    loadUserCards();
  }

  @FXML
  private void initialize(){
  }

  @FXML
  private void onClickOpenCreateUser(){
    ViewManager.INSTANCE.showPopup(FXMLPath.USER_CREATOR_POPUP, "Create new user");
  }

  private void loadUserCards(){
    userListRoot.getChildren().removeAll();
    userListRoot.getChildren().clear();

    userManagementService.getUserListModel().getUsersObservable().forEach(userModel -> {
      Pair<Parent, UserCardController> p = FXMLManager.INSTANCE.loadFXML(USER_CARD_COMPONENT);
      p.getValue().setUserModel(userModel);
      userListRoot.getChildren().add(p.getKey());
    });

    if(userManagementService.getUserListModel().getUsersObservable().isEmpty()){
      userListRoot.getChildren().add(new Label("No users to show"));
    }
  }
}
