package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.dal.entities.Event;
import dk.easv.ticketmanager.dal.entities.EventImage;
import dk.easv.ticketmanager.dal.entities.Location;
import dk.easv.ticketmanager.dal.entities.User;

import java.io.IOException;
import java.util.List;

public interface EventRepository extends BaseRepository<Event>{
    List<Location> getAllLocations();
    void assignCoordinatorToEvent(Event event, User user);
    void dissociateEventFromCoordinator(Event event, User user);
    List<EventImage> getAllEventImages();
    EventImage saveEventImage(EventImage eventImage) throws IOException;
    EventImage getEventImageByID(Long eventImageID);
}
