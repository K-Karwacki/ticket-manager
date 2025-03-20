package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.EventDetailsPopupController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_DETAILS_POPUP;

public class EventCardComponentController
{
  private final FXMLManager fxmlManager = FXMLManager.getInstance();
  private Event event;

  @FXML private Rectangle eventImageContainer;
  @FXML private Label eventNameLbl;
  @FXML private Label eventDateLbl;
  @FXML private Label eventTimeLbl;
  @FXML private Label eventLocationLbl;
  @FXML private Label normalTicketsAmountLbl;
  @FXML private Label vipTicketsAmountLbl;

  public EventCardComponentController(){
  }

  @FXML
  private void openEvent(){
    Pair<Parent, EventDetailsPopupController> eventDetailsPopupControllerPair = fxmlManager.loadFXML(EVENT_DETAILS_POPUP);
    eventDetailsPopupControllerPair.getValue().setEventDetails(event);
    Stage stage = new Stage();
    stage.setTitle("Event Details");
    stage.setScene(new Scene(eventDetailsPopupControllerPair.getKey()));
    stage.show();
  }

  public void setEvent(Event event){
    this.event = event;
    eventNameLbl.setText(event.getName());
    eventDateLbl.setText(event.getDate().toString());
    eventTimeLbl.setText(event.getTime().toString());
    normalTicketsAmountLbl.setText(String.valueOf(event.getNormal_ticket_amount()));
    vipTicketsAmountLbl.setText(String.valueOf(event.getVip_ticket_amount()));
    eventLocationLbl.setText(event.getLocation().toString());
    Image image = new Image(event.getImagePath());
    ImagePattern pattern = new ImagePattern(image);
    eventImageContainer.setFill(pattern);
  }
  public Event getEvent(){
    return event;
  }
}
