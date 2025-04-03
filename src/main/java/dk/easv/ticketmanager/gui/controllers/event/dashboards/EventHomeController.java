package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.components.EventCardController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.util.List;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;

public class EventHomeController
{
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
  private final ViewManager viewManager = ViewManager.INSTANCE;
  private EventManagementService eventManagementService;

  @FXML
  private FlowPane eventListRoot;
  @FXML
  private ScrollPane eventListScrollPane;
  @FXML
  private ComboBox<String> comboBoxCities;


  public EventHomeController() {

  }


  public void setServices(EventManagementService eventManagementService) {
    this.eventManagementService = eventManagementService;
    ObservableList<EventModel> eventModelObservableList = eventManagementService.getEventListModel()
        .getEventsObservable();

    eventModelObservableList.addListener((ListChangeListener<EventModel>) change ->{
      while (change.next()){
        if(change.wasAdded()){
          loadEventCards();
        }
      }
    });

    loadEventCards();
  }

  @FXML
  private void initialize(){
//    loadEventCards();
  }

  @FXML private void openEventCreator(){
    viewManager.switchDashboard(EVENT_CREATOR_POPUP, "Create Event");
  }



  public void loadEventCards() {
    eventListRoot.getChildren().removeAll();
    eventListRoot.getChildren().clear();
    List<EventModel> events = eventManagementService.getEventListModel().getEventsObservable();
    events.forEach(event -> {
      Pair<Parent, EventCardController> p = fxmlManager.loadFXML(EVENT_CARD_COMPONENT);
      p.getValue().setEventModel(event);
      eventListRoot.getChildren().add(p.getKey());
    });

    if(events.isEmpty()){
      eventListRoot.getChildren().add(new Label("No events to show"));
    }
  }


}
