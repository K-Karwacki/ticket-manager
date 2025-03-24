package dk.easv.ticketmanager.dal.interfaces;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;

import java.util.List;

public interface ITicketRepository {
    void addCustomer(Customer customer);
    void addTicket(Ticket ticket);
    int getAmountOfTicketsForEvent(Event event, String type);
    void addTicketType(TicketType ticketType);
    List<TicketType> getAllTicketTypes();
}
