package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.bll.services.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.*;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

public class DatabaseServiceImpl implements DatabaseService
{
  private final RepositoryService repositoryService;

  private final UserListModel userListModel = new UserListModel();
  private final EventListModel eventListModel = new EventListModel();

  public DatabaseServiceImpl(){
    repositoryService = null;
  }

  public DatabaseServiceImpl(RepositoryService repositoryService){
    this.repositoryService = repositoryService;
    setEventListModel();
    setUserListModel();
  }

  @Override
  public RepositoryService getRepositoryService() {
    return repositoryService;
  }
  public UserListModel getUserListModel() {
    return userListModel;
  }
  public EventListModel getEventListModel() {
    return eventListModel;
  }
  private void setEventListModel(){
    List<Event> events = repositoryService.getRepository(EventRepository.class).getAll();
    List<EventModel> eventModels = new ArrayList<>();
    events.forEach(event ->
    {
      LocationModel locationModel = new LocationModel(event.getLocation().getID());
      locationModel.setName(event.getLocation().getName());
      locationModel.setAddress(event.getLocation().getAddress());
      locationModel.setCity(event.getLocation().getCity());
      locationModel.setPost_code(event.getLocation().getPostCode());

      EventModel eventModel = new EventModel(event.getID());
      eventModel.setLocation(locationModel);
      eventModel.setName(event.getName());
      eventModel.setDescription(event.getDescription());
      eventModel.setTime(event.getTime());
      eventModel.setDate(event.getDate());
      eventModel.setImagePath(event.getImagePath());

      eventModels.add(eventModel);
    });
    eventListModel.setEvents(eventModels);
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


}
