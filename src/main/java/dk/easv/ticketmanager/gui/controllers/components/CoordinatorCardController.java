package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.CoordinatorListPopupController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_LIST_POPUP;
import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_DETAILS_POPUP;

public class CoordinatorCardController {
    private final EventDataModel eventDataModel = new EventDataModel();
    private final FXMLManager fxmlManager = FXMLManager.getInstance();

    private User user;
    private Event event;

    @FXML
    private HBox hboxContainer;

    @FXML
    private Label lblCoordinatorFirstName;

    @FXML
    private Label lblCoordinatorLastName;

    @FXML
    private Button btnAssignButton;

    public void setUser(User user) {
        this.user = user;
        lblCoordinatorFirstName.setText(user.getFirst_name());
        lblCoordinatorLastName.setText(user.getLast_name());
    }

    @FXML
    private void assign(ActionEvent event) {
        Button btnAssign = (Button) event.getSource();
        if (btnAssign.getStyleClass().contains("inactive")) {
            eventDataModel.assignCoordinatorToEvent(this.event, user);
            btnAssign.getStyleClass().remove("inactive");
            btnAssign.getStyleClass().add("active");
            btnAssign.setText("Assigned");
        } else {
            eventDataModel.dissociateEventFromCoordinator(this.event, user);
            btnAssign.getStyleClass().remove("active");
            btnAssign.getStyleClass().add("inactive");
            btnAssign.setText("Assign");
        }
    }


    public void setEvent(Event event) {
        this.event = event;
    }

    public void setButtonToActive() {
        btnAssignButton.setText("Assigned");
        btnAssignButton.getStyleClass().remove("inactive");
        btnAssignButton.getStyleClass().add("active");
    }

    public void setDeletionButton() {
        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("delete");
        deleteButton.getStyleClass().add("assign-btn");
        hboxContainer.getChildren().remove(btnAssignButton);
        hboxContainer.getChildren().add(deleteButton);

        deleteButton.setOnMouseClicked(event -> {
            Pair<Parent, CoordinatorListPopupController> parent = fxmlManager.getFXML(COORDINATOR_LIST_POPUP);
            if (parent != null && parent.getValue() != null) {
                parent.getValue().getFlowPaneCoordinatorContainer().getChildren().remove(hboxContainer);
            }
            Thread dbThread = new Thread(() -> {
                try {
                    eventDataModel.dissociateEventFromCoordinator(this.event, user);
                    Platform.runLater(() -> {
                        if (this.event.getCoordinators() != null) {
                            this.event.getAssignedCoordinators().remove(user);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            dbThread.start();
        });
    }
}
