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
import java.util.stream.Collectors;

import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_CARD_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_DETAILS_POPUP;

public class CoordinatorListPopupController {
    private final UserDataModel userDataModel = new UserDataModel();
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private Event event;

    @FXML
    private FlowPane flowPaneCoordinatorContainer;


    public void displayNotAssignedCoordinatorsToEventList(){
        flowPaneCoordinatorContainer.getChildren().clear();
        List<User> notAssignedCoordinators = userDataModel.getAllCoordinators().stream()
            .filter(user -> !user.getCoordinatedEvents().contains(event))
            .collect(Collectors.toList());


        notAssignedCoordinators.forEach(coordinator->{
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setDependencies(coordinator, event);
            //            p.getValue().setUser(coordinator);
            //            p.getValue().setEvent(event);
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
            p.getValue().setDependencies(assignedCoordinator, event);
//            p.getValue().setUser(assignedCoordinator);
//            p.getValue().setEvent(event);
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
        Stage stage = new Stage();
        stage.setTitle("Coordinators List");
        stage.setScene(new Scene(flowPaneCoordinatorContainer));
        stage.show();
    }

    public FlowPane getFlowPaneCoordinatorContainer() {
        return flowPaneCoordinatorContainer;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }
}
