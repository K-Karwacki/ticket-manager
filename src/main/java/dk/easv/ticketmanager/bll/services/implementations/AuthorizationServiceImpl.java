package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Permission;
import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.AuthorizationService;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.models.UserSession;
import dk.easv.ticketmanager.utils.RoleType;

import java.util.List;
import java.util.Optional;

public class AuthorizationServiceImpl implements AuthorizationService
{
  private final AuthRepository authRepository;
  private final UserSession userSession = UserSession.getInstance();

  public AuthorizationServiceImpl(){
    this.authRepository = null;
  }

  public AuthorizationServiceImpl(AuthRepository authRepository){
    this.authRepository = authRepository;
  }

  @Override public List<Permission> getPermissionsForRole(String roleName)
  {
    if(authRepository == null){
      return null;
    }
    return authRepository.findRoleByName(roleName).map(authRepository::getPermissionsForRole).orElse(null);
  }

  @Override public void createNewRole(String name)
  {
    if(authRepository == null){
      return;
    }
    this.authRepository.save(new Role(name));
  }

  @Override public Role findRoleByName(String name)
  {
    if(authRepository == null){
      return null;
    }
    Optional<Role> optionalRole = authRepository.findRoleByName(name);
    return optionalRole.orElse(null);
  }

  @Override public boolean canAddUser(User user)
  {
//    if(user.getRole().getName().equals(RoleType.ADMIN.name())){
////      if(userSession.getLoggedUserModel().getRoleProperty().get().get)
//    }
    return false;
  }

}
