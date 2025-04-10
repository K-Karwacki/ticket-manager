package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.gui.models.CustomerModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.event.GeneratedTicketModel;
import dk.easv.ticketmanager.gui.models.lists.EventListModel;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.gui.models.event.TicketModel;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EventManagementService {
    EventListModel getEventListModel();
    boolean createNewEvent(EventModel eventModel);
    boolean deleteEvent(EventModel eventModel);
    boolean updateEvent(EventModel eventModel);

    boolean removeCoordinatorFromEvent(UserModel userModel, EventModel eventModel);
    boolean addCoordinatorToEvent(UserModel userModel, EventModel eventModel);

//    Optional<Event> getEventById(long ID);

    Set<TicketModel> getTicketsForEvent(EventModel eventModel);
    boolean addTicketToEvent(TicketModel ticketModel, EventModel eventModel);
    boolean removeTicketFromEvent(TicketModel ticketModel, EventModel eventModel);

    boolean generateTicketsForCustomer(int quantity,TicketModel ticketModel, CustomerModel customerModel);
//    Set<GeneratedTicketModel>
    boolean sendTicketsViaEmailToCustomer(TicketModel ticketModel, CustomerModel customerModel);

    List<Image> getAllImages();

    boolean uploadEventImage(Image image) throws IOException;
}
