package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.TicketService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.List;
import java.util.stream.Collectors;

public class TicketDataModel {
    private final TicketService ticketService = new TicketService();
    private static final ObservableList<TicketType> ticketTypes = FXCollections.observableArrayList();
    public void load(){
        ticketTypes.setAll(ticketService.getAllTicketTypes());
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
    public void addTicketType(TicketType ticketType){
        ticketService.addTicketType(ticketType);
        load();
    }
    public ObservableList<TicketType> getTicketTypesForEvent(Event event){
        return ticketTypes.filtered(ticketType -> ticketType.getEvent().getId() == event.getId());
    }
}
