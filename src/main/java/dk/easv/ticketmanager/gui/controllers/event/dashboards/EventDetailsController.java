package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.gui.FXMLManager;
//import dk.easv.ticketmanager.gui.models.EventDataModel;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.popups.EventEditorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketTypeCreatorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketGeneratorController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventDetailsController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private final ViewManager viewManager = ViewManager.INSTANCE;
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
        // will bind it in a sec
//        lblEventDate.textProperty().bind(event.dateProperty());
//        lblEventTime.textProperty().bind(event.timeProperty());
        lblEventLocation.setText(event.getLocation().toString());
        lblEventDescription.textProperty().bind(event.descriptionProperty());
        lblEventName.textProperty().bind(event.nameProperty());
//        Image image = event.getImage().get();
//        ImagePattern imagePattern = new ImagePattern(image);
//        rectangleImageContainer.setFill(imagePattern);
    }

    public EventModel getEvent() {
        return eventModel;
    }

    @FXML
    private void showTicketTypeCreatorForm() {
        viewManager.showPopup(TICKET_TYPE_CREATOR_POPUP, "Ticket creator");
        TicketTypeCreatorController ticketTypeCreatorController = (TicketTypeCreatorController) fxmlManager.getFXML(TICKET_TYPE_CREATOR_POPUP).getValue();
        ticketTypeCreatorController.setEvent(eventModel);
    }

    @FXML
    private void showTicketGeneratorForm() {
        viewManager.showPopup(TICKET_GENERATOR_POPUP, "Ticket generator");
       TicketGeneratorController ticketGeneratorController= (TicketGeneratorController) fxmlManager.getFXML(TICKET_GENERATOR_POPUP).getValue();
       ticketGeneratorController.addTicketTypes(eventModel);
    }

    @FXML
    private void onClickDelete(ActionEvent actionEvent) {}

    @FXML
    private void onClickEdit(ActionEvent actionEvent) {}
//    EventEditorController eventEditorController = viewManager.showPopup(EVENT_EDITOR_POPUP, "Event editor");
//    eventEditorController.dd
}
