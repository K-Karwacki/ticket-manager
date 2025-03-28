package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.Permission;
import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends BaseRepository<Role>
{
  List<Permission> getPermissionsForRole(Role role);
  Optional<Role> findRoleByName(String roleName);
  List<Role> getRoles();
}
