package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.UserService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_DETAILS_POPUP;

public class CoordinatorListPopupController {
    private final UserDataModel userDataModel = new UserDataModel();
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private Event event;

    @FXML
    private FlowPane flowPaneCoordinatorContainer;


    public void displayAllCoordinatorsList(){
        flowPaneCoordinatorContainer.getChildren().clear();
        userDataModel.getAllCoordinators().forEach(coordinator->{
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setUser(coordinator);
            p.getValue().setEvent(event);
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });

        Stage stage = new Stage();
        stage.setTitle("Coordinators List");
        stage.setScene(new Scene(flowPaneCoordinatorContainer));
        stage.show();
    }

    public void displayAssignedCoordinatorsToTheEventList(){
        flowPaneCoordinatorContainer.getChildren().clear();
        event.getAssignedCoordinators().forEach(assignedCoordinator -> {
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setUser(assignedCoordinator);
            p.getValue().setEvent(event);
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
        Stage stage = new Stage();
        stage.setTitle("Coordinators List");
        stage.setScene(new Scene(flowPaneCoordinatorContainer));
        stage.show();
    }





    private void addAssignedUsers(Set<User> users, boolean deleteMode){
        if(!deleteMode){
        users.forEach(user -> {
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setUser(user);
            p.getValue().setEvent(event);
            p.getValue().setButtonToActive();
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });}
        else{
            users.forEach(user -> {
                Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
                p.getValue().setUser(user);
                p.getValue().setEvent(event);
                p.getValue().setDeletionButton();
                flowPaneCoordinatorContainer.getChildren().add(p.getKey());
            });
        }
    }
    private void addUnassignedUsers(Set<User> users) {
        users.forEach(user -> {
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setUser(user);
            p.getValue().setEvent(event);
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
    }

    public void showAssignedUsers() {
        Pair<Parent, EventDetailsPopupController> parent = fxmlManager.getFXML(EVENT_DETAILS_POPUP);
        event = parent.getValue().getEvent();
        Set<User> assignedUsers = event.getAssignedCoordinators();
        System.out.println(assignedUsers);
        addAssignedUsers(assignedUsers, true);

    }

    public void showAll() {
        flowPaneCoordinatorContainer.getChildren().clear();
        Pair<Parent, EventDetailsPopupController> parent = fxmlManager.getFXML(EVENT_DETAILS_POPUP);
        event = parent.getValue().getEvent();
        Set<User> assignedUsers = event.getAssignedCoordinators();
        Set<User> allUsers = new HashSet<>(userDataModel.getUsers());
        Set<User> unassignedUsers = new HashSet<>(allUsers);
        unassignedUsers.removeAll(assignedUsers);

        addAssignedUsers(assignedUsers, false);
        addUnassignedUsers(unassignedUsers);
    }

    public FlowPane getFlowPaneCoordinatorContainer() {
        return flowPaneCoordinatorContainer;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }
}
