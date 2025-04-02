package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.be.Permission;
import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;

import java.util.List;
import java.util.Optional;

public interface AuthorizationService
{
  List<Permission> getPermissionsForRole(String roleName);
  void createNewRole(String name);
  Role findRoleByName(String name);
  boolean canAddUser(User user);
}
