package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MenuComponentController
{
  private final FXMLManager fxmlManager = FXMLManager.getInstance();
//  public VBox menuRoot;
  private BorderPane parentContainer;
  @FXML private Parent menuRoot;

  public void setParentContainer(BorderPane parentContainer){
      this.parentContainer = parentContainer;
  }

  public void onClickOpenHome(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.HOME_DASHBOARD).getKey());
    switchButtonsHighlight(event);

  }

  public void onClickOpenUsers(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.USERS_DASHBOARD).getKey());
    switchButtonsHighlight(event);
  }


  public void onClickOpenEvents(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.EVENTS_DASHBOARD).getKey());
    switchButtonsHighlight(event);

  }

  public void onClickOpenSettings(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.SETTINGS_DASHBOARD).getKey());
    switchButtonsHighlight(event);

  }

  private void switchButtonsHighlight(MouseEvent event){
    menuRoot.getChildrenUnmodifiable().forEach(node -> {
      node.getStyleClass().remove("active-menu-btn");
    });
    HBox node = (HBox) event.getTarget();
    if(node.getAccessibleRole().equals(AccessibleRole.BUTTON)){
      node.getStyleClass().add("active-menu-btn");
    }
  }
}
