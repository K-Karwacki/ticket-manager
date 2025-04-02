package dk.easv.ticketmanager.gui.controllers.event.popups;


import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
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
    private EventManagementService eventManagementService;

    private EventModel eventModel;

    @FXML
    private TextField txtFieldTicketTypeName;

    @FXML
    private TextField txtFieldTicketPrice;

    @FXML
    private void addTicketType(){
        TicketType ticketType = new TicketType();
        ticketType.setPrice(Double.parseDouble(txtFieldTicketPrice.getText()));
        ticketType.setName(txtFieldTicketTypeName.getText());
        if(eventManagementService.addTicketTypeForEventByID(ticketType, eventModel.getID())){
            ViewManager.INSTANCE.hideCurrentStage();
        }

    }
    public void setEvent(EventModel eventModel) {
        this.eventModel = eventModel;
    }


    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
