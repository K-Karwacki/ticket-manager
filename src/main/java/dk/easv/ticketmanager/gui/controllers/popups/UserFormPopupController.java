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
    @FXML private Button browseButton, submitButton;
    @FXML private ImageView profilePictureView;
    @FXML private Label resultLabel;

    private final UserDataModel userDataModel = new UserDataModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleComboBox.getItems().addAll(userDataModel.getRoles());

        browseButton.setOnAction(e -> handleBrowseImage());
        submitButton.setOnAction(e -> handleSubmit());
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

        resetFieldStyles();
        resultLabel.setText("");
        resetResultLabelColor();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || role == null) {
            resultLabel.setText("All fields need to be used.");
            resultLabel.setStyle("-fx-text-fill: red;");  // Set error text color to red
            if (firstName.isEmpty()) showError(firstNameField);
            if (lastName.isEmpty()) showError(lastNameField);
            if (email.isEmpty()) showError(emailField);
            if (phone.isEmpty()) showError(phoneField);
            if (password.isEmpty()) showError(passwordField);
            if (role == null) showError(roleComboBox);
            return;
        }

        if (!phone.matches("\\d{8,12}")) {
            showError(phoneField);
            resultLabel.setText("Phone number must be between 8 and 12 digits.");
            resultLabel.setStyle("-fx-text-fill: red;");  // Set error text color to red
            return;
        }
        User user = new User(firstName, lastName, email, password, phone, "default.jpg", role);

        try {
            userDataModel.addNewUser(user);
            resultLabel.setText("User successfully added.");
            resultLabel.setStyle("-fx-text-fill: green;");


            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    javafx.application.Platform.runLater(() -> {
                        Stage stage = (Stage) submitButton.getScene().getWindow();
                        stage.close();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
            resultLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void resetResultLabelColor() {
        resultLabel.setStyle("-fx-text-fill: black;");
    }

    private void showError(Control field) {
        if (field instanceof TextField) {
            ((TextField) field).setStyle("-fx-border-color: red;");
        } else if (field instanceof PasswordField) {
            ((PasswordField) field).setStyle("-fx-border-color: red;");
        } else if (field instanceof ComboBox) {
            ((ComboBox<?>) field).setStyle("-fx-border-color: red;");
        }
    }

    private void resetFieldStyles() {
        firstNameField.setStyle("-fx-border-color: transparent;");
        lastNameField.setStyle("-fx-border-color: transparent;");
        phoneField.setStyle("-fx-border-color: transparent;");
        emailField.setStyle("-fx-border-color: transparent;");
        passwordField.setStyle("-fx-border-color: transparent;");
        roleComboBox.setStyle("-fx-border-color: transparent;");
    }
}
