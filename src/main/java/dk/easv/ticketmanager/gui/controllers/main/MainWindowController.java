package dk.easv.ticketmanager.gui.controllers.main;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.components.MenuComponentController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;

public class MainWindowController
{
  @FXML private BorderPane root;

  public MainWindowController(){
  }
  @FXML
  private void initialize(){
    FXMLManager fxmlManager = FXMLManager.INSTANCE;
    ViewManager viewManager = ViewManager.INSTANCE;
//
//
    Pair<Parent, MenuComponentController> menuComponentFxmlResult = fxmlManager.getFXML(
        FXMLPath.MENU_COMPONENT);
    Parent menuRoot = menuComponentFxmlResult.getKey();
    MenuComponentController menuComponentController = menuComponentFxmlResult.getValue();
    menuComponentController.setParentContainer(root);
//    menuComponentController.setViewManager(viewManager);
//    root.setLeft(menuRoot);
    ViewManager.INSTANCE.setStageRoot(root);
  }
}
