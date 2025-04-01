package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.AuthorizationService;
import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.dal.repositories.TicketRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceImpl implements DatabaseService
{
  private final RepositoryService repositoryService;
  private final AuthorizationService authorizationService;

  private final UserListModel userListModel = new UserListModel();
  private final EventListModel eventListModel = new EventListModel();

  public DatabaseServiceImpl(){
    repositoryService = null;
    authorizationService = null;
  }

  public DatabaseServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
    this.repositoryService = repositoryService;
    this.authorizationService = authorizationService;

    setEventListModel();
    setUserListModel();
  }

  public UserListModel getUserListModel() {
    return userListModel;
  }
  public EventListModel getEventListModel() {
    return eventListModel;
  }

  @Override
  public boolean createNewEvent(Event event) {
    Event newEvent = repositoryService.getRepository(EventRepository.class).save(event);
    if(newEvent == null){
      throw new RuntimeException("Error creating new event");
    }
    eventListModel.getEvents().add(new EventModel(newEvent));
    return true;
  }

  @Override
  public boolean updateEvent(Event event) {
    return false;
  }

  @Override
  public boolean deleteEvent(Event event) {
    return false;
  }

//  @Override
//  public List<UserModel> getCoordinatorList() {
//    if(authorizationService.canGetCoordinators(UserSession.getInstance().getLoggedUserModel().getID())){
//      return List.of();
//    }
//    throw new RuntimeException("NOT AUTHORIZED");
//  }

  private void setEventListModel(){
    List<Event> events = repositoryService.getRepository(EventRepository.class).getAll();
      List<EventModel> eventModels = new ArrayList<>(events.stream().map(event -> new EventModel(event)).toList());
    eventListModel.setEvents(eventModels);
  }


  public void addTicketType(TicketType ticketType){
    this.repositoryService.getRepository(TicketRepository.class).addTicketType(ticketType);
  }


  private void setUserListModel(){
    List<User> users = repositoryService.getRepository(UserRepository.class).getAll();
    List<UserModel> userModels = new ArrayList<>();
    users.forEach(user ->
    {
      UserModel userModel = new UserModel(user.getID(), user.getRole());
      userModel.setFirstName(user.getFirstName());
      userModel.setLastName(user.getLastName());
      userModel.setEmail(user.getEmail());
      userModel.setPhoneNumber(user.getPhoneNumber());
      userModels.add(userModel);
      userModels.add(userModel);
    });
    userListModel.setUsers(userModels);
  }
  public Event getEventById(long ID){
    return this.repositoryService.getRepository(EventRepository.class).getById(ID);
  };

  public void addTicket(Ticket ticket){
    this.repositoryService.getRepository(TicketRepository.class).save(ticket);
  }

  @Override
  public List<TicketType> getTicketTypesForEvent(Event event) {
    return List.of();
  }
}
