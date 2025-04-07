package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.dal.entities.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TicketRepository extends BaseRepository<Ticket>{
    Set<Ticket> getTicketsForEventID(long ID);
    Set<Ticket> getGeneratedTicketsForEventID(long ID);
    boolean saveTicketForEventID(Ticket ticket, long ID);

//    boolean saveTicketType(TicketType ticketType);
//    boolean deleteTicketType(TicketType ticketType);
//    List<TicketType> getAllTicketTypesForTicket();
    List<LocalDateTime> getAllDatesForPurchasedTickets();
}
