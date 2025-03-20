package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
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
    @FXML private void showCoordinators(){

        Pair<Parent, CoordinatorListPopupController> p = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
        p.getValue().showAll();
        Stage stage = new Stage();
        stage.setTitle("Coordinators List");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }

    @FXML private void showAssignedCoordinators(){
        Pair<Parent, CoordinatorListPopupController> p = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
        Stage stage = new Stage();
        p.getValue().showAssignedUsers();
        stage.setTitle("Coordinators List");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
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
