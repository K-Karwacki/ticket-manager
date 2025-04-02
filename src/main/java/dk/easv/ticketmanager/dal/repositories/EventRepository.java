package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.EventImage;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.List;

public interface EventRepository extends BaseRepository<Event>{
    List<Location> getAllLocations();
    void assignCoordinatorToEvent(Event event, User user);
    void dissociateEventFromCoordinator(Event event, User user);
    List<Image> getAllImages();
    boolean addEventImage(EventImage eventImage) throws IOException;
}
