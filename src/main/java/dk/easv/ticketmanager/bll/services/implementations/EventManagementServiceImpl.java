package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.EventImage;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventManagementServiceImpl implements EventManagementService {
    private final RepositoryService repositoryService;

    private final EventListModel eventListModel = new EventListModel();

    public EventManagementServiceImpl(){
        repositoryService = null;
    }

    public EventManagementServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService) throws IOException {
        this.repositoryService = repositoryService;

        setEventListModel();
    }

    public EventListModel getEventListModel() {
        return eventListModel;
    }

    @Override
    public boolean createNewEvent(Event event) throws IOException {
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
    public boolean updateEvent(EventModel event) {
        return false;
    }

    @Override
    public boolean deleteEvent(Event event) {
        return false;
    }

    @Override
    public void setEventListModel() throws IOException {
        List<Event> events = repositoryService.getRepository(EventRepository.class).getAll().stream().toList();
        List<EventModel> list = new ArrayList<>();
        for (Event event : events) {
            EventModel eventModel = new EventModel(event);
            list.add(eventModel);
        }
        List<EventModel> eventModels = new ArrayList<>(list);
        eventListModel.setEvents(eventModels);
    }

    @Override
    public Optional<Event> getEventById(long ID){
        return this.repositoryService.getRepository(EventRepository.class).getById(ID);
    };

    @Override
    public List<Image> getAllImages(){
        return repositoryService.getRepository(EventRepository.class).getAllImages();
    }
    @Override
    public void addEventImage(Image image) throws IOException {
        EventImage eventImage = new EventImage();
        eventImage.setImageData(ImageConverter.convertToByteArray(image));
        repositoryService.getRepository(EventRepository.class).addEventImage(eventImage);
    }

}
