package dk.easv.ticketmanager.gui.controllers.event.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;

import dk.easv.ticketmanager.bll.services.EventManagementService;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.LocationModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @FXML
    public void onSave() {
        if (eventModel == null) {
            errorLabel.setText("Error: No event selected!");
            return;
        }

//        System.out.println(eventModel.getImage());

//        eventModel.setName(txtFieldEventName.getText());
//        eventModel.setDescription(txtAreaEventDescription.getText());
//        eventModel.setTime(LocalTime.parse(txtFieldEventTime.getText()));
//        eventModel.setDate(datePickerEventDate.getValue());
//
//
//        LocationModel location = eventModel.getLocation();
//        if (location == null) {
//            location = new LocationModel();
//            event.setLocation(location);
//        }
//
//        location.setName(txtFieldEventLocationName.getText());
//        location.setAddress(txtFieldEventAddress.getText());
//        location.setCity(txtFieldEventCity.getText());
////        location.setPostCode(txtFieldEventPostalCode.getText());
//
//        try {
//            eventManagementService.updateEvent(event);
//
//            new Thread(() -> {
//                try {
//                    Thread.sleep(2000);
//                    javafx.application.Platform.runLater(() -> {
//                        Stage stage = (Stage) saveEditedEvent.getScene().getWindow();
//                        stage.close();
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//
//        } catch (Exception ex) {
//            errorLabel.setText("Error saving event: " + ex.getMessage());
//            ex.printStackTrace();
//        }
    }
    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
