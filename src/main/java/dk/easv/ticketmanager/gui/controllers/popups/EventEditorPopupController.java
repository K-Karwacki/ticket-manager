package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.dal.implementations.EventRepository;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EventEditorPopupController {

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

    private Event event;
    private final EventRepository eventRepository = new EventRepository();
    private final EventDataModel eventDataModel = new EventDataModel();

    public void setEvent(Event event) {
        this.event = event;
        if (event != null) {
            txtFieldEventName.setText(event.getName());
            txtAreaEventDescription.setText(event.getDescription());
            txtFieldEventTime.setText(event.getTime());
            datePickerEventDate.setValue(event.getDate());

            Location location = event.getLocation();
            if (location != null) {
                txtFieldEventLocationName.setText(location.getLocationName());
                txtFieldEventAddress.setText(location.getAddress());
                txtFieldEventCity.setText(location.getCity());
                txtFieldEventPostalCode.setText(location.getPostCode());
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


        Location location = event.getLocation();
        if (location == null) {
            location = new Location();
            event.setLocation(location);
        }

        location.setName(txtFieldEventLocationName.getText());
        location.setAddress(txtFieldEventAddress.getText());
        location.setCity(txtFieldEventCity.getText());
        location.setPostCode(txtFieldEventPostalCode.getText());

        try {
            eventDataModel.editEvent(event);

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
}