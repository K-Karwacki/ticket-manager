package dk.easv.ticketmanager.gui.controllers.dashboards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserListDashboardController implements Initializable
{
  @FXML
  private ListView<String> usersListView;
  private List<String> users = new ArrayList<>();

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    users.forEach(user->{
      usersListView.getItems().add(user);
    });
  }

  @FXML
  public void addNewCoordinator() throws IOException {
    Stage dialogStage = new Stage();
    dialogStage.setTitle("Add New User");

    VBox dialogLayout = new VBox(15);
    dialogLayout.setPadding(new Insets(20));
    dialogLayout.setAlignment(Pos.CENTER);

    Label roleLabel = new Label("Select Role:");
    ComboBox<String> roleComboBox = new ComboBox<>();
    roleComboBox.getItems().addAll("Admin", "Coordinator");
    roleComboBox.setValue("Coordinator");

    GridPane gridPane = new GridPane();
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setAlignment(Pos.CENTER);

    Label firstNameLabel = new Label("First Name:");
    TextField firstNameField = new TextField();
    firstNameField.setPromptText("Enter First Name");

    Label lastNameLabel = new Label("Last Name:");
    TextField lastNameField = new TextField();
    lastNameField.setPromptText("Enter Last Name");

    Label phoneLabel = new Label("Phone Number:");
    TextField phoneField = new TextField();
    phoneField.setPromptText("Enter Phone Number");

    Label emailLabel = new Label("Email:");
    TextField emailField = new TextField();
    emailField.setPromptText("Enter email");

    Label passwordLabel = new Label("Password:");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Enter Password");



    Label profilePictureLabel = new Label("Profile Picture:");
    Button browseButton = new Button("Browse...");
    ImageView profilePictureView = new ImageView();
    profilePictureView.setFitWidth(100);
    profilePictureView.setFitHeight(100);
    profilePictureView.setPreserveRatio(true);


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

    gridPane.add(firstNameLabel, 0, 0);
    gridPane.add(firstNameField, 1, 0);
    gridPane.add(lastNameLabel, 0, 1);
    gridPane.add(lastNameField, 1, 1);
    gridPane.add(phoneLabel, 0, 2);
    gridPane.add(phoneField, 1, 2);
    gridPane.add(emailLabel, 0, 3);
    gridPane.add(emailField, 1, 3);
    gridPane.add(passwordLabel, 0, 4);
    gridPane.add(passwordField, 1, 4);


    gridPane.add(profilePictureLabel, 0, 5);
    gridPane.add(browseButton, 1, 5);
    gridPane.add(profilePictureView, 1, 6);

    Button submitButton = new Button("Add User");
    Button cancelButton = new Button("Cancel");

    submitButton.setStyle("-fx-border-radius: 5px; -fx-border-color: #999; -fx-background-color: #ddd;");
    cancelButton.setStyle("-fx-border-radius: 5px; -fx-border-color: #999; -fx-background-color: #ddd;");

    Label resultLabel = new Label();
    resultLabel.setStyle("-fx-text-fill: green;");

    dialogLayout.getChildren().addAll(roleLabel, roleComboBox, gridPane, submitButton, cancelButton, resultLabel);

    roleComboBox.fireEvent(new javafx.event.ActionEvent());

    cancelButton.setOnAction(e -> dialogStage.close());

    submitButton.setOnAction(e -> {
      String firstName = firstNameField.getText().trim();
      String lastName = lastNameField.getText().trim();
      String phone = phoneField.getText().trim();
      String email = emailField.getText().trim();
      String role = roleComboBox.getValue();
      String password = passwordField.getText();


      if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
        resultLabel.setStyle("-fx-text-fill: red;");
        resultLabel.setText("Please fill in all fields.");
        return;
      }

      if (!phone.matches("\\d{8,12}")) {
        resultLabel.setStyle("-fx-text-fill: red;");
        resultLabel.setText("Phone number must be between 8 and 12 digits.");
        return;
      }

      String newUser = role + ": " + firstName + " " + lastName;
      usersListView.getItems().add(newUser);


      resultLabel.setStyle("-fx-text-fill: green;");
      resultLabel.setText("User added successfully!");


      new Thread(() -> {
        try {
          Thread.sleep(1000);
          javafx.application.Platform.runLater(() -> {
            dialogStage.close();
          });
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }).start();
    });

    Scene dialogScene = new Scene(dialogLayout, 550, 550);
    dialogStage.setScene(dialogScene);
    dialogStage.showAndWait();
  }

}
