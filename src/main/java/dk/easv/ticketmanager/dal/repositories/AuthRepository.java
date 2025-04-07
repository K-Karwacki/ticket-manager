package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.dal.entities.Permission;
import dk.easv.ticketmanager.dal.entities.Role;

import java.util.List;
import java.util.Optional;

public interface AuthRepository extends BaseRepository<Role>
{
  List<Permission> getPermissionsForRole(Role role);
  Optional<Role> findRoleByName(String roleName);
  List<Role> getRoles();
}
