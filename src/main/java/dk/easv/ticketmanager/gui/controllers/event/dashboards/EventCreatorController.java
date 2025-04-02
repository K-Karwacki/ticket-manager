package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagmentService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.ImageSelectorPopupController;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.IMAGE_SELECTOR_POPUP;

public class EventCreatorController
{
    private EventManagmentService eventManagmentService;
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
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

    public EventCreatorController(){

    }

    @FXML private void onClickAddNewEvent(){

        if(txtFieldEventLocationName.getText().isEmpty()){
            txtFieldEventLocationName.setStyle("-fx-border-color: red;");
            return;
        }

        // Assume that all fields are not null

        Location location = new Location();
        location.setName(txtFieldEventLocationName.getText());
        location.setCity(txtFieldEventCity.getText());
        location.setPostCode(txtFieldEventPostalCode.getText());
        location.setAddress(txtFieldEventAddress.getText());

        Event newEvent = new Event();
        newEvent.setLocation(location);
        newEvent.setImagePath(imagePath);
        newEvent.setName(txtFieldEventName.getText());
        newEvent.setDescription(txtAreaEventDescription.getText());
        newEvent.setDate(datePickerEventDate.getValue());
        newEvent.setTime(txtFieldEventTime.getText());

        Stage stage = (Stage) txtFieldEventName.getScene().getWindow();
        stage.close();
//        eventDataModel.addEvent(event);
//        eventDataModel.loadEvents();
//        Pair<Parent, EventListDashboardController> p = fxmlManager.getFXML(EVENTS_DASHBOARD);
//        p.getValue().load();
    }



    @FXML private void chooseImage() {
        Pair<Parent, ImageSelectorPopupController> p = fxmlManager.getFXML(IMAGE_SELECTOR_POPUP);
        Stage stage = new Stage();
        stage.setTitle("Image Selector");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }

    public void setImage(String imagePath) {
        this.imagePath = imagePath;
        imageViewSelectedImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
    }

    public void setDatabaseService(EventManagmentService eventManagmentService)
    {
        this.eventManagmentService = eventManagmentService;
    }
}
