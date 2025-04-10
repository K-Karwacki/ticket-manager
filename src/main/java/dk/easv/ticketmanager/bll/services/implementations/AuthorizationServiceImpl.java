package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.entities.Role;

import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.gui.models.UserSession;
import dk.easv.ticketmanager.utils.RoleType;

import java.util.List;
import java.util.Optional;

public class AuthorizationServiceImpl implements AuthorizationService
{
  private final AuthRepository authRepository;
  private final UserSession userSession = UserSession.INSTANCE;

  public AuthorizationServiceImpl(){
    this.authRepository = null;
  }

  public AuthorizationServiceImpl(RepositoryService repositoryService){
    this.authRepository = repositoryService.getRepository(AuthRepository.class);

//    return authRepository.findRoleByName(roleName).map(authRepository::getPermissionsForRole).orElse(null);
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

  @Override
  public boolean canAddUserWithRole(Role role) {
    System.out.println(userSession.getLoggedUserModel().getName());
    if(userSession.getLoggedUserModel().getRole().getName().equals(
        RoleType.COORDINATOR.name())){
      if(role.getName().equals(RoleType.ADMIN.name())){
        return false;
      }
      return true;
    }
    if(userSession.getLoggedUserModel().getRole().getName().equals(RoleType.ADMIN.name())){
      return true;
    }
    return false;
  }

  @Override
  public boolean canEditUser(UserModel userModel) {
    if(userSession.getLoggedUserModel().getRole().getName().equals(
        RoleType.COORDINATOR.name())){
      if(userSession.getLoggedUserModel().getID() != userModel.getID()){
        return false;
      }
      return true;
    }
    if(userSession.getLoggedUserModel().getRole().getName().equals(RoleType.ADMIN.name())){
      return true;
    }
    return false;
  }

  @Override public Role findRoleByID(long id)
  {
    if(authRepository == null){
      return null;
    }

    Optional<Role> role = authRepository.getById(id);
    return role.orElse(null);
  }

  @Override public List<Role> getRoles()
  {
    if(authRepository == null){
      throw new RuntimeException("Load auth repository");
    }
    return authRepository.getRoles();
  }

}
