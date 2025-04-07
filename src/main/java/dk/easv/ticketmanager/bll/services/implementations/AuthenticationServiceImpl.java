package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthenticationService;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import dk.easv.ticketmanager.utils.TokenGenerator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService
{
  private final AuthRepository authRepository;
  private final UserRepository userRepository;
  private UserModel authenticatedUserModel;

  public AuthenticationServiceImpl(){
    this.authRepository = null;
    this.authenticatedUserModel = null;
    this.userRepository = null;
  }

  public AuthenticationServiceImpl(RepositoryService repositoryService){
    this.authRepository = repositoryService.getRepository(AuthRepository.class);
    this.userRepository = repositoryService.getRepository(UserRepository.class);
  }

  @Override public UserModel findUserByEmail(String email)
  {
    if(userRepository == null){
      return null;
    }
    Optional<User> userOptional = userRepository.findUserByEmail(email);
    return userOptional.map(UserModel::new)
        .orElse(null);
  }

  public boolean authenticateUser(String email, String password) throws AuthenticationException{
    if(userRepository == null){
      throw new RuntimeException("User repository failed");
    }
    if (email == null || password == null) {
      return false;
    }

    Optional<User> user = userRepository.findUserByEmail(email);

    if(user.isEmpty()){
      throw new AuthenticationException("Couldn't find user with given email.");
    }

    User userFound = user.get();

    if(verifyPassword(password, userFound.getHashedPassword())){
      this.authenticatedUserModel = new UserModel(userFound);
      this.authenticatedUserModel.setLoggedSessionToken(TokenGenerator.generateSessionToken());
      UserSession.getInstance().setLoggedUserModel(this.authenticatedUserModel);
      return true;
    }
    return false;
  }

  boolean verifyPassword(String plainPassword, String hashedPassword) {
    try {
      if (plainPassword == null || hashedPassword == null) {
        return false;
      }
      return BCrypt.checkpw(plainPassword, hashedPassword);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }




}