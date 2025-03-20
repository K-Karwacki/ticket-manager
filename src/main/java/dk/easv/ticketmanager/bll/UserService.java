package dk.easv.ticketmanager.bll;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.implementations.UserRepository;
import dk.easv.ticketmanager.dal.interfaces.IUserRepository;

import java.util.List;

public class UserService {
    private final IUserRepository userRepository = new UserRepository();

    public List<User> getAllUsers(){
        return userRepository.getAll();
    }
    public List<User> getUsersByRole(Role role){
        return userRepository.getByRole(role);
    }
    public void addUser(User user){

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("you miserably failed", e);
        }

    }
    public void deleteUser(User user){
        try{
        userRepository.delete(user);
        } catch (Exception e) {
            throw new RuntimeException("you miserably failed", e);
        }
    }

  public List<Role> getAllRoles()
  {
      try{
          return userRepository.getRoles();
      }catch (Exception e){
          throw new RuntimeException("Couldn't fetch roles: ", e);
      }
  }
}
