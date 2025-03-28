package dk.easv.ticketmanager.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;
import java.util.HashMap;
import java.util.Map;

public class SceneManager
{
  private Stage currentStage;
  private BorderPane stageRoot;
  private final Map<String, Scene> sceneCache = new HashMap<>();

  public SceneManager(){
    this.currentStage = null;
    this.stageRoot = null;
  }

  public SceneManager(Stage currentStage){
    this.currentStage = currentStage;
  }

  public Scene loadScene(String fxmlPath){
    if(!sceneCache.containsKey(fxmlPath)){
      Parent root = FXMLManager.INSTANCE.getFXML(fxmlPath).getKey();
      if(root != null){
        sceneCache.put(fxmlPath, new Scene(root));
        return sceneCache.get(fxmlPath);
      }
    }
    return sceneCache.get(fxmlPath);
  }

  public void switchScene(String fxmlPath){

    if(currentStage != null){
      if(stageRoot != null){
        stageRoot.setCenter(loadScene(fxmlPath).getRoot());
      }
    }
  }

  public void setCurrentStage(Stage stage)
  {
    this.currentStage = stage;
  }

  public void switchDashboard(String fxmlPath, String title)
  {
    if(currentStage != null){
      if(stageRoot != null){
        stageRoot.setCenter(loadScene(fxmlPath).getRoot());
        currentStage.setTitle(title);
      }
    }
  }

  public void setStageRoot(BorderPane stageRoot)
  {
    this.stageRoot = stageRoot;
  }
}
