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
        userRepository.save(user);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
