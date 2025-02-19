package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EventCardComponentController
{
  @FXML private Label eventNameLbl;
  @FXML private ImageView eventImage;
  @FXML private Label eventDateLbl;
  @FXML private Label eventTimeLbl;
  @FXML private Label eventLocationLbl;
  @FXML private Label normalTicketsAmountLbl;
  @FXML private Label vipTicketsAmountLbl;

  public EventCardComponentController(){

  }


  public void setEventData(Event event){
    eventNameLbl.setText(event.getEventName());
    eventImage.setImage(event.getEventImage());
    eventDateLbl.setText(event.getEventDate().toString());
    eventTimeLbl.setText(event.getEventDate().getTime()+"");
    eventLocationLbl.setText(event.getEventLocation());
    normalTicketsAmountLbl.setText(event.getNormalTicketsAmount()+"");
    vipTicketsAmountLbl.setText(event.getVipTicketsAmount()+"");
  }
}
