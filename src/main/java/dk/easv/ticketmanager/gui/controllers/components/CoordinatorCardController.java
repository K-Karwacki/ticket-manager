package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CoordinatorCardController {
    private User user;

    @FXML
    private Label lblCoordinatorFirstName;

    @FXML
    private Label lblCoordinatorLastName;

    public void setUser(User user) {
        this.user = user;
        lblCoordinatorFirstName.setText(user.getFirst_name());
        lblCoordinatorLastName.setText(user.getLast_name());
    }
}
