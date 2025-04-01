package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventHomeController;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.lang.reflect.Constructor;

public class MenuComponentController
{
  private BorderPane parentRoot;
  private final ViewManager viewManager = ViewManager.INSTANCE;
  @FXML private Parent menuRoot;

  public MenuComponentController() {
    parentRoot = null;
    menuRoot = null;

  }


  @FXML
  private void initialize(){
//    this.viewManager = new ViewManager(parentContainer);
  }

  public void setParentContainer(BorderPane parentRoot){
      this.parentRoot = parentRoot;
  }

  public void onClickOpenHome(MouseEvent event)
  {
    viewManager.switchDashboard(FXMLPath.HOME_DASHBOARD, "Home");
//    parentRoot.setCenter(FXMLManager.getInstance().getFXML(FXMLPath.HOME_DASHBOARD).getKey());
    switchButtonsHighlight(event);

  }

  public void onClickOpenUsers(MouseEvent event)
  {
//    parentRoot.setCenter(FXMLManager.getInstance().getFXML(FXMLPath.USERS_DASHBOARD).getKey());
    viewManager.switchDashboard(FXMLPath.USERS_DASHBOARD, "User management");
    switchButtonsHighlight(event);
  }


  public void onClickOpenEvents(MouseEvent event)
  {
    viewManager.switchDashboard(FXMLPath.EVENTS_DASHBOARD, "Event management");
    switchButtonsHighlight(event);

  }

  public void onClickOpenSettings(MouseEvent event)
  {
    viewManager.switchDashboard(FXMLPath.SETTINGS_DASHBOARD, "Settings");
    switchButtonsHighlight(event);

  }

  private void switchButtonsHighlight(MouseEvent event){
    menuRoot.getChildrenUnmodifiable().forEach(node -> {
      node.getStyleClass().remove("active-menu-btn");
    });
    Node clickedNode = (Node) event.getTarget();
    HBox root = null;
    if(clickedNode instanceof Text || clickedNode instanceof Label){
      HBox parent = (HBox) clickedNode.getParent().getParent();
      parent.getStyleClass().add("active-menu-btn");
    }else if(clickedNode instanceof HBox hBox){
      hBox.getStyleClass().add("active-menu-btn");
    }else{
      clickedNode.getParent().getStyleClass().add("active-menu-btn");
    }
  }

  public void onClickLogout(MouseEvent event) {
    viewManager.showStage(FXMLPath.LOGIN, "Login", false);
    UserSession.getInstance().clearSession();
  }

//  public void addNewMenuButton()
}
