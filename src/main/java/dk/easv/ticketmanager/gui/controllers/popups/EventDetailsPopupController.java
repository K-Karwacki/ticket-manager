package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.dal.implementations.EventRepository;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.dashboards.EventListDashboardController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventDetailsPopupController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private final EventDataModel eventDataModel = new EventDataModel();
    private final TicketTypeCreatorPopupController ticketTypeCreatorPopupController = new TicketTypeCreatorPopupController();
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

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveEditedEvent;

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

    @FXML private void showTicketGeneratorForm(){
        Pair<Parent, TicketGeneratorPopupController> p = fxmlManager.getFXML(TICKET_GENERATOR_POPUP);
        p.getValue().addTicketTypes(event);
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
    @FXML private void showTicketTypeCreatorForm(){
        ticketTypeCreatorPopupController.load(event);
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
        rectangleImageContainer.setFill(new ImagePattern(image));

    }

    public Event getEvent() {
        return event;
    }

    @FXML
    public void onClickEditEvent() {
        Pair<Parent, EventEditorPopupController> p = fxmlManager.loadFXML(EVENT_EDITOR_POPUP);
        Stage popupStage = new Stage();
        popupStage.setTitle("Edit Event");
        p.getValue().setEvent(event);
        Scene scene = new Scene(p.getKey());
        popupStage.setScene(scene);
        popupStage.show();
    }

    @FXML
    public void onClickDelete() {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText("Delete Event");
        confirmation.setContentText("Are you sure you want to delete " + event.getName() + "?");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Pair<Parent, EventListDashboardController> p = FXMLManager.getInstance().getFXML(EVENTS_DASHBOARD);
                    eventDataModel.deleteEvent(event);
                    p.getValue().load();
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Event deleted successfully.");

                    successAlert.show();
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            javafx.application.Platform.runLater(() -> {
                                successAlert.close();
                                Stage stage = (Stage) deleteButton.getScene().getWindow();
                                stage.close();
                            });
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); //
                        }
                    }).start();

                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete event: " + e.getMessage());
                }
            }
        });
    }
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
