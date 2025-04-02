package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.EventImage;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EventManagementService {
    EventListModel getEventListModel();
    boolean createNewEvent(EventModel eventModel);
    boolean deleteEvent(EventModel eventModel);
    boolean updateEvent(EventModel eventModel);

    Optional<Event> getEventById(long ID);
    void setEventListModel() throws IOException;

    List<Image> getAllImages();

    boolean uploadEventImage(Image image, boolean saveToDB) throws IOException;
}
