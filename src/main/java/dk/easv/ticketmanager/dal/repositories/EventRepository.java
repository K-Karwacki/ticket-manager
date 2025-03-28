package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;

import java.util.List;

public interface EventRepository extends BaseRepository<Event>{
    List<Location> getAllLocations();
    void assignCoordinatorToEvent(Event event, User user);
    void dissociateEventFromCoordinator(Event event, User user);
}
