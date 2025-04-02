package dk.easv.ticketmanager.gui.controllers.ticket;


import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class TicketTypeCreatorController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private static DatabaseService databaseService;

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
