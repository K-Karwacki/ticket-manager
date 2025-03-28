package dk.easv.ticketmanager.gui.controllers.user;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class CoordinatorListPopupController
{
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private Event event;

    @FXML
    private FlowPane flowPaneCoordinatorContainer;


    public void displayNotAssignedCoordinatorsToEventList(){
        flowPaneCoordinatorContainer.getChildren().clear();



        Stage stage = new Stage();
        stage.setTitle("Coordinators List");
        stage.setScene(new Scene(flowPaneCoordinatorContainer));
        stage.show();
    }

    public void displayAssignedCoordinatorsToTheEventList(){
        flowPaneCoordinatorContainer.getChildren().clear();
//        event.getAssignedCoordinators().forEach(assignedCoordinator -> {
//            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
//            p.getValue().setDependencies(assignedCoordinator, event);
////            p.getValue().setUser(assignedCoordinator);
////            p.getValue().setEvent(event);
//            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
//        });
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
