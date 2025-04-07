package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.models.UserModel;

public interface AuthenticationService
{
  boolean authenticateUser(String email, String hashedPassword) throws AuthenticationException;
//  UserModel registerNewUser(String firstName, String lastName, long ID, String email, String phone, String Password) throws AuthenticationException;
  UserModel findUserByEmail(String admin);
}
