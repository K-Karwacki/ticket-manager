package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.dal.entities.Permission;
import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.gui.models.UserModel;

import java.util.Collection;
import java.util.List;

public interface AuthorizationService
{
  List<Permission> getPermissionsForRole(String roleName);
  void createNewRole(String name);
  Role findRoleByName(String name);
  Role findRoleByID(long id);
  Collection<Role> getRoles();


  boolean canAddUserWithRole(Role role);
  boolean canEditUser(UserModel userModel);
}
