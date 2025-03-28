package dk.easv.ticketmanager.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageManager
{
  private Stage currentStage;
  private final Map<String, Stage> stageCache = new HashMap<>();
  private SceneManager sceneManager;

  public StageManager() {
    sceneManager = null;
  }

  public StageManager(SceneManager sceneManager){
    this.sceneManager = sceneManager;
  }

  public void setSceneManager(SceneManager sceneManager)
  {
    this.sceneManager = sceneManager;
  }

  public void setCurrentStage(Stage currentStage)
  {
    this.currentStage = currentStage;
    sceneManager.setCurrentStage(currentStage);
  }

  // Load a new stage only if it hasn't been loaded before
  public void loadStage(String fxmlFile, String title, boolean isModal) {
    if (!stageCache.containsKey(fxmlFile)) {
      Parent root = FXMLManager.INSTANCE.getFXML(fxmlFile).getKey();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle(title);

      if (isModal) {
        stage.initModality(Modality.APPLICATION_MODAL);
      }

      stageCache.put(fxmlFile, stage); // Cache the stage
    }
  }

  // Show a cached stage
  public void showStage(String fxmlFile) {
    if(currentStage != null){
      currentStage.hide();
    }
    Stage stage = stageCache.get(fxmlFile);
    if (stage != null) {
      stage.setResizable(false);
      stage.show();
      currentStage = stage;
    } else {
      System.err.println("Stage not found in cache: " + fxmlFile);
    }
  }

  // Hide a stage instead of closing it
  public void hideStage(String fxmlFile) {
    Stage stage = stageCache.get(fxmlFile);
    if (stage != null) {
      stage.hide(); // Keeps it in memory, ready for reopening
    }
  }

  // Close and remove a stage from cache
  public void closeStage(String fxmlFile) {
    Stage stage = stageCache.remove(fxmlFile);
    if (stage != null) {
      stage.close();
    }
  }

  // Get a reference to the cached stage
  public Stage getStage(String fxmlFile) {
    return stageCache.get(fxmlFile);
  }

  public SceneManager getSceneManager()
  {
    return sceneManager;
  }
}
