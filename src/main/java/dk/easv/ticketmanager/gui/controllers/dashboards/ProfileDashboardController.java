package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.popups.ProfileSettingPopupController;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.PROFILE_SETTINGS_POPUP;


public class ProfileDashboardController implements Initializable
{
    @FXML
    private Circle profileImage;
    @FXML
    private Label profileFullName;
    @FXML
    private Label profileEmail;
    @FXML
    private Label profilePhoneNumber;
    @FXML
    private Button editProfileButton;
    private final FXMLManager fxmlManager= FXMLManager.getInstance();

   User user = UserSession.getInstance().getUser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ImagePattern img = new ImagePattern(user.getUserImage());
//        profileImage.setFill(img);
        profileFullName.setText(user.getFirst_name() + " " + user.getLast_name());
        profileEmail.setText(user.getEmail());
        profilePhoneNumber.setText(user.getPhoneNumber());
    }

    public void editUserInformation(ActionEvent actionEvent) {
        Pair<Parent, ProfileSettingPopupController> p = fxmlManager.loadFXML(PROFILE_SETTINGS_POPUP);
        Stage stage = new  Stage();
        stage.setTitle("Edit Profile");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
}
