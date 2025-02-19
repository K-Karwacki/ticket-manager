package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuComponentController
{
  private final FXMLManager fxmlManager = FXMLManager.getInstance();
  private BorderPane parentContainer;
  @FXML private Node menuRootContainer;

  public void setParentContainer(BorderPane parentContainer){
      this.parentContainer = parentContainer;
  }

  public void onClickOpenHome(MouseEvent event)
  {
    System.out.println(parentContainer);
//    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.HOME_DASHBOARD).getKey());
  }

  public void onClickOpenUsers(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.USERS_DASHBOARD).getKey());
  }


  public void onClickOpenEvents(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.EVENTS_DASHBOARD).getKey());
  }

  public void onClickOpenSettings(MouseEvent event)
  {
    parentContainer.setCenter(fxmlManager.getFXML(FXMLPath.SETTINGS_DASHBOARD).getKey());
  }
}
