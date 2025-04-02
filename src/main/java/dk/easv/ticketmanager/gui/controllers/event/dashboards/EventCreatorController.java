package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENTS_DASHBOARD;
import static dk.easv.ticketmanager.gui.FXMLPath.IMAGE_SELECTOR_POPUP;

public class EventCreatorController {
    private Event event = new Event();
    private String imagePath;
    private EventManagementService eventManagementService;

    @FXML
    private ImageView imageViewSelectedImage;
    @FXML
    private TextField txtFieldEventName;
    @FXML
    private TextArea txtAreaEventDescription;
    @FXML
    private TextField txtFieldEventTime;
    @FXML
    private DatePicker datePickerEventDate;
    @FXML
    private TextField txtFieldEventLocationName;
    @FXML
    private TextField txtFieldEventAddress;
    @FXML
    private TextField txtFieldEventCity;
    @FXML
    private TextField txtFieldEventPostalCode;

    @FXML private void addEvent(){
        setEventData();
        Stage stage = (Stage) txtFieldEventName.getScene().getWindow();
        stage.close();
        eventManagementService.createNewEvent(event);
        Pair<Parent, EventHomeController> p = FXMLManager.INSTANCE.getFXML(EVENTS_DASHBOARD);
        p.getValue().loadEventCards();
    }

    private void setEventData(){
        Location location = new Location();
        location.setName(txtFieldEventLocationName.getText());
        location.setCity(txtFieldEventCity.getText());
        location.setPostCode(txtFieldEventPostalCode.getText());
        location.setAddress(txtFieldEventAddress.getText());


        event.setLocation(location);
        event.setImagePath(imagePath);
        event.setName(txtFieldEventName.getText());
        event.setDescription(txtAreaEventDescription.getText());
        event.setDate(datePickerEventDate.getValue());
        event.setTime(txtFieldEventTime.getText());
        event.setDate(datePickerEventDate.getValue());
    }


    @FXML private void chooseImage() {
        ViewManager.INSTANCE.showPopup(IMAGE_SELECTOR_POPUP, "Image Selector");
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
        imageViewSelectedImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
    }
    public void setDatabaseService(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
