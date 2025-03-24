package dk.easv.ticketmanager;

import dk.easv.ticketmanager.gui.models.EventDataModel;
import dk.easv.ticketmanager.gui.models.TicketDataModel;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
  @Override public void start(Stage stage) throws IOException
  {
    UserDataModel userDataModel = new UserDataModel();
    EventDataModel eventDataModel = new EventDataModel();
    TicketDataModel ticketDataModel = new TicketDataModel();
    userDataModel.load();
    eventDataModel.load();
    ticketDataModel.load();
    FXMLLoader fxmlLoader = new FXMLLoader(
        Main.class.getResource("fxml/main/main.fxml"));
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