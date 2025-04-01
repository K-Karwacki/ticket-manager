package dk.easv.ticketmanager.gui.controllers.main;

import dk.easv.ticketmanager.gui.FXMLController;
import dk.easv.ticketmanager.gui.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

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
