package dk.easv.ticketmanager.gui.controllers.event.popups;


import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;


public class TicketTypeCreatorPopupController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;

    private Event event;

    @FXML
    private TextField txtFieldTicketTypeName;

    @FXML
    private TextField txtFieldTicketPrice;

    @FXML
    private void addTicketType(){
        String ticketTypeName = txtFieldTicketTypeName.getText();
        String ticketPrice = txtFieldTicketPrice.getText();
        TicketType ticketType = new TicketType();
        ticketType.setEvent(event);
        ticketType.setPrice(Double.parseDouble(ticketPrice));
        ticketType.setName(ticketTypeName);
        ticketDataModel.addTicketType(ticketType);
        Stage stage = (Stage) txtFieldTicketPrice.getScene().getWindow();
        stage.close();
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public void load(Event event) {
        Pair<Parent, TicketTypeCreatorPopupController> p = fxmlManager.loadFXML(TICKET_TYPE_CREATOR_POPUP);
        p.getValue().setEvent(event);
        Stage stage = new Stage();
        stage.setTitle("Ticket creator");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
}
