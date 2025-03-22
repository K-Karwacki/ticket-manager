package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_COMPONENT;

public class TicketController {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private Ticket ticket;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblEventTime;
    @FXML
    private Label lblEventDate;
    @FXML
    private Label lblEventPrice;
    @FXML
    private Label lblEventAddress;
    @FXML
    private Label lblTicketType;
    @FXML
    private ImageView imgQR;
    @FXML
    private ImageView imgBarcode;

    public void setTicketDetails(Ticket ticket) {
        this.ticket = ticket;
        lblEventName.setText(ticket.getEvent().getName());
        lblEventTime.setText(ticket.getEvent().getTime().toString());
        lblEventDate.setText(ticket.getEvent().getDate().toString());
        lblEventPrice.setText(String.valueOf(ticket.getPrice()) + " DKK");
        lblTicketType.setText(ticket.getType());
        lblEventAddress.setText(String.valueOf(ticket.getEvent().getLocation()));
    }
    public void displayTicket(Ticket ticket){
        Pair<Parent, TicketController> p = fxmlManager.loadFXML(TICKET_COMPONENT);
        p.getValue().setTicketDetails(ticket);
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
}
