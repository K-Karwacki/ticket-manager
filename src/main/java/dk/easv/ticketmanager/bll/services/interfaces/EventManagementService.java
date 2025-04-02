package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EventManagementService {
    EventListModel getEventListModel();
    boolean createNewEvent(Event event) throws IOException;
    boolean updateEvent(Event event);

    boolean updateEvent(EventModel event);

    boolean deleteEvent(Event event);
    Optional<Event> getEventById(long ID);
    void setEventListModel() throws IOException;

    List<Image> getAllImages();

    void addEventImage(Image image) throws IOException;
}
