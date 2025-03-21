package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TicketController {
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
        lblEventName.setText(ticket.getEvent().getName());
        lblEventTime.setText(ticket.getEvent().getTime().toString());
        lblEventDate.setText(ticket.getEvent().getDate().toString());
        lblEventPrice.setText(String.valueOf(ticket.getPrice()));
        lblTicketType.setText(ticket.getType());
        lblEventAddress.setText(String.valueOf(ticket.getEvent().getLocation()));
    }
}
