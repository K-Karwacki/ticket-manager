package dk.easv.ticketmanager.bll;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.models.UserDataModel;
import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationService {
  private static final AuthenticationService INSTANCE = new AuthenticationService();
  private final UserDataModel userDataModel;

  private AuthenticationService() {
    this.userDataModel = new UserDataModel();
  }
  
  public User authenticateUser(String email, String password) {
    if (email == null || password == null) {
      return null;
    }

    User user = userDataModel.getUserByEmail(email);
    if (user != null && verifyPassword(password, user.getPassword())) {
      return user;
    }
    return null;
  }

  public boolean verifyPassword(String plainPassword, String hashedPassword) {
    try {
      if (plainPassword == null || hashedPassword == null) {
        return false;
      }
      return BCrypt.checkpw(plainPassword, hashedPassword); // DELETE LATER - FOR NOW PASSWORDS ARE NOT HASHED IN THE DB
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

  public static AuthenticationService getInstance() {
    return INSTANCE;
  }
}