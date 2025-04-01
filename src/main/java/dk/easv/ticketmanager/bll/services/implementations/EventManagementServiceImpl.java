package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.bll.services.EventManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EventManagementServiceImpl implements EventManagementService
{
  private final RepositoryService repositoryService;
  private static EventRepository eventRepository;
  private final ObservableSet<EventModel> eventModelObservableSet;

  public EventManagementServiceImpl(){
    repositoryService = null;
    eventRepository = null;
    eventModelObservableSet = null;

  }


  public EventManagementServiceImpl(RepositoryService repositoryService){
    this.repositoryService = repositoryService;
    eventRepository = this.repositoryService.getRepository(EventRepository.class);
    eventModelObservableSet = FXCollections.observableSet();
  }


  private EventRepository getEventRepository(){
    if(repositoryService == null || repositoryService.getRepository(UserRepository.class) == null){
      return null;
    }
    return repositoryService.getRepository(EventRepository.class);
  }

  private EventModel mapEvent(Event event){
    return new EventModel(event.getID(), event.getName());
  }


  @Override public List<EventModel> getEventModelList()
  {
    return eventRepository.getAll().stream().map(
        this::mapEvent).toList();
  }

  @Override public ObservableSet<EventModel> getEventModelObservable()
  {
    return eventModelObservableSet;
  }

  @Override public void addEvent(Event event)
  {
    eventRepository.save(event);
  }


}
