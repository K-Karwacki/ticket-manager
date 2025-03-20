package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_LIST_POPUP;

public class EventDetailsPopupController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private final EventDataModel eventDataModel = new EventDataModel();
    private Event event;

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
        Pair<Parent, CoordinatorListPopupController> p = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
        p.getValue().setEvent(event);
        p.getValue().displayNotAssignedCoordinatorsToEventList();

    }



    @FXML private void showAssignedCoordinatorsToEventPopup(){
        Pair<Parent, CoordinatorListPopupController> p = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
        p.getValue().setEvent(event);
        p.getValue().displayAssignedCoordinatorsToTheEventList();
    }

    public void setEventDetails(Event event) {
        this.event = event;
        lblEventDate.setText(event.getDate().toString());
        lblEventTime.setText(event.getTime().toString());
        lblEventLocation.setText(event.getLocation().toString());
        lblNormalEventTickets.setText(String.valueOf(event.getNormal_ticket_amount()));
        lblSpecialEventTickets.setText(String.valueOf(event.getVip_ticket_amount()));
        lblEventDescription.setText(event.getDescription());
        lblEventName.setText(event.getName());
        Image image = new Image(Objects.requireNonNull(getClass().getResource(event.getImagePath())).toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        rectangleImageContainer.setFill(imagePattern);
    }

    public Event getEvent() {
        return event;
    }


}
