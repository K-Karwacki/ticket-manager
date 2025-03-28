package dk.easv.ticketmanager.gui.controllers.event.dashboards;

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

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;

public class EventHomeController
{
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;

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
//    Pair<Parent, EventCreatorPopupController> p = fxmlManager.getFXML(EVENT_CREATOR_POPUP);
//    Stage stage = new Stage();
//    stage.setTitle("Event Creator");
//    stage.setScene(new Scene(p.getKey()));
//    stage.show();
  }

  @FXML
  private void initialize()
  {

    // Listen for changes if there was event added or removed
//    eventManagementService.getEventsObservable().addListener((SetChangeListener<EventModel>) change -> {
//      if (change.wasAdded()) {
//        Pair<Parent, EventCardController> eventCardComponentControllerPair = fxmlManager.loadFXML(EVENT_CARD_COMPONENT);
//        eventCardComponentControllerPair.getValue().setEventModel(change.getElementAdded());
//        eventListRoot.getChildren().add(eventCardComponentControllerPair.getKey());
//        System.out.println("Successfully loaded new event card: " + change.getElementAdded());
//      }
//      if (change.wasRemoved()) {
//        loadEventCards();
//        System.out.println("Successfully removed card: " + change.getElementRemoved());
//      }
//    });;

    loadEventCards();
  }


  public void loadEventCards() {
    System.out.println("Loading events");
    eventListRoot.getChildren().removeAll();
    eventListRoot.getChildren().clear();

//
//    eventManagementService.getEventsObservable().forEach(eventModel -> {
//      Pair<Parent, EventCardComponentController> eventCardComponentControllerPair = fxmlManager.loadFXML(EVENT_CARD_COMPONENT);
//      eventCardComponentControllerPair.getValue().setEventModel(eventModel);
//      System.out.println(eventCardComponentControllerPair.getValue());
//      eventListRoot.getChildren().add(eventCardComponentControllerPair.getKey());
//    });



//    EventManagementService.INSTANCE.getEventsObservable().forEach(eventModel -> {
//      System.out.println(eventModel.getSimpleStringProperty().getValue());
//
//      Pair<Parent, EventCardComponentController> eventCardComponentControllerPair = FXMLManager.getInstance().getFXML(EVENT_CARD_COMPONENT);
//      System.out.println(eventCardComponentControllerPair.getValue());
//
////      eventCardComponentControllerPair.getValue().setEventModel(eventModel);
////      eventListRoot.getChildren().add(eventCardComponentControllerPair.getKey());
//
//
////      Pair<Parent, EventCardComponentController> p = FXMLManager.getInstance().loadFXML(EVENT_CARD_COMPONENT);
////      p.getValue().setEventModel(eventModel);
////      Parent parent = p.getKey();
////      EventCardComponentController controller = p.getValue();
//    });
//    comboBoxCities.getItems().setAll(eventDataModel.getCities());
  }

}
