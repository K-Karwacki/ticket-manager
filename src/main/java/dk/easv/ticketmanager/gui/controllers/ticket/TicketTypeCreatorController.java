package dk.easv.ticketmanager.gui.controllers.ticket;


import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagementService;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TicketTypeCreatorController {
    private static TicketManagementService ticketManagementService;
    private static EventManagementService eventManagementService;

    private EventModel eventModel;

    @FXML
    private TextField txtFieldTicketTypeName;

    @FXML
    private TextField txtFieldTicketPrice;

    @FXML
    private void addTicketType(){
        System.out.println(this);
        String ticketTypeName = txtFieldTicketTypeName.getText();
        String ticketPrice = txtFieldTicketPrice.getText();
        TicketType ticketType = new TicketType();
        ticketType.setEvent(eventManagementService.getEventById(eventModel.getID()).get());
        ticketType.setPrice(Double.parseDouble(ticketPrice));
        ticketType.setName(ticketTypeName);
        ticketManagementService.addTicketType(ticketType);
        Stage stage = (Stage) txtFieldTicketPrice.getScene().getWindow();
        stage.close();
    }
    public void setEvent(EventModel eventModel) {
        this.eventModel = eventModel;
    }


    public void setDatabaseService(TicketManagementService ticketManagementService, EventManagementService eventManagementService) {
        this.ticketManagementService = ticketManagementService;
        this.eventManagementService = eventManagementService;
    }
}
