package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.ObservableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public interface EventManagementService
{

  List<EventModel> getEventModelList();
  ObservableSet<EventModel> getEventModelObservable();
  void addEvent(Event event);


}
