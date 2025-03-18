package dk.easv.ticketmanager.dal.interfaces;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;

import java.util.List;

public interface IUserRepository {
    List<User> getAll();
    List<User> getByRole(Role role);
    void save(User user);
    void delete(User user);
}
