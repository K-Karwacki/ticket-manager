package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class UserDataModel {
    private final UserService userService = new UserService();
    private static final ObservableList<User> users = FXCollections.observableArrayList();
    public UserDataModel() {
    }
    public void loadUsers(){
        users.setAll(userService.getAllUsers());
    }
    public ObservableList<User> getUsers() {
        return users;
    }
    public void addNewUser(User user){
        userService.addUser(user);
        users.add(user);
    }

    public void editUser(User user){
        userService.editUser(user);
    }

    public void deleteUser(User user){
        userService.deleteUser(user);
        users.remove(user);
    }

    public List<Role> getRoles()
    {
        return userService.getAllRoles();
    }

    public List<User> getAllCoordinators()
    {
        return users.stream()
            .filter(user -> user.getRole().getName().equals("coordinator"))
            .collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
