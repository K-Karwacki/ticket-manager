package dk.easv.ticketmanager.bll;

import org.mindrot.jbcrypt.BCrypt;

public class AuthenticationService
{
  private static final AuthenticationService INSTANCE = new AuthenticationService();

  public AuthenticationService() {}


  public boolean authenticateUser(String username, String password){
    String hashedPassword = "hashed";

    return BCrypt.checkpw(password, hashedPassword);
  }

  public String hashPassword(String password){
    return BCrypt.hashpw(password, BCrypt.gensalt(12));
  }

  public static AuthenticationService getInstance()
  {
    return INSTANCE;
  }
}
