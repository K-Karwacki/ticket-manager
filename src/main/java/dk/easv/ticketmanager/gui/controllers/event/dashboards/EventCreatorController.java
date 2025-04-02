package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.popups.ImageSelectorPopupController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENTS_DASHBOARD;
import static dk.easv.ticketmanager.gui.FXMLPath.IMAGE_SELECTOR_POPUP;

public class EventCreatorController {
    private final EventModel eventModel = new EventModel();
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



    // bind bidirectional values in text field to model and back
    @FXML
    private void initialize(){
        Bindings.bindBidirectional(txtFieldEventName.textProperty(), eventModel.nameProperty());
        Bindings.bindBidirectional(txtAreaEventDescription.textProperty(), eventModel.descriptionProperty());
        Bindings.bindBidirectional(txtFieldEventTime.textProperty(), eventModel.timeProperty(), new LocalTimeStringConverter(DateTimeFormatter.ISO_LOCAL_TIME, null));
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

    // Method on click submit

    public void onClickSubmit(ActionEvent actionEvent) {

    }



    // more methods for ticket creating etc
    @FXML private void chooseImage() {
        ViewManager.INSTANCE.showPopup(IMAGE_SELECTOR_POPUP, "Image Selector");
        ImageSelectorPopupController imageSelectorPopupController = (ImageSelectorPopupController) FXMLManager.INSTANCE.getFXML(IMAGE_SELECTOR_POPUP).getValue();


    }

    public void setImage(String imagePath) {
//        this.imagePath = imagePath;
        imageViewSelectedImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
    }
    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }




}
