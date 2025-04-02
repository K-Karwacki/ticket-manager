package dk.easv.ticketmanager.bll.services.interfaces;

import com.google.zxing.common.BitMatrix;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.scene.image.Image;

import java.util.List;

public interface TicketManagmentService {
    void addTicketType(TicketType ticketType);
    void addTicket(Ticket ticket);
    List<TicketType> getTicketTypesForEvent(EventModel eventModel);
    Image generateBarcode(String text);
    Image generateQRCode(String text);
    String generateTicketNumber();
}
