package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.popups.AssignCoordinatorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.ImageSelectorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.TicketCreatorController;
import dk.easv.ticketmanager.gui.controllers.user.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.gui.models.event.LocationModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.event.TicketModel;
import dk.easv.ticketmanager.utils.FieldValidator;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.awt.*;
import java.time.LocalTime;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventCreatorController {
    private Image image;
    EventModel eventModel = new EventModel();
    private EventManagementService eventManagementService;

    @FXML
    private StackPane assignedCoordinatorsContainer;
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

    @FXML
    private ListView<TicketModel> ticketListView;

    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }

    @FXML
    private void initialize(){

        // Listen on assigned coordinators
        eventModel.getAssignedCoordinators().addListener((SetChangeListener<UserModel>) change ->{
            if(change.wasAdded() || change.wasRemoved()){
                loadAssignedCoordinators();
            }
        } );

        // Listen on added tickets
        eventModel.getTickets().addListener((SetChangeListener<TicketModel>) change->{
            if(change.wasAdded() || change.wasRemoved()){
                System.out.println("ticket added or removed");
                loadCreatedTickets();
            }
        });
    }

    // Method to load the tickets
    private void loadCreatedTickets(){
        ObservableList<TicketModel> ticketModels = FXCollections.observableArrayList(eventModel.getTickets());
        ticketListView.setItems(ticketModels);
    }

    // Method to load the coordinators
    private void loadAssignedCoordinators(){
        assignedCoordinatorsContainer.getChildren().removeAll();
        assignedCoordinatorsContainer.getChildren().clear();

//        assignedCoordinatorsContainer.getChildren().addAll(eventModel.getAssignedCoordinators());
        for (UserModel assignedCoordinator : eventModel.getAssignedCoordinators())
        {
            Pair<Parent, CoordinatorCardController> coordinatorCardPair = FXMLManager.INSTANCE.getFXML(
                COORDINATOR_CARD_COMPONENT);
            coordinatorCardPair.getValue().setModel(assignedCoordinator, this.eventModel);
            assignedCoordinatorsContainer.getChildren().add(coordinatorCardPair.getKey());
        }
    }


    // Method on click submit new event and try save data to db via event management service
    @FXML
    private void onClickSubmit() {
        System.out.println(eventModel.getTickets().size());

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

        if(eventModel.getTickets().size() < 1){
            System.out.println("ERROR please create at least one ticket");
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

        // calls service. If true reset fields and switch dashboard
        if(eventManagementService.createNewEvent(eventModel)){
            //reset the model
            eventModel = new EventModel();
            ticketListView.getItems().clear();
            assignedCoordinatorsContainer.getChildren().clear();
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

    @FXML private void onClickOpenAddTicket(){
        ViewManager.INSTANCE.showPopup(FXMLPath.TICKET_CREATOR_POPUP, "Add ticket");
        TicketCreatorController ticketCreatorController = (TicketCreatorController) FXMLManager.INSTANCE.getFXML(TICKET_CREATOR_POPUP).getValue();
        ticketCreatorController.setEvent(eventModel);
    }


}
