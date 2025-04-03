package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.bll.services.implementations.EventManagementServiceImpl;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagementService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.gui.FXMLManager;
//import dk.easv.ticketmanager.gui.models.EventDataModel;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.popups.EventEditorController;
import dk.easv.ticketmanager.gui.controllers.ticket.SpecialTicketGeneratorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketTypeCreatorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketGeneratorController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventDetailsController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private final ViewManager viewManager = ViewManager.INSTANCE;
//    private final EventDataModel eventDataModel = new EventDataModel();
    private EventModel eventModel;
    private EventRepository eventRepository;
    private EventManagementService eventManagementService;

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
       TicketGeneratorController ticketGeneratorController = (TicketGeneratorController) fxmlManager.getFXML(TICKET_GENERATOR_POPUP).getValue();
       ticketGeneratorController.addTicketTypes(eventModel);
    }

    @FXML
    public void showSpecialTicketGeneratorForm() {
        viewManager.showPopup(SPECIAL_TICKET_GENERATOR_POPUP, "Special ticket generator");
        SpecialTicketGeneratorController specialTicketGeneratorController = (SpecialTicketGeneratorController) fxmlManager.getFXML(SPECIAL_TICKET_GENERATOR_POPUP).getValue();
    }

    @FXML
    private void onClickDelete() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete Event");
        confirm.setContentText("Are you sure you want to delete " + eventModel.getName() + "?");
        confirm.showAndWait();
            if(confirm.getResult() == ButtonType.OK){
        try {
            eventManagementService.deleteEvent(eventModel);
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }


    @FXML
    private void onClickEdit() {
        viewManager.showPopup(EVENT_EDITOR_POPUP, "Event editor");
        EventEditorController eventEditorController = (EventEditorController) fxmlManager.getFXML(EVENT_EDITOR_POPUP).getValue();
        eventEditorController.setEventModel(eventModel);
    }

    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
