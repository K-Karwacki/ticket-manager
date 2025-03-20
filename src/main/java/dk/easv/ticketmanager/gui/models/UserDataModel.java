package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

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

    public List<User> getCoordinators() {
        return users.stream()
                .filter(user -> user.getRole().getName().equals("coordinator"))
                .toList();
    }
}
