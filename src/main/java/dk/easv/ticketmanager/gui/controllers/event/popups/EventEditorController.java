package dk.easv.ticketmanager.gui.controllers.event.popups;


import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.LocalTimeStringConverter;
import java.time.format.DateTimeFormatter;

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

    private EventModel eventModel;
    private EventManagementService eventManagementService;


    public void setEventModel(EventModel eventModel){
        this.eventModel = eventModel;
        Bindings.bindBidirectional(txtFieldEventName.textProperty(), eventModel.nameProperty());
        Bindings.bindBidirectional(txtAreaEventDescription.textProperty(), eventModel.descriptionProperty());
        Bindings.bindBidirectional(txtFieldEventTime.textProperty(), eventModel.timeProperty(), new LocalTimeStringConverter(
            DateTimeFormatter.ISO_LOCAL_TIME, null));
        Bindings.bindBidirectional(datePickerEventDate.valueProperty(), eventModel.dateProperty());
        Bindings.bindBidirectional(txtFieldEventLocationName.textProperty(), eventModel.locationProperty().get()
            .nameProperty());
        Bindings.bindBidirectional(txtFieldEventAddress.textProperty(), eventModel.locationProperty().get()
            .addressProperty());
        Bindings.bindBidirectional(txtFieldEventCity.textProperty(), eventModel.locationProperty().getValue()
            .cityProperty());
        Bindings.bindBidirectional(txtFieldEventPostalCode.textProperty(), eventModel.locationProperty().get()
            .post_codeProperty());

    }


    public void onSave() {
        if (eventManagementService == null) {
            errorLabel.setText("Error: Service not initialized");
            return;
        }
        try {
            eventManagementService.updateEvent(eventModel);
            errorLabel.setText("Event saved successfully");
        } catch (Exception e) {
            errorLabel.setText("Error saving event: " + e.getMessage());
        }
    }

    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
