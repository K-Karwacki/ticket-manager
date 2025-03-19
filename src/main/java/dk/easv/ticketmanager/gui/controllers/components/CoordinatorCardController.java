package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.EventService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CoordinatorCardController {
    private final EventService eventService = new EventService();

    private User user;
    private Event event;
    private String state;

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

    @FXML private void assign(ActionEvent event) {
        Button btnAssign = (Button) event.getSource();
        if(btnAssign.getStyleClass().contains("inactive")){
            btnAssign.getStyleClass().remove("inactive");
            btnAssign.getStyleClass().add("active");
            btnAssign.setText("Assigned");
            eventService.assignCoordinatorToEvent(this.event, user);
        }
        else{
            btnAssign.getStyleClass().remove("active");
            btnAssign.getStyleClass().add("inactive");
            btnAssign.setText("Assign");
            eventService.dissociateEventFromCoordinator(this.event, user);
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
}
