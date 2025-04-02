package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.gui.models.EventModel;

import java.util.List;

public interface TicketManagmentService {
    void addTicketType(TicketType ticketType);
    void addTicket(Ticket ticket);
    List<TicketType> getTicketTypesForEvent(EventModel eventModel);
}
