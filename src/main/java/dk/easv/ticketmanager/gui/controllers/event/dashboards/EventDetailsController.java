package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.gui.FXMLManager;
//import dk.easv.ticketmanager.gui.models.EventDataModel;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketTypeCreatorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketGeneratorController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventDetailsController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private final ViewManager viewManager = ViewManager.INSTANCE;
    private static DatabaseService databaseService;
//    private final EventDataModel eventDataModel = new EventDataModel();
    private EventModel eventModel;

    @FXML
    private Rectangle rectangleImageContainer;

    @FXML
    private Label lblEventDate;

    @FXML
    private Label lblEventTime;

    @FXML
    private Label lblEventLocation;

    @FXML
    private Label lblNormalEventTickets;

    @FXML
    private Label lblSpecialEventTickets;

    @FXML
    private Label lblEventDescription;

    @FXML
    private Label lblEventName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setEventDetails();
    }
    @FXML private void showCoordinatorsListPopup(){
//        Pair<Parent, CoordinatorListPopupController> p = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
//        p.getValue().setEvent(eventModel);
//        p.getValue().displayNotAssignedCoordinatorsToEventList();

    }



    @FXML private void showAssignedCoordinatorsToEventPopup(){
//        Pair<Parent, CoordinatorListPopupController> p = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
//        p.getValue().setEvent(eventModel);
//        p.getValue().displayAssignedCoordinatorsToTheEventList();
    }

    public void setEventDetails(EventModel event) {
        this.eventModel = event;
        lblEventDate.textProperty().bind(event.dateProperty());
        lblEventTime.textProperty().bind(event.timeProperty());
        lblEventLocation.setText(event.getLocation().toString());
//        lblNormalEventTickets.setText(String.valueOf(event.getNormal_ticket_amount()));
//        lblSpecialEventTickets.setText(String.valueOf(event.getVip_ticket_amount()));
        lblEventDescription.textProperty().bind(event.descriptionProperty());
        lblEventName.textProperty().bind(event.nameProperty());
        Image image = new Image(Objects.requireNonNull(getClass().getResource(event.imagePathProperty().get())).toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        rectangleImageContainer.setFill(imagePattern);
    }

    public EventModel getEvent() {
        return eventModel;
    }

  public void setDatabaseService(DatabaseService databaseService)
  {
      this.databaseService = databaseService;
  }

    @FXML
    private void showTicketTypeCreatorForm() {
        TicketTypeCreatorController ticketTypeCreatorController = viewManager.showPopup(TICKET_TYPE_CREATOR_POPUP, "Ticket creator");
        ticketTypeCreatorController.setEvent(eventModel);
    }

    @FXML
    private void showTicketGeneratorForm() {
       TicketGeneratorController ticketGeneratorController= viewManager.showPopup(TICKET_GENERATOR_POPUP, "Ticket generator");
       ticketGeneratorController.addTicketTypes(eventModel);
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {}

    @FXML
    private void onClickEdit(ActionEvent actionEvent) {}

}
