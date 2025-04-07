package dk.easv.ticketmanager.gui.controllers.event.components;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventDetailsController;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.time.format.DateTimeFormatter;

public class EventCardController
{
  private final ViewManager viewManager = ViewManager.INSTANCE;
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
  private EventModel eventModel;
  private EventManagementService eventManagementService;

  @FXML private Rectangle eventImageContainer;
  @FXML private Label eventNameLbl;
  @FXML private Label eventDateLbl;
  @FXML private Label eventTimeLbl;
  @FXML private Label eventLocationLbl;
  @FXML private Label normalTicketsAmountLbl;
  @FXML private Label vipTicketsAmountLbl;


  public EventCardController(){
    eventModel = null;
  }

  public void setServices(EventManagementService eventManagementService)
  {
    this.eventManagementService = eventManagementService;
  }

  @FXML
  private void openEvent(){
    Pair<Parent, EventDetailsController> p = fxmlManager.getFXML(FXMLPath.EVENT_DETAILS);
    p.getValue().setEventDetails(eventModel);
    p.getValue().setServices(eventManagementService);
    viewManager.switchDashboard(FXMLPath.EVENT_DETAILS, "Event details");
  }

  @FXML
  private void onClickDeleteEvent(){
  }

  public void setEventModel(EventModel eventModel){
    this.eventModel = eventModel;


    eventNameLbl.textProperty().bind(eventModel.nameProperty());

    eventDateLbl.textProperty().bind(
        Bindings.createStringBinding(() ->
            eventModel.dateProperty().get().format(DateTimeFormatter.ISO_LOCAL_DATE), eventModel.dateProperty())
    );

    eventTimeLbl.textProperty().bind(
        Bindings.createStringBinding(() ->
            eventModel.timeProperty().get().format(DateTimeFormatter.ISO_LOCAL_TIME), eventModel.timeProperty())
    );


    eventLocationLbl.textProperty().bind(
        Bindings.createStringBinding(() ->
            eventModel.locationProperty().get().toString(), eventModel.locationProperty())
    );

    eventImageContainer.fillProperty().bind(eventModel.imageProperty());

//    eventImageContainer.fillProperty().bind(eventModel.imageProperty());

//    ImagePattern pattern = new ImagePattern(eventModel.getImage().get());
//    eventImageContainer.setFill(pattern);

  }



  //  @Override public void initialize(URL location, ResourceBundle resources)
//  {
//    eventNameLbl.textProperty().bind(eventModel.getSimpleStringProperty());
//
//  }
}
