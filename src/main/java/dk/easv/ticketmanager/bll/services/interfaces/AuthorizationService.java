package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.be.Permission;
import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;

import java.util.List;

public interface AuthorizationService
{
  List<Permission> getPermissionsForRole(String roleName);
  void createNewRole(String name);
  Role findRoleByName(String name);
  boolean canAddUser(User user);
}
