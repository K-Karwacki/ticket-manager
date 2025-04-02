package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;

import java.util.Optional;

public interface EventManagementService {
    EventListModel getEventListModel();
    boolean createNewEvent(EventModel eventModel);
    boolean updateEvent(EventModel eventModel);
    boolean deleteEvent(EventModel eventModel);
    Optional<Event> getEventById(long ID);
    void setEventListModel();

}
