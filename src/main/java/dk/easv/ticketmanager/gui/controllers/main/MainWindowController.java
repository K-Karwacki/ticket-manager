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
  private final ViewManager viewManager = ViewManager.INSTANCE;
  @FXML private BorderPane root;

  public MainWindowController(){
  }
  @FXML
  private void initialize(){

    viewManager.setStageRoot(root);
  }
}
