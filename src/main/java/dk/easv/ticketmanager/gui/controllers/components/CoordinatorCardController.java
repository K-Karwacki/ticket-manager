package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.CoordinatorListPopupController;
import dk.easv.ticketmanager.gui.models.EventDataModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_LIST_POPUP;

public class CoordinatorCardController implements Initializable
{
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

    @FXML
    private Button btnDeleteButton;


    @FXML
    private void assign(ActionEvent event) {
        eventDataModel.assignCoordinatorToEvent(this.event, this.user);
    }

    @FXML
    private void unassign(ActionEvent event){
        System.out.println("DELETE");
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
            Pair<Parent, CoordinatorListPopupController> parent = fxmlManager.loadFXML(COORDINATOR_LIST_POPUP);
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


    public void setDependencies(User user, Event event){
        this.user = user;
        this.event = event;
        lblCoordinatorFirstName.setText(user.getFirst_name());
        lblCoordinatorLastName.setText(user.getLast_name());

        if(user.getCoordinatedEvents().contains(event)){
            hboxContainer.getChildren().remove(btnAssignButton);
            if(!hboxContainer.getChildren().contains(btnDeleteButton)){
                hboxContainer.getChildren().add(btnDeleteButton);
            }
        }else{
            hboxContainer.getChildren().remove(btnDeleteButton);
            if(!hboxContainer.getChildren().contains(btnAssignButton)){
                hboxContainer.getChildren().add(btnAssignButton);
            }
        }
    }

    @Override public void initialize(URL location, ResourceBundle resources)
    {
//        hboxContainer.getChildren().remove(btnDeleteButton);
//        hboxContainer.getChildren().remove(btnAssignButton);
    }
}
