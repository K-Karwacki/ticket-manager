package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.bll.UserService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.COORDINATOR_CARD_COMPONENT;

public class CoordinatorListPopupController implements Initializable {
    private final UserDataModel userDataModel = new UserDataModel();
    private final FXMLManager fxmlManager = FXMLManager.getInstance();

    @FXML
    private FlowPane flowPaneCoordinatorContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userDataModel.getUsers().forEach(user -> {
            Pair<Parent, CoordinatorCardController> p = fxmlManager.loadFXML(COORDINATOR_CARD_COMPONENT);
            p.getValue().setUser(user);
            flowPaneCoordinatorContainer.getChildren().add(p.getKey());
        });
    }
}
