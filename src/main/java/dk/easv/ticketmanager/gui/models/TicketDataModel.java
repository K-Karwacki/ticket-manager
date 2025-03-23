package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.bll.TicketService;
import javafx.scene.image.Image;

public class TicketDataModel {
    private final TicketService ticketService = new TicketService();
    public void loadTickets(){
    }
    public Image generateBarcode(String text){
        return ticketService.generateBarcode(text);
    }
    public Image generateQRCode(String text){
        return ticketService.generateQRCode(text);
    }
    public String generateTicketNumber(){
        return ticketService.generateTicketNumber();
    }

    public void addTicket(Ticket ticket) {
        ticketService.addTicket(ticket);
    }
}
