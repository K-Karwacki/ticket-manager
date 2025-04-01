package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserListModel {
    private ObservableList<UserModel> users = FXCollections.observableArrayList();

    public void setUsers(List<UserModel> users) {
        this.users.setAll(users);
    }
}
