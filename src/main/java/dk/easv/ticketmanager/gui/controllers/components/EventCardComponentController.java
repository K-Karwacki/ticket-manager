package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_DETAILS_DASHBOARD;

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
    Pair<Parent, ?> eventScene = fxmlManager.loadFXML(EVENT_DETAILS_DASHBOARD);
    Stage stage = new Stage();
    stage.setTitle("Event Details");
    stage.setScene(new Scene(eventScene.getKey()));
    stage.show();
  }

  public void setEvent(Event event){
    this.event = event;
  }
  public Event getEvent(){
    return event;
  }
}
