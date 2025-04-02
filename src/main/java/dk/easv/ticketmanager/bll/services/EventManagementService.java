package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.ObservableSet;
import javafx.scene.image.Image;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public interface EventManagementService
{

  List<EventModel> getEventModelList();
  ObservableSet<EventModel> getEventModelObservableSet();
  void addEvent(Event event);
  boolean addTicketTypeForEventByID(TicketType ticketType, long id);
  void updateEvent(EventModel eventModel);
  List<Image> getAllImages();
  public void addEventImage(Image image);
}
