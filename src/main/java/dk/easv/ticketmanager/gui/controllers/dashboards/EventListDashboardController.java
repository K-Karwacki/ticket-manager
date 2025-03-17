package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.EventCardComponentController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;

public class EventListDashboardController implements Initializable
{
  private EventDataModel eventDataModel = new EventDataModel();
  private final FXMLManager fxmlManager = FXMLManager.getInstance();

  @FXML
  private FlowPane eventListRoot;
  @FXML
  private ScrollPane eventListScrollPane;


  public EventListDashboardController() {

  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    eventDataModel.getEvents().forEach(event -> {
      Pair<Parent, EventCardComponentController> p = fxmlManager.loadFXML(EVENT_CARD_COMPONENT);
      p.getValue().setEvent(event);
      eventListRoot.getChildren().add(p.getKey());
    });
  }
}
