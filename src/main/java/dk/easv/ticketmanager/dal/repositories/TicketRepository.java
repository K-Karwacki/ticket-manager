package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.*;

import java.util.List;

public interface TicketRepository extends BaseRepository<Ticket>{
    void addTicketType(TicketType ticketType);
    void deleteTicketType(TicketType ticketType);
    List<TicketType> getAllTicketTypesForTicket();
}
