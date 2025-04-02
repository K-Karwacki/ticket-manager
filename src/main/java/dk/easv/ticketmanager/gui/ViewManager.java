package dk.easv.ticketmanager.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public enum ViewManager
{
  INSTANCE;
  private StageManager stageManager;
  private SceneManager sceneManager;
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;

  ViewManager(){
    stageManager = null;
    sceneManager = null;
  }

  public void setStageManager(StageManager stageManager)
  {
    this.stageManager = stageManager;
    this.sceneManager = this.stageManager.getSceneManager();
  }

  public void showStage(String fxml, String title, boolean isModal){
    if(stageManager != null){
      stageManager.loadStage(fxml, title, isModal);
      stageManager.showStage(fxml);

//      stageManager.setCurrentStage(stageManager);
    }
  }

  public void showScene(String fxml){
    if(stageManager != null){
      sceneManager.switchScene(fxml);
    }
  }

  public void switchDashboard(String fxml, String title){
    if(stageManager != null){
      sceneManager.switchDashboard(fxml, title);
    }
  }
  public <T> T showPopup(String fxmlFile, String title){
      return stageManager.showPopup(fxmlFile, title);
  }

  public void setStageRoot(BorderPane borderPane)
  {
    if(stageManager != null){
      sceneManager.setStageRoot(borderPane);
    }
  }
}
