package dk.easv.ticketmanager.dal.interfaces;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;

import java.util.List;

public interface IEventRepository {
    List<Event> getAll();
    Event getById(long id);
    void save(Event event);
    void delete(Event event);
    List<Location> getAllLocations();
    void assignCoordinatorToEvent(Event event, User user);
    void dissociateEventFromCoordinator(Event event, User user);
}
