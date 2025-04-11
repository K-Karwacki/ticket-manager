package dk.easv.ticketmanager.gui.controllers.event.dashboards;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
//import dk.easv.ticketmanager.gui.models.EventDataModel;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.popups.AssignCoordinatorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.EventEditorController;
import dk.easv.ticketmanager.gui.controllers.ticket.SpecialTicketGeneratorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.TicketCreatorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketGeneratorController;
import dk.easv.ticketmanager.gui.controllers.user.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.gui.models.event.TicketModel;
import javafx.beans.binding.Bindings;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class EventDetailsController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private final ViewManager viewManager = ViewManager.INSTANCE;
//    private final EventDataModel eventDataModel = new EventDataModel();
    private EventModel eventModel;
    private EventManagementService eventManagementService;

    @FXML
    private Rectangle rectangleImageContainer;

    @FXML
    private Label lblEventDate;

    @FXML
    private Label lblEventTime;

    @FXML
    private Label lblEventLocation;

    @FXML
    private Label lblNormalEventTickets;

    @FXML
    private Label lblSpecialEventTickets;

    @FXML
    private Label lblEventDescription;

    @FXML
    private Label lblEventName;

    @FXML
    private ListView<TicketModel> ticketsListView;

    @FXML
    private ScrollPane coordinatorsScrollPane;

    @FXML
    private Button showCoordinatorsBtn;

    @FXML
    private Button showTicketListViewBtn;

    @FXML
    private VBox coordinatorsContainerVBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setEventDetails();
        ticketsListView.setVisible(false);
        ticketsListView.setManaged(false);
        coordinatorsScrollPane.setVisible(false);
        coordinatorsScrollPane.setManaged(false);
        rectangleImageContainer.setVisible(true);
        rectangleImageContainer.setManaged(true);


    }

    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }

    public void setEventDetails(EventModel event) {
        this.eventModel = event;

        loadCoordinatorCards();

        lblEventDate.textProperty().bind(Bindings.createStringBinding(
            () -> event.dateProperty().get() != null ? event.dateProperty().get().format(
                DateTimeFormatter.ofPattern("dd MMM yyyy")) : "",
            event.dateProperty()));

        lblEventTime.textProperty().bind(Bindings.createStringBinding(
            () -> event.timeProperty().get() != null
                ? event.timeProperty().get().format(DateTimeFormatter.ofPattern("HH:mm"))
                : "",
            event.timeProperty()
        ));


        lblEventLocation.setText(event.getLocation().toString());
        lblEventDescription.textProperty().bind(event.descriptionProperty());
        lblEventName.textProperty().bind(event.nameProperty());
        rectangleImageContainer.setFill(event.getImage());

        ticketsListView.getItems().setAll(event.getTickets());

        this.eventModel.getAssignedCoordinators().addListener((SetChangeListener<UserModel>) (observable -> {
            if(observable.wasAdded() || observable.wasRemoved()){
//                System.out.println("lalaa");

                loadCoordinatorCards();
            }
        }));

    }

    private void loadCoordinatorCards(){
        coordinatorsContainerVBox.getChildren().clear();
        coordinatorsContainerVBox.getChildren().removeAll();
        for (UserModel assignedCoordinator : this.eventModel.getAssignedCoordinators())
        {
            Pair<Parent, CoordinatorCardController> coordinatorCardControllerPair = FXMLManager.INSTANCE.loadFXML(
                COORDINATOR_CARD_COMPONENT);
            coordinatorCardControllerPair.getValue().setServices(eventManagementService);
            coordinatorCardControllerPair.getValue().setModel(assignedCoordinator, this.eventModel);
            if(!coordinatorsContainerVBox.getChildren().contains(coordinatorCardControllerPair.getKey())){
                coordinatorsContainerVBox.getChildren().add(coordinatorCardControllerPair.getKey());
            }
        }
    }

    public EventModel getEvent() {
        return eventModel;
    }

    @FXML
    private void showTicketTypeCreatorForm() {
        viewManager.showPopup(TICKET_CREATOR_POPUP, "Ticket creator");
        TicketCreatorController ticketCreatorController = (TicketCreatorController) fxmlManager.getFXML(TICKET_CREATOR_POPUP).getValue();
        ticketCreatorController.setEvent(eventModel);
    }

    @FXML
    private void showTicketGeneratorForm() {
        viewManager.showPopup(TICKET_GENERATOR_POPUP, "Ticket generator");
       TicketGeneratorController ticketGeneratorController = (TicketGeneratorController) fxmlManager.getFXML(TICKET_GENERATOR_POPUP).getValue();
       ticketGeneratorController.setServices(eventManagementService);
       ticketGeneratorController.setEventModel(this.eventModel);
    }

    @FXML
    public void showSpecialTicketGeneratorForm() {
        viewManager.showPopup(SPECIAL_TICKET_GENERATOR_POPUP, "Special ticket generator");
        SpecialTicketGeneratorController specialTicketGeneratorController = (SpecialTicketGeneratorController) fxmlManager.getFXML(SPECIAL_TICKET_GENERATOR_POPUP).getValue();
    }

    @FXML
    private void onClickDelete() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete Event");
        confirm.setContentText("Are you sure you want to delete " + eventModel.getName() + "?");
        confirm.showAndWait();
            if(confirm.getResult() == ButtonType.OK){
        try {
            eventManagementService.deleteEvent(eventModel);
        } catch (Exception e) {
            e.printStackTrace();
            }
        }
    }


    @FXML
    private void onClickEdit() {
        viewManager.showPopup(EVENT_EDITOR_POPUP, "Event editor");
        EventEditorController eventEditorController = (EventEditorController) fxmlManager.getFXML(EVENT_EDITOR_POPUP).getValue();
        eventEditorController.setEventModel(eventModel);
    }


    public void onClickShowTickets(ActionEvent actionEvent)
    {
        if(showTicketListViewBtn.getStyleClass().contains("active")){
            showTicketListViewBtn.getStyleClass().remove("active");
            ticketsListView.setVisible(false);
            ticketsListView.setManaged(false);
            rectangleImageContainer.setVisible(true);
            rectangleImageContainer.setManaged(true);
            return;
        }

        rectangleImageContainer.setVisible(false);
        rectangleImageContainer.setManaged(false);
        coordinatorsScrollPane.setVisible(false);
        coordinatorsScrollPane.setManaged(false);
        ticketsListView.setVisible(true);
        ticketsListView.setManaged(true);
        showCoordinatorsBtn.getStyleClass().remove("active");
        showTicketListViewBtn.getStyleClass().add("active");
    }

    public void onClickShowCoordinators()
    {
        if(showCoordinatorsBtn.getStyleClass().contains("active")){
            showCoordinatorsBtn.getStyleClass().remove("active");
            coordinatorsScrollPane.setVisible(false);
            coordinatorsScrollPane.setManaged(false);
            rectangleImageContainer.setVisible(true);
            rectangleImageContainer.setManaged(true);
            return;
        }

        rectangleImageContainer.setVisible(false);
        rectangleImageContainer.setManaged(false);
        coordinatorsScrollPane.setVisible(true);
        coordinatorsScrollPane.setManaged(true);
        ticketsListView.setVisible(false);
        ticketsListView.setManaged(false);
        showTicketListViewBtn.getStyleClass().remove("active");
        showCoordinatorsBtn.getStyleClass().add("active");
    }

    @FXML
    private void onClickAssignCoordinator()
    {
        Pair<Parent, AssignCoordinatorController> parentAssignCoordinatorControllerPair = FXMLManager.INSTANCE.getFXML(
            COORDINATOR_LIST_POPUP);

        parentAssignCoordinatorControllerPair.getValue().setEventModel(this.eventModel);
        ViewManager.INSTANCE.showPopup(COORDINATOR_LIST_POPUP, "Assign coordinator");

    }

    public void goBackToEvents() {
        ViewManager.INSTANCE.switchDashboard(EVENTS_DASHBOARD, "Events Dashboard");
    }
}
