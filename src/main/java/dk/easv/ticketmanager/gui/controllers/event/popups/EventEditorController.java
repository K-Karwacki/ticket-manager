package dk.easv.ticketmanager.gui.controllers.event.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;

import dk.easv.ticketmanager.bll.services.EventManagementService;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.LocationModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EventEditorController {

    @FXML
    private TextField txtFieldEventName;
    @FXML
    private TextArea txtAreaEventDescription;
    @FXML
    private TextField txtFieldEventLocationName;
    @FXML
    private TextField txtFieldEventAddress;
    @FXML
    private TextField txtFieldEventCity;
    @FXML
    private TextField txtFieldEventPostalCode;
    @FXML
    private TextField txtFieldEventTime;
    @FXML
    private DatePicker datePickerEventDate;
    @FXML
    private Label errorLabel;
    @FXML
    private Button saveEditedEvent;

    private EventModel event;
    private EventManagementService eventManagementService;

    public void setEvent(EventModel event) {
        this.event = event;
        if (event != null) {
            txtFieldEventName.setText(event.nameProperty().getValue());
            txtAreaEventDescription.setText(event.descriptionProperty().getValue());
            txtFieldEventTime.setText(event.timeProperty().getValue());
            datePickerEventDate.setValue(LocalDate.parse(event.dateProperty().getValue()));

            LocationModel location = event.getLocation();
            if (location != null) {
                txtFieldEventLocationName.setText(location.nameProperty().getValue());
                txtFieldEventAddress.setText(location.addressProperty().getValue());
                txtFieldEventCity.setText(location.cityProperty().getValue());
                txtFieldEventPostalCode.setText(location.post_codeProperty().getValue());
            }
        }
    }

    @FXML
    public void onSave() {
        if (event == null) {
            errorLabel.setText("Error: No event selected!");
            return;
        }

        event.setName(txtFieldEventName.getText());
        event.setDescription(txtAreaEventDescription.getText());
        event.setTime(txtFieldEventTime.getText());
        event.setDate(datePickerEventDate.getValue());


        LocationModel location = event.getLocation();
        if (location == null) {
            location = new LocationModel();
            event.setLocation(location);
        }

        location.setName(txtFieldEventLocationName.getText());
        location.setAddress(txtFieldEventAddress.getText());
        location.setCity(txtFieldEventCity.getText());
        location.setPostCode(txtFieldEventPostalCode.getText());

        try {
            eventManagementService.updateEvent(event);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    javafx.application.Platform.runLater(() -> {
                        Stage stage = (Stage) saveEditedEvent.getScene().getWindow();
                        stage.close();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception ex) {
            errorLabel.setText("Error saving event: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void setDatabaseService(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
