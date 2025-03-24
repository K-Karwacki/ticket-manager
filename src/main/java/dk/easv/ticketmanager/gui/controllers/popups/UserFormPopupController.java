package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.AuthenticationService;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UserFormPopupController implements Initializable {
    @FXML private ComboBox<Role> roleComboBox;
    @FXML private TextField firstNameField, lastNameField, phoneField, emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button browseButton, submitButton, cancelButton;
    @FXML private ImageView profilePictureView;
    @FXML private Label resultLabel;

    private final UserDataModel userDataModel = new UserDataModel();
    private final AuthenticationService authenticationService = AuthenticationService.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll(userDataModel.getRoles());

        browseButton.setOnAction(e -> handleBrowseImage());
        submitButton.setOnAction(e -> handleSubmit());
        cancelButton.setOnAction(e -> closePopup());
    }

    private void handleBrowseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePictureView.setImage(image);
        }
    }

    private void handleSubmit() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        Role role = roleComboBox.getValue();
        String password = passwordField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            resultLabel.setText("Please fill in all fields.");
            return;
        }

        if (!phone.matches("\\d{8,12}")) {
            resultLabel.setText("Phone number must be between 8 and 12 digits.");
            return;
        }

        password = authenticationService.hashPassword(password);
        User user = new User(firstName, lastName, email, password, phone, "default.jpg", role);

        try {
            userDataModel.addNewUser(user);
            resultLabel.setText("User successfully added.");
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    private void closePopup() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
