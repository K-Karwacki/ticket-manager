package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
