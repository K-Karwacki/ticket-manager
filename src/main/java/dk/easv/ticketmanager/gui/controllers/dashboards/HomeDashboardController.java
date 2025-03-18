package dk.easv.ticketmanager.gui.controllers.dashboards;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.io.IOException;

public class HomeDashboardController {

    @FXML
    public void addNewCoordinator() throws IOException {
        // Initialize the dialog stage
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add New User");

        // Set up the main layout
        VBox dialogLayout = new VBox(15);
        dialogLayout.setPadding(new Insets(20));
        dialogLayout.setAlignment(Pos.CENTER);

        // Role selection component
        Label roleLabel = new Label("Select Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Admin", "Coordinator");
        roleComboBox.setValue("Coordinator");

        // Set up the grid for input fields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Input fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Enter First Name");

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Enter Last Name");

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter Address");

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter Phone Number");

        Label profilePictureLabel = new Label("Profile Picture:");
        Button browseButton = new Button("Browse...");
        ImageView profilePictureView = new ImageView();
        profilePictureView.setFitWidth(100);
        profilePictureView.setFitHeight(100);
        profilePictureView.setPreserveRatio(true);

        // Apply CSS styles to the Browse button
        browseButton.setStyle("-fx-border-radius: 5px; -fx-border-color: #999; -fx-background-color: #ddd;");

        browseButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(dialogStage);
            if (selectedFile != null) {
                try {
                    Image image = new Image(selectedFile.toURI().toString());
                    profilePictureView.setImage(image);
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load the image.");
                    alert.showAndWait();
                }
            }
        });

        // Add fields to grid
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(addressLabel, 0, 2);
        gridPane.add(addressField, 1, 2);
        gridPane.add(phoneLabel, 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(profilePictureLabel, 0, 4);
        gridPane.add(browseButton, 1, 4);
        gridPane.add(profilePictureView, 1, 5);

        // Action buttons
        Button submitButton = new Button("Add User");
        Button cancelButton = new Button("Cancel");

        // Apply CSS styles to the Submit and Cancel buttons
        submitButton.setStyle("-fx-border-radius: 5px; -fx-border-color: #999; -fx-background-color: #ddd;");
        cancelButton.setStyle("-fx-border-radius: 5px; -fx-border-color: #999; -fx-background-color: #ddd;");

        // Result label for feedback
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-text-fill: green;");

        // Assemble the dialog layout
        dialogLayout.getChildren().addAll(roleLabel, roleComboBox, gridPane, submitButton, cancelButton, resultLabel);

        // Trigger role selection initialization (though no dynamic update is implemented here)
        roleComboBox.fireEvent(new javafx.event.ActionEvent());

        // Button actions
        cancelButton.setOnAction(e -> dialogStage.close());

        submitButton.setOnAction(e -> {
            // Collect input data
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();

            // Validate required fields
            if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                resultLabel.setStyle("-fx-text-fill: red;");
                resultLabel.setText("Please fill in all fields.");
                return;
            }

            // Validate phone number format
            if (!phone.matches("\\d{8,12}")) {
                resultLabel.setStyle("-fx-text-fill: red;");
                resultLabel.setText("Phone number must be between 8 and 12 digits.");
                return;
            }

            // Simulate successful submission
            resultLabel.setStyle("-fx-text-fill: green;");
            resultLabel.setText("User added successfully!");

            // Close dialog after a delay
            new Thread(() -> {
                try {
                    Thread.sleep(3000); // Display success message for 3 seconds
                    dialogStage.close();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });

        // Set up and display the scene
        Scene dialogScene = new Scene(dialogLayout, 500, 500);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
}