package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.dal.entities.*;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.dal.repositories.TicketRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.CustomerModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.event.TicketModel;
import dk.easv.ticketmanager.gui.models.lists.EventListModel;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

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

        for (TicketModel ticketModel : eventModel.getTickets()) {
            Ticket newTicketForEvent = new Ticket();
            newTicketForEvent.setInfo(ticketModel.getInfo());
            newTicketForEvent.setPrice(ticketModel.getPrice());
            newTicketForEvent.setType(ticketModel.getType());
            newTicketForEvent.setEvent(newEvent);

            newEvent.addTicket(newTicketForEvent);
        }

        for (UserModel assignedCoordinator : eventModel.getAssignedCoordinators())
        {
            Optional<User> assignedCoordinatorFetched = repositoryService.getRepository(
                UserRepository.class).getById(assignedCoordinator.getID());
            System.out.println("found two " + assignedCoordinatorFetched.get().getId());
            assignedCoordinatorFetched.ifPresent(
                newEvent::assignCoordinatorToEvent);
        }


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
            if (existingEvent.isEmpty()) {
                return false;
            }
            Event eventToUpdate = existingEvent.get();
            eventToUpdate.setName(eventModel.nameProperty().get());
            eventToUpdate.setDescription(eventModel.descriptionProperty().get());
            eventToUpdate.setTime(eventModel.timeProperty().get());
            eventToUpdate.setDate(eventModel.dateProperty().get());


            Location location = eventToUpdate.getLocation();
            if (location == null) {
                return false;
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

    @Override public Set<TicketModel> getTicketsForEvent(EventModel eventModel)
    {
        Set<TicketModel> ticketModelHashSet = new HashSet<>();
        try{
            for (Ticket ticket : repositoryService.getRepository(
                    TicketRepository.class)
                .getTicketsForEventID(eventModel.getID()))
            {
                ticketModelHashSet.add(new TicketModel(ticket));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticketModelHashSet;
    }

    @Override public boolean addTicketToEvent(TicketModel ticketModel,
        EventModel eventModel)
    {
        try{
            eventModel.addTicket(ticketModel);
            Ticket ticketToSave = new Ticket();
            Optional<Event> event = eventRepository.getById(eventModel.getID());

            if(event.isEmpty()){
                System.out.println("no event with given id");
                return false;
            }

            ticketToSave.setEvent(event.get());
            repositoryService.getRepository(TicketRepository.class).save(ticketToSave);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override public boolean removeTicketFromEvent(TicketModel ticketModel,
        EventModel eventModel)
    {
        return false;
    }

    @Override public boolean generateTicketsForCustomer(TicketModel ticketModel,
        CustomerModel customerModel)
    {
        return false;
    }

    @Override public boolean sendTicketsViaEmailToCustomer(
        TicketModel ticketModel, CustomerModel customerModel)
    {
        return false;
    }

    @Override
    public boolean deleteEvent(EventModel eventModel) {
        try {
          eventRepository.deleteById(eventModel.getID());
          eventListModel.deleteEventModel(eventModel);
          return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


//    @Override
//    public void setEventListModel() {
//        List<Event> events = eventRepository.getAll().stream().toList();
//        List<EventModel> list = new ArrayList<>();
//        for (Event event : events) {
//            EventModel eventModel = new EventModel(event);
//            list.add(eventModel);
//        }
//        List<EventModel> eventModels = new ArrayList<>(list);
//        eventListModel.setEvents(eventModels);
//    }
//
//    @Override
//    public Optional<Event> getEventById(long ID){
//        return this.repositoryService.getRepository(EventRepository.class).getById(ID);
//    };

    @Override
    public List<Image> getAllImages(){
        List<Image> imageList = new ArrayList<>();
        for (EventImage eventImage : eventListModel.getEventImagesObservable())
        {
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
