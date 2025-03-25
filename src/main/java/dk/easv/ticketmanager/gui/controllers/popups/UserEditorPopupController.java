package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.dal.implementations.UserRepository;
import dk.easv.ticketmanager.be.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class UserEditorPopupController {

    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField textFieldPassword;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private Label errorLabel;
    @FXML
    private Button saveEditedUser;

    private User user;
    private final UserRepository userRepository = new UserRepository();
    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            textFieldFirstName.setText(user.getFirstName());
            textFieldLastName.setText(user.getLastName());
            textFieldEmail.setText(user.getEmail());
            textFieldPhone.setText(user.getPhoneNumber());
        }
    }

    @FXML
    public void onSave(ActionEvent event) {
        if (user == null) {
            errorLabel.setText("Error: No user selected!");
            return;
        }

        if (textFieldFirstName.getText().isEmpty() ||
                textFieldLastName.getText().isEmpty() ||
                textFieldEmail.getText().isEmpty() ||
                textFieldPhone.getText().isEmpty()) {
            errorLabel.setText("Please fill in all required fields.");
            return;
        }

        user.setFirstName(textFieldFirstName.getText());
        user.setLastName(textFieldLastName.getText());
        user.setEmail(textFieldEmail.getText());
        user.setPhoneNumber(textFieldPhone.getText());

        if (!textFieldPassword.getText().isEmpty()) {
            user.setPassword(textFieldPassword.getText());
        }

        try {
            userRepository.edit(user);

            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    javafx.application.Platform.runLater(() -> {
                        Stage stage = (Stage) saveEditedUser.getScene().getWindow();
                        stage.close();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
