package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.AuthorizationService;
import dk.easv.ticketmanager.bll.services.UserManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.List;
import java.util.stream.Collectors;

public class UserManagementServiceImpl implements UserManagementService
{
  private final UserRepository userRepository;
  private final AuthorizationService authorizationService;
  private final ObservableSet<UserModel> userModelObservableSet;

  public UserManagementServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
    userRepository = repositoryService.getRepository(UserRepository.class);
    this.authorizationService = authorizationService;
    userModelObservableSet = FXCollections.observableSet();
  }

  private UserModel mapUser(User user){
    return new UserModel(user);
  }

  @Override public List<UserModel> getUserModelList()
  {
    return userRepository.getAll().stream().map(this::mapUser).collect(
        Collectors.toList());
  }

  @Override public ObservableSet<UserModel> getUserModelObservable()
  {
    return userModelObservableSet;
  }

  @Override public void addUser(User user)
  {
    if(authorizationService.canAddUser(user)){
      User newUser = userRepository.save(user);
      if(newUser != null){
        userModelObservableSet.add(mapUser(user));
      }
    }

    System.out.println("User couldn't be created.");
  }

}
