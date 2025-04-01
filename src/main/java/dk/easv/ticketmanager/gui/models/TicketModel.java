package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Ticket;
import javafx.beans.property.SimpleStringProperty;

public class TicketModel {
    private final long ID;
    private final SimpleStringProperty price = new SimpleStringProperty();
    private final SimpleStringProperty customerFirstName = new SimpleStringProperty();
    private final SimpleStringProperty customerLastName = new SimpleStringProperty();
    private final SimpleStringProperty customerEmail = new SimpleStringProperty();
    private EventModel eventModel;

    public TicketModel(Ticket ticket) {
        this.ID = ticket.getId();
        this.price.set(String.valueOf(ticket.getPrice()));
        this.customerFirstName.set(ticket.getCustomer().getFirstName());
        this.customerLastName.set(ticket.getCustomer().getLastName());
        this.customerEmail.set(ticket.getCustomer().getEmail());
        this.eventModel = new EventModel(ticket.getEvent());
    }

}
