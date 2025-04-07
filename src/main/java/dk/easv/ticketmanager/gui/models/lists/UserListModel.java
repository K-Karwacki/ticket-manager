package dk.easv.ticketmanager.gui.models.lists;

import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Set;

public class UserListModel {
    private ObservableSet<UserModel> users = FXCollections.observableSet();

    public void setUsers(Set<UserModel> users) {
        this.users.addAll(users);
    }

    public void addUserModel(UserModel userModel){
        users.add(userModel);
    }

    public ObservableSet<UserModel> getUsersObservable()
    {
        return users;
    }
}
