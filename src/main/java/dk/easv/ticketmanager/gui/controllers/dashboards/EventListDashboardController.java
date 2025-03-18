package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.EventCardComponentController;
import dk.easv.ticketmanager.gui.controllers.popups.EventCreatorPopupController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;

public class EventListDashboardController implements Initializable
{
  private EventDataModel eventDataModel = new EventDataModel();
  private final FXMLManager fxmlManager = FXMLManager.getInstance();

  @FXML
  private FlowPane eventListRoot;
  @FXML
  private ScrollPane eventListScrollPane;
  @FXML
  private ComboBox<String> comboBoxCities;


  public EventListDashboardController() {

  }
  @FXML private void openEventCreator(){
    Pair<Parent, EventCreatorPopupController> p = fxmlManager.loadFXML(EVENT_CREATOR_POPUP);
    Stage stage = new Stage();
    stage.setTitle("Event Creator");
    stage.setScene(new Scene(p.getKey()));
    stage.show();
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    load();
  }

  public void clear(){
    eventListRoot.getChildren().clear();
    comboBoxCities.getItems().clear();
  }
  public void load() {
    clear();
    eventDataModel.getEvents().forEach(event -> {
      Pair<Parent, EventCardComponentController> p = fxmlManager.loadFXML(EVENT_CARD_COMPONENT);
      p.getValue().setEvent(event);
      eventListRoot.getChildren().add(p.getKey());
    });
    comboBoxCities.getItems().setAll(eventDataModel.getCities());
  }
}
