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


    public void displayAllCoordinators() {
        flowPaneCoordinatorContainer.getChildren().clear();
        userDataModel.getCoordinators().forEach(coordinator -> {
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setCoordinator(coordinator);
            p.getValue().setEvent(event);
            p.getValue().setButton();
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
    }

    public void displayAssignedCoordinators() {
        flowPaneCoordinatorContainer.getChildren().clear();
        userDataModel.getCoordinators().forEach(coordinator -> {
                Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
                p.getValue().setEvent(event);
                p.getValue().setCoordinator(coordinator);
                p.getValue().setDeletionButton();
                flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
    }

        public FlowPane getFlowPaneCoordinatorContainer() {
        return flowPaneCoordinatorContainer;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void reloadEvent() {
        Pair<Parent, EventDetailsPopupController> p = fxmlManager.loadFXML(EVENT_DETAILS_POPUP);
        p.getValue().reloadEvent();

    }
}
