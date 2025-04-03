package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.EventImage;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EventManagementServiceImpl implements EventManagementService {
    private final RepositoryService repositoryService;
    private final EventRepository eventRepository;

    private final EventListModel eventListModel;




    public EventManagementServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService) {
        this.repositoryService = repositoryService;
        this.eventRepository = this.repositoryService.getRepository(EventRepository.class);
        eventListModel = new EventListModel();

        initialize();
    }

    private void initialize() {
        if (eventListModel == null || eventRepository == null || repositoryService == null) {
            throw new RuntimeException("Load dependencies for EventManagementService");
        }
        Collection<Event> events = eventRepository.getAll();
        for (Event event : events) {
            EventModel eventModel = new EventModel(event);
            eventModel.setImage(ImageConverter.convertToImage(event.getEventImage().getImageData()));
            eventListModel.addEventModel(new EventModel(event));
        }

        eventListModel.setEventImages(eventRepository.getAllEventImages());
    }

    public EventListModel getEventListModel() {
        return eventListModel;
    }

    @Override
    public boolean createNewEvent(EventModel eventModel) {
        Event newEvent = new Event();
        newEvent.setLocation(new Location(eventModel.getLocation().getName(), eventModel.getLocation().getAddress(), eventModel.getLocation().getCity(), eventModel.getLocation().getPost_code()));
        EventImage eventImage = eventRepository.getEventImageByID(eventModel.getEventImage().getId());
        newEvent.setEventImage(eventImage);
        newEvent.setName(eventModel.nameProperty().get());
        newEvent.setTime(eventModel.timeProperty().get());
        newEvent.setDate(eventModel.dateProperty().get());
        newEvent.setDescription(eventModel.descriptionProperty().get());

        Event saved = eventRepository.save(newEvent);
        if (saved == null) {
            throw new RuntimeException("Error creating new event");
        }
        EventModel savedModel = new EventModel(saved);

        eventListModel.addEventModel(savedModel);
        return true;
    }

    @Override
    public boolean updateEvent(EventModel eventModel) {
        try {
            Optional<Event> existingEvent = eventRepository.getById(eventModel.getID());
            if (!existingEvent.isPresent()) {
                return false;
            }
            Event eventToUpdate = existingEvent.get();
            eventToUpdate.setName(eventModel.nameProperty().get());
            eventToUpdate.setDescription(eventModel.descriptionProperty().get());
            eventToUpdate.setTime(eventModel.timeProperty().get());
            eventToUpdate.setDate(eventModel.dateProperty().get());


            Location location = eventToUpdate.getLocation();
            if (location == null) {
                location = new Location();
                eventToUpdate.setLocation(location);
            }
            location.setName(eventModel.getLocation().getName());
            location.setAddress(eventModel.getLocation().getAddress());
            location.setCity(eventModel.getLocation().getCity());
            location.setPostCode(eventModel.getLocation().getPost_code());

            Event updatedEvent = eventRepository.update(eventToUpdate);
            if (updatedEvent == null) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEvent(EventModel eventModel) {
        try {
            Event event = new Event();
          EventModel savedModel = new EventModel(event);
          eventRepository.deleteById(event.getID());
          eventListModel.deleteEventModel(savedModel);
          return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void setEventListModel() {
        List<Event> events = eventRepository.getAll().stream().toList();
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
        List<Image> imageList = new ArrayList<>();
        for (EventImage eventImage : eventListModel.getEventImagesObservable())
        {
            System.out.println(eventImage);
            imageList.add(ImageConverter.convertToImage(eventImage.getImageData()));
        }
        return imageList;
    }

    @Override
    public boolean uploadEventImage(Image image, boolean saveToDB) throws IOException {
        EventImage eventImage = new EventImage();
        eventImage.setImageData(ImageConverter.convertToByteArray(image));
        EventImage saved = eventRepository.saveEventImage(eventImage);
        if(saved != null){
            eventListModel.getEventImagesObservable().add(eventImage);
            return true;
        }
        return false;
    }


}
