package dk.easv.ticketmanager.bll;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.dal.implementations.EventRepository;
import dk.easv.ticketmanager.dal.interfaces.IEventRepository;

import java.util.List;

public class EventService {
    private final IEventRepository eventRepository = new EventRepository();

    public List<Event> getAllEvents() {
        return eventRepository.getAll();
    }
}
