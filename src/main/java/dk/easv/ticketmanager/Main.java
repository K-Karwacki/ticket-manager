package dk.easv.ticketmanager;

import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
  @Override public void start(Stage stage) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("fxml/main/coordinator.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setTitle("Login page");
    stage.setScene(scene);
    stage.show();
    stage.setResizable(false);
    stage.setMaximized(false);
  }

  public static void main(String[] args)
  {
    launch();
  }
}