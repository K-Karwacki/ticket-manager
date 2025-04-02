package dk.easv.ticketmanager.gui.controllers.event.components;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventDetailsController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class EventCardController
{
  private final ViewManager viewManager = ViewManager.INSTANCE;
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
    Pair<Parent, EventDetailsController> p = fxmlManager.getFXML(FXMLPath.EVENT_DETAILS);
    p.getValue().setEventDetails(eventModel);
    viewManager.switchDashboard(FXMLPath.EVENT_DETAILS, "Event details");
  }

  @FXML
  private void onClickDeleteEvent(){
  }

  public void setEventModel(EventModel eventModel){
    this.eventModel = eventModel;
    eventNameLbl.textProperty().bind(eventModel.nameProperty());
    eventDateLbl.textProperty().bind(eventModel.dateProperty());
    eventTimeLbl.textProperty().bind(eventModel.timeProperty());
    normalTicketsAmountLbl.textProperty().bind(eventModel.normal_ticket_amountProperty());
    vipTicketsAmountLbl.textProperty().bind(eventModel.vip_ticket_amountProperty());
    eventLocationLbl.setText(eventModel.getLocation().toString());
//    ImagePattern pattern = new ImagePattern(eventModel.getImage());
//    eventImageContainer.setFill(pattern);

  }


  //  @Override public void initialize(URL location, ResourceBundle resources)
//  {
//    eventNameLbl.textProperty().bind(eventModel.getSimpleStringProperty());
//
//  }
}
