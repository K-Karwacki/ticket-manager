package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.SPECIAL_TICKET_TYPE_CREATOR_POPUP;

public class SpecialTicketGeneratorPopupController {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();

    @FXML
    public void load() {
        Pair<Parent, SpecialTicketGeneratorPopupController> p =
                fxmlManager.loadFXML(SPECIAL_TICKET_TYPE_CREATOR_POPUP);
        Stage stage = new Stage();
        stage.setTitle("Ticket Creator");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
}