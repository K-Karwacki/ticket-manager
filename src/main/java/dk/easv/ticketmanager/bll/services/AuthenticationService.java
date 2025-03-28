package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.exceptions.AuthenticationException;
import dk.easv.ticketmanager.gui.models.UserModel;

public interface AuthenticationService
{
  boolean authenticateUser(String email, String hashedPassword) throws AuthenticationException;
  UserModel registerNewUser(String firstName, String lastName, String roleName, String email, String Password) throws AuthenticationException;
  UserModel findUserByEmail(String admin);
}
