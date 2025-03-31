package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.components.EventCardController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.collections.SetChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.util.List;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;

public class EventHomeController
{
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
  private DatabaseService databaseService;

  @FXML
  private FlowPane eventListRoot;
  @FXML
  private ScrollPane eventListScrollPane;
  @FXML
  private ComboBox<String> comboBoxCities;


  public EventHomeController() {

  }
  @FXML private void openEventCreator(){
    ViewManager.INSTANCE.switchDashboard(EVENT_CREATOR_POPUP, "Create Event");
  }

  @FXML
  private void initialize()
  {
    loadEventCards();
  }


  public void loadEventCards() {
    EventRepository eventRepository = databaseService.getRepositoryService().getRepository(EventRepository.class);
    eventListRoot.getChildren().removeAll();
    eventListRoot.getChildren().clear();
    List<Event> events = eventRepository.getAll();
    events.forEach(event -> {
      Pair<Parent, EventCardController> p = FXMLManager.INSTANCE.loadFXML(EVENT_CARD_COMPONENT);
    });

  }

  public void setDatabaseService(DatabaseService databaseService) {
    this.databaseService = databaseService;
  }
}
