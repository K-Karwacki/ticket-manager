package dk.easv.ticketmanager.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public enum ViewManager
{
  INSTANCE;
  private StageManager stageManager;
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;

  ViewManager(){
    stageManager = null;
  }

  public void setStageManager(StageManager stageManager)
  {
    this.stageManager = stageManager;
  }

  public void showStage(String fxml, String title, boolean isModal){
    if(stageManager != null){
      stageManager.loadStage(fxml, title, isModal);
      stageManager.showStage(fxml);
      stageManager.setCurrentStage(stageManager.getStage(fxml));
    }
  }

  public void showScene(String fxml){
    if(stageManager != null){
      stageManager.getSceneManager().switchScene(fxml);
    }
  }

  public void switchDashboard(String fxml, String title){
    if(stageManager != null){
      stageManager.getSceneManager().switchDashboard(fxml, title);
    }
  }


  public void setStageRoot(BorderPane borderPane)
  {
    if(stageManager != null){
      stageManager.getSceneManager().setStageRoot(borderPane);
    }
  }
}
