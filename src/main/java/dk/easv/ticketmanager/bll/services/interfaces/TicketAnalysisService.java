package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.dal.entities.Ticket;
//import dk.easv.ticketmanager.dal.entities.TicketType;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketAnalysisService
{
//    void addTicketType(TicketType ticketType);
    void addTicket(Ticket ticket);
//    List<TicketType> getTicketTypesForEvent(EventModel eventModel);
    List<LocalDateTime> getAllDatesForPurchasedTickets();
    Image generateBarcode(String text);
    Image generateQRCode(String text);
    String generateTicketNumber();
}
