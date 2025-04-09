package dk.easv.ticketmanager.gui.controllers.user.popup;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.utils.FieldValidator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Objects;

public class UserFormController {
    protected static AuthorizationService authorizationService;
    protected static UserManagementService userManagementService;

    @FXML
    protected VBox formRoot;
    @FXML protected ComboBox<Role> roleComboBox;
    @FXML protected TextField firstNameField;
    @FXML protected TextField lastNameField;
    @FXML protected TextField phoneField;
    @FXML protected TextField emailField;
    @FXML protected PasswordField passwordField;
    @FXML protected Button submitButton;
    @FXML protected StackPane browseButton;
    @FXML protected Circle profilePictureView;
    @FXML protected Label resultLabel;


    public void setServices(UserManagementService userManagementService, AuthorizationService authorizationService){
        UserFormController.userManagementService = userManagementService;
        UserFormController.authorizationService = authorizationService;

    }

    @FXML
    protected void handleBrowseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePictureView.setFill(new ImagePattern(image));
        }
    }


    protected void resetResultLabelColor() {
        resultLabel.setStyle("-fx-text-fill: black;");
    }

    protected void showError(Control field) {
        if (field instanceof TextField) {
            ((TextField) field).setStyle("-fx-border-color: red;");
        } else if (field instanceof PasswordField) {
            ((PasswordField) field).setStyle("-fx-border-color: red;");
        } else if (field instanceof ComboBox) {
            ((ComboBox<?>) field).setStyle("-fx-border-color: red;");
        }
    }

    protected void setDefaultImage(){
        Image defaultImage = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/user-template.png")));
        profilePictureView.setFill(new ImagePattern(defaultImage));
    }

    protected void resetFields(){
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        passwordField.clear();
    }

    protected void resetFieldStyles() {
        firstNameField.setStyle("-fx-border-color: transparent;");
        lastNameField.setStyle("-fx-border-color: transparent;");
        phoneField.setStyle("-fx-border-color: transparent;");
        emailField.setStyle("-fx-border-color: transparent;");
        passwordField.setStyle("-fx-border-color: transparent;");
        roleComboBox.setStyle("-fx-border-color: transparent;");
    }
}
