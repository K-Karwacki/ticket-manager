package dk.easv.ticketmanager.bll;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
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
}
