package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.dal.repositories.BaseRepository;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.ObservableList;

import java.util.List;

public interface DatabaseService
{

    UserListModel getUserListModel();
    EventListModel getEventListModel();

    boolean createNewEvent(Event event);
    boolean updateEvent(Event event);
    boolean deleteEvent(Event event);
    void addTicketType(TicketType ticketType);
    Event getEventById(long ID);
    void addTicket(Ticket ticket);

    List<TicketType> getTicketTypesForEvent(Event event);
//    List<UserModel> getCoordinatorList();
}
