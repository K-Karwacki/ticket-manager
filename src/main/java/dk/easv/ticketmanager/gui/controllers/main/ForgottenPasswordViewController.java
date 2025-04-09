package dk.easv.ticketmanager.gui.controllers.main;

import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Optional;

public class ForgottenPasswordViewController {
    @FXML
    private TextField txtFieldEmail;

    private UserManagementService userManagementService;


    @FXML
    private void onClickShowLogin(ActionEvent actionEvent) {
        ViewManager.INSTANCE.showStage(FXMLPath.LOGIN, "Login", false);
    }

    @FXML
    private void onClickSendEmail() throws IOException {
        String email = txtFieldEmail.getText();
        Optional<User> optionalUser = userManagementService.getUserByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userManagementService.sendTemporaryPassword(user);
        }

    }

    public void setServices(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }
}
