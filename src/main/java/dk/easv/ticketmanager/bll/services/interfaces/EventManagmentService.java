package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.models.EventListModel;

import java.util.Optional;

public interface EventManagmentService {
    EventListModel getEventListModel();
    boolean createNewEvent(Event event);
    boolean updateEvent(Event event);
    boolean deleteEvent(Event event);
    Optional<Event> getEventById(long ID);
    void setEventListModel();

}
