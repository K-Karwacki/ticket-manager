package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.popups.AssignCoordinatorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.ImageSelectorController;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.LocationModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.FieldValidator;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalTime;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventCreatorController {
    private Image image;
    EventModel eventModel = new EventModel();
    private EventManagementService eventManagementService;

    @FXML
    private HBox assignedCoordinatorsContainer;
    @FXML
    private VBox formContainer;
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

    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
        eventModel.getAssignedCoordinators().addListener((SetChangeListener<UserModel>) change ->{
            if(change.wasAdded() || change.wasRemoved()){
                loadAssignedCoordinators();
            }
        } );
    }

    @FXML
    private void initialize(){
        // Bind event creation form fields with event model properties
//        Bindings.bindBidirectional(txtFieldEventName.textProperty(), eventModel.nameProperty());
//        Bindings.bindBidirectional(txtAreaEventDescription.textProperty(), eventModel.descriptionProperty());
//        Bindings.bindBidirectional(txtFieldEventTime.textProperty(), eventModel.timeProperty(), new LocalTimeStringConverter(DateTimeFormatter.ISO_LOCAL_TIME, null));
//        Bindings.bindBidirectional(datePickerEventDate.valueProperty(), eventModel.dateProperty());
//        Bindings.bindBidirectional(txtFieldEventLocationName.textProperty(), eventModel.locationProperty().get()
//            .nameProperty());
//        Bindings.bindBidirectional(txtFieldEventAddress.textProperty(), eventModel.locationProperty().get()
//            .addressProperty());
//        Bindings.bindBidirectional(txtFieldEventCity.textProperty(), eventModel.locationProperty().getValue()
//            .cityProperty());
//        Bindings.bindBidirectional(txtFieldEventPostalCode.textProperty(), eventModel.locationProperty().get()
//            .post_codeProperty());

    }

    private void loadAssignedCoordinators(){
        assignedCoordinatorsContainer.getChildren().removeAll();
        assignedCoordinatorsContainer.getChildren().clear();

//        assignedCoordinatorsContainer.getChildren().addAll(eventModel.getAssignedCoordinators());
        for (UserModel assignedCoordinator : eventModel.getAssignedCoordinators())
        {
            assignedCoordinatorsContainer.getChildren().add(new Label(assignedCoordinator.getName()));
        }
    }

    // Method on click submit
    @FXML
    private void onClickSubmit(ActionEvent actionEvent) {
        boolean fieldsFilled = FieldValidator.areAllFieldsFilled(formContainer);
        if(!fieldsFilled){
            System.out.println("ERROR fill all fields");
            return;
        }
        if(!FieldValidator.isValidTime(txtFieldEventTime.getText())){
            System.out.println("time is not valid");
            return;
        }

        if(eventModel.getEventImage() == null){
            System.out.println("image not selected");
            return;
        }

        eventModel.setName(txtFieldEventName.getText());
        eventModel.setDescription(txtAreaEventDescription.getText());
        eventModel.setDate(datePickerEventDate.getValue());
        eventModel.setTime(LocalTime.parse(txtFieldEventTime.getText()));
        LocationModel locationModel = new LocationModel();
        locationModel.setCity(txtFieldEventCity.getText());
        locationModel.setAddress(txtFieldEventAddress.getText());
        locationModel.setPost_code(txtFieldEventPostalCode.getText());
        locationModel.setName(txtFieldEventLocationName.getText());
        eventModel.setLocation(locationModel);

        if(eventManagementService.createNewEvent(eventModel)){
            //reset the model
            eventModel = new EventModel();
            imageViewSelectedImage.setImage(null);
            FieldValidator.clearFields(formContainer);
            ViewManager.INSTANCE.switchDashboard(EVENTS_DASHBOARD, "Events");
        }

    }


    @FXML private void chooseImage() {
        ViewManager.INSTANCE.showPopup(IMAGE_SELECTOR_POPUP, "Image Selector");

        ImageSelectorController imageSelectorController = (ImageSelectorController) FXMLManager.INSTANCE.getFXML(IMAGE_SELECTOR_POPUP).getValue();
        imageSelectorController.setImageConsumer(selectedImage ->{
            imageViewSelectedImage.setImage(ImageConverter.convertToImage(selectedImage.getImageData()));
            eventModel.setEventImage(selectedImage);
        });
    }

    @FXML private void onClickOpenAssignCoordinators(){
        ViewManager.INSTANCE.showPopup(FXMLPath.COORDINATOR_LIST_POPUP, "Assign coordinators");

        AssignCoordinatorController assignCoordinatorController = (AssignCoordinatorController) FXMLManager.INSTANCE.getFXML(
            COORDINATOR_LIST_POPUP).getValue();

        assignCoordinatorController.setEventModel(eventModel);
    }



}
