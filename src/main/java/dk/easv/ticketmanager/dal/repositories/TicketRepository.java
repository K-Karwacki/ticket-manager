package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.dal.entities.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TicketRepository extends BaseRepository<Ticket>{
    Set<Ticket> getTicketsForEventID(long ID);
    Set<Ticket> getGeneratedTicketsForEventID(long ID);
    boolean saveTicketForEventID(Ticket ticket, long ID);

    boolean generateTicketForCustomer(Ticket ticket, Customer customer);
    List<Ticket> getGeneratedTicketsForCustomerEmail(String email);

    List<LocalDateTime> getAllDatesForPurchasedTickets();
    void saveGeneratedTicket(GeneratedTicket generatedTicket);
}
