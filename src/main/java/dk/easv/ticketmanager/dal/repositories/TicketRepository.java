package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface TicketRepository extends BaseRepository<Ticket>{
    boolean saveTicketType(TicketType ticketType);
    boolean deleteTicketType(TicketType ticketType);
    List<TicketType> getAllTicketTypesForTicket();
    List<LocalDateTime> getAllDatesForPurchasedTickets();
}
