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
import javafx.scene.layout.FlowPane;
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


    private void addAssignedUsers(List<User> users){
        users.forEach(user -> {
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setUser(user);
            p.getValue().setEvent(event);
            p.getValue().setButtonToActive();
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
    }
    private void addUnassignedUsers(List<User> users) {
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
        List<User> assignedUsers = event.getAssignedCoordinators();
        addAssignedUsers(assignedUsers);

    }

    public void showAll(){
        Pair<Parent, EventDetailsPopupController> parent = fxmlManager.getFXML(EVENT_DETAILS_POPUP);
        event = parent.getValue().getEvent();
        List<User> assignedUsers = event.getAssignedCoordinators();
        List<User> unassignedUsers = userDataModel.getUsers();
        unassignedUsers.removeAll(assignedUsers);
        addAssignedUsers(assignedUsers);
        addUnassignedUsers(unassignedUsers);
    }
}
