package dk.easv.ticketmanager.bll;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.implementations.EventRepository;
import dk.easv.ticketmanager.dal.interfaces.IEventRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventService {
    private final IEventRepository eventRepository = new EventRepository();

    public List<Event> getAllEvents() {
        return eventRepository.getAll();
    }
    public void addEvent(Event event) {
        eventRepository.save(event);
    }
    public void editEvent(Event event) { eventRepository.edit(event);}
    public void deleteEvent(Event event) {eventRepository.delete(event);}

    public List<String> getAllUniqueCities(){
        List<Location> locations =  eventRepository.getAllLocations();
        List<String> cities = new ArrayList<>();
        locations.forEach(location -> {
            cities.add(location.getCity());
        });
        Set<String> set = new HashSet<>(cities);
        cities.clear();
        cities.addAll(set);
        return cities;
    }
    public void assignCoordinatorToEvent(Event event, User user){
        eventRepository.assignCoordinatorToEvent(event, user);
    }
    public void dissociateEventFromCoordinator(Event event, User user){
        eventRepository.dissociateEventFromCoordinator(event, user);
    }

    public Event getEventById(long id) {
        return eventRepository.getById(id);
    }
}
