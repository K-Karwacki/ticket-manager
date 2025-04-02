package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventManagementServiceImpl implements EventManagementService {
    private final RepositoryService repositoryService;

    private final EventListModel eventListModel = new EventListModel();

    public EventManagementServiceImpl(){
        repositoryService = null;
    }

    public EventManagementServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
        this.repositoryService = repositoryService;

        setEventListModel();
    }

    public EventListModel getEventListModel() {
        return eventListModel;
    }

    @Override
    public boolean createNewEvent(Event event) {
        Event newEvent = repositoryService.getRepository(EventRepository.class).save(event);
        if(newEvent == null){
            throw new RuntimeException("Error creating new event");
        }
        eventListModel.getEvents().add(new EventModel(newEvent));
        return true;
    }

    @Override
    public boolean updateEvent(Event event) {
        return false;
    }

    @Override
    public boolean deleteEvent(Event event) {
        return false;
    }

    @Override
    public void setEventListModel(){
        List<Event> events = repositoryService.getRepository(EventRepository.class).getAll().stream().toList();
        List<EventModel> eventModels = new ArrayList<>(events.stream().map(EventModel::new).toList());
        eventListModel.setEvents(eventModels);
    }

    @Override
    public Optional<Event> getEventById(long ID){
        return this.repositoryService.getRepository(EventRepository.class).getById(ID);
    };

}
