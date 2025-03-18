package dk.easv.ticketmanager.dal.interfaces;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;

import java.util.List;

public interface IEventRepository {
    List<Event> getAll();
    Event getById(int id);
    void save(Event event);
    void delete(Event event);
    List<Location> getAllLocations();
}
