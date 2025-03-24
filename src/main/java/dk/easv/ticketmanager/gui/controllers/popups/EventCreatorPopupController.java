package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.bll.EventService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.dashboards.EventListDashboardController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENTS_DASHBOARD;
import static dk.easv.ticketmanager.gui.FXMLPath.IMAGE_SELECTOR_POPUP;

public class EventCreatorPopupController {
    private final EventDataModel eventDataModel = new EventDataModel();
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private Event event = new Event();
    private String imagePath;

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
        eventDataModel.addEvent(event);
        eventDataModel.load();
        Pair<Parent, EventListDashboardController> p = fxmlManager.getFXML(EVENTS_DASHBOARD);
        p.getValue().load();
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
        Pair<Parent, ImageSelectorPopupController> p = fxmlManager.loadFXML(IMAGE_SELECTOR_POPUP);
        Stage stage = new Stage();
        stage.setTitle("Image Selector");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
        imageViewSelectedImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
    }
}
