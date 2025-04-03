package dk.easv.ticketmanager.gui.controllers.user;

import dk.easv.ticketmanager.gui.FXMLManager;
//import dk.easv.ticketmanager.gui.models.EventDataModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CoordinatorCardController
{
//    private final EventDataModel eventDataModel = new EventDataModel();
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private UserModel userModel;
    private EventModel eventModel;

    @FXML
    private HBox hboxContainer;

    @FXML
    private Label lblCoordinatorFirstName;

    @FXML
    private Label lblCoordinatorLastName;

    @FXML
    private Button btnAssignButton;

    @FXML
    private Button btnDeleteButton;


    @FXML
    private void assign(ActionEvent event) {
//        eventDataModel.assignCoordinatorToEvent(this.event, this.user);
        eventModel.getAssignedCoordinators().add(this.userModel);
        setButtonVisibility();
    }

    @FXML
    private void unassign(ActionEvent event){
        eventModel.getAssignedCoordinators().remove(this.userModel);
        setButtonVisibility();
    }



//    public void setDeletionButton() {
//        Button deleteButton = new Button("Delete");
//        deleteButton.getStyleClass().add("delete");
//        deleteButton.getStyleClass().add("assign-btn");
//        hboxContainer.getChildren().remove(btnAssignButton);
//        hboxContainer.getChildren().add(deleteButton);
//
//        deleteButton.setOnMouseClicked(event -> {
//            Pair<Parent, CoordinatorAssignController> parent = fxmlManager.getFXML(COORDINATOR_LIST_POPUP);
//            if (parent != null && parent.getValue() != null) {
////                parent.getValue().getFlowPaneCoordinatorContainer().getChildren().remove(hboxContainer);
//            }
//            Thread dbThread = new Thread(() -> {
//                try {
////                    eventDataModel.dissociateEventFromCoordinator(this.event, user);
//                    Platform.runLater(() -> {
//                        if (this.event.getCoordinators() != null) {
////                            this.event.getAssignedCoordinators().remove(user);
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//            dbThread.start();
//        });
//    }

    private void setButtonVisibility() {
        if (eventModel.getAssignedCoordinators().contains(this.userModel)) {
            btnAssignButton.setVisible(false);
            btnAssignButton.setManaged(false);
            btnDeleteButton.setVisible(true);
            btnDeleteButton.setManaged(true);
        } else {
            btnAssignButton.setVisible(true);
            btnAssignButton.setManaged(true);
            btnDeleteButton.setVisible(false);
            btnDeleteButton.setManaged(false);
        }
    }

    public void setModel(UserModel userModel, EventModel eventModel)
    {
        this.userModel = userModel;
        this.eventModel = eventModel;

       setButtonVisibility();
    }
}
