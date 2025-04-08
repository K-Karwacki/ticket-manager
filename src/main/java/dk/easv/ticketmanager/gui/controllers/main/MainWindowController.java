package dk.easv.ticketmanager.gui.controllers.main;

import dk.easv.ticketmanager.gui.FXMLController;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.components.MenuComponentController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

public class MainWindowController
{
  private final ViewManager viewManager = ViewManager.INSTANCE;
  @FXML private StackPane dashboardRoot;
  @FXML private BorderPane root;

  public MainWindowController(){
  }
  @FXML
  private void initialize(){

    Pair<Parent, MenuComponentController> menuComponentControllerPair = FXMLManager.INSTANCE.getFXML(FXMLPath.MENU_COMPONENT);
//    menuComponentControllerPair.getValue().setParentContainer(root);
//    root.getCenter().setFocusTraversable(false);
    viewManager.setStageRoot(root);
  }
}

