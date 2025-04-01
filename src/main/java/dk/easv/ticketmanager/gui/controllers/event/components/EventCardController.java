package dk.easv.ticketmanager.gui.controllers.event.components;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EventCardController
{
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
  private EventModel eventModel;

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



  @FXML
  private void openEvent(){
//    Pair<Parent, EventDetailsPopupController> eventDetailsPopupControllerPair = fxmlManager.loadFXML(EVENT_DETAILS_POPUP);
////    eventDetailsPopupControllerPair.getValue().setEventDetails(event);
//    Stage stage = new Stage();
//    stage.setTitle("Event Details");
//    stage.setScene(new Scene(eventDetailsPopupControllerPair.getKey()));
//    stage.show();
  }

  @FXML
  private void onClickDeleteEvent(){
  }

  public void setEventModel(EventModel eventModel){
    this.eventModel = eventModel;
    eventNameLbl.textProperty().bind(eventModel.nameProperty());
    eventDateLbl.textProperty().bind(eventModel.dateProperty());
    eventTimeLbl.textProperty().bind(eventModel.timeProperty());
    normalTicketsAmountLbl.textProperty().bind(eventModel.normalTicketAmountProperty());
    vipTicketsAmountLbl.textProperty().bind(eventModel.vipTicketAmountProperty());
    eventLocationLbl.textProperty().bind(eventModel.getLocation().nameProperty());
    ImagePattern pattern = new ImagePattern(eventModel.getImage());
    eventImageContainer.setFill(pattern);

  }


  //  @Override public void initialize(URL location, ResourceBundle resources)
//  {
//    eventNameLbl.textProperty().bind(eventModel.getSimpleStringProperty());
//
//  }
}
