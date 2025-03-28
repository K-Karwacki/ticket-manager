package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.AuthenticationService;
import dk.easv.ticketmanager.bll.services.AuthorizationService;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
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

  public AuthenticationServiceImpl(AuthRepository authRepository, UserRepository userRepository){
    this.authRepository = authRepository;
    this.userRepository = userRepository;
  }


  public UserModel registerNewUser(String firstName, String lastName, String roleName, String email, String password) throws AuthenticationException
  {
    if(authRepository == null || userRepository == null){
      throw new RuntimeException("Repositories failed");
    }
    // Validate new user data

    //todo: validate fields if empty return null and throw exception

    if(email.isEmpty()){
      throw new AuthenticationException("Email cannot be empty");
    }
    if(password.isEmpty()){
      throw new AuthenticationException("Password cannot be empty");
    }
    Optional<Role> roleOptional = authRepository.findRoleByName(roleName);
    if(roleOptional.isEmpty()){
      throw new AuthenticationException("Not a single role with given name.");
    }

    User newUser = new User();
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setRole(roleOptional.get());
    newUser.setEmail(email);
    newUser.setHashedPassword(hashPassword(password));
//    UserModel registeredUserModel = userRepository.save(newUser);
    User savedUser = userRepository.save(newUser);

    if(savedUser == null){
      System.out.println("Something went wrong");
      throw new AuthenticationException("User couldn't be created");
    }

    return new UserModel(savedUser.getId(), savedUser.getRole(), savedUser.getFullName());
  }

  @Override public UserModel findUserByEmail(String email)
  {
    if(userRepository == null){
      return null;
    }
    Optional<User> userOptional = userRepository.findUserByEmail(email);
    return userOptional.map(
            user -> new UserModel(user.getId(), user.getRole(), user.getFullName()))
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
      this.authenticatedUserModel = new UserModel(userFound.getId(), userFound.getRole(), userFound.getFullName());
      UserSession.getInstance().setLoggedUserModel(this.authenticatedUserModel);
      return true;
    }
    return false;
  }

  public boolean verifyPassword(String plainPassword, String hashedPassword) {
    try {
      if (plainPassword == null || hashedPassword == null) {
        return false;
      }
      return BCrypt.checkpw(plainPassword, hashedPassword);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public String hashPassword(String password) {
    if (password == null) {
      throw new IllegalArgumentException("Password cannot be null");
    }
    return BCrypt.hashpw(password, BCrypt.gensalt(12));
  }


}