package dk.easv.ticketmanager.gui.controllers.event.popups;


import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.bll.services.implementations.DatabaseServiceImpl;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_TYPE_CREATOR_POPUP;


public class TicketTypeCreatorPopupController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private DatabaseService databaseService;

    private EventModel eventModel;

    @FXML
    private TextField txtFieldTicketTypeName;

    @FXML
    private TextField txtFieldTicketPrice;

    @FXML
    private void addTicketType(){
        String ticketTypeName = txtFieldTicketTypeName.getText();
        String ticketPrice = txtFieldTicketPrice.getText();
        TicketType ticketType = new TicketType();
        ticketType.setEvent(databaseService.getEventById(eventModel.getID()));
        ticketType.setPrice(Double.parseDouble(ticketPrice));
        ticketType.setName(ticketTypeName);
        databaseService.addTicketType(ticketType);
        Stage stage = (Stage) txtFieldTicketPrice.getScene().getWindow();
        stage.close();
    }
    public void setEvent(EventModel eventModel) {
        this.eventModel = eventModel;
    }


    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
