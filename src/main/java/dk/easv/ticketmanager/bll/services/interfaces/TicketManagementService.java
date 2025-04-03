package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketManagementService {
    void addTicketType(TicketType ticketType);
    void addTicket(Ticket ticket);
    List<TicketType> getTicketTypesForEvent(EventModel eventModel);
    List<LocalDateTime> getAllDatesForPurchasedTickets();
    Image generateBarcode(String text);
    Image generateQRCode(String text);
    String generateTicketNumber();
}
