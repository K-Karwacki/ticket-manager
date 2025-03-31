package dk.easv.ticketmanager.gui.controllers.event.components;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    eventNameLbl.textProperty().bind(eventModel.getSimpleStringProperty());

    eventNameLbl.setText(eventModel.getName());
    eventDateLbl.setText(event.getDate().toString());
    eventTimeLbl.setText(event.getTime().toString());
    normalTicketsAmountLbl.setText(String.valueOf(event.getNormal_ticket_amount()));
    vipTicketsAmountLbl.setText(String.valueOf(event.getVip_ticket_amount()));
    eventLocationLbl.setText(event.getLocation().toString());
    Image image = new Image(event.getImagePath());
    ImagePattern pattern = new ImagePattern(image);
    eventImageContainer.setFill(pattern);
  }


  //  @Override public void initialize(URL location, ResourceBundle resources)
//  {
//    eventNameLbl.textProperty().bind(eventModel.getSimpleStringProperty());
//
//  }
}
