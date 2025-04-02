package dk.easv.ticketmanager.gui.controllers.main;

import dk.easv.ticketmanager.gui.FXMLController;
import dk.easv.ticketmanager.gui.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainWindowController
{
  private final ViewManager viewManager = ViewManager.INSTANCE;
  @FXML private StackPane dashboardRoot;
  @FXML private BorderPane root;

  public MainWindowController(){
  }
  @FXML
  private void initialize(){

    root.getCenter().setFocusTraversable(false);
    viewManager.setStageRoot(root);
  }
}

