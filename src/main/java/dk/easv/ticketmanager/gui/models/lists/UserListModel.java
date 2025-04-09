package dk.easv.ticketmanager.gui.models.lists;

import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Iterator;
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

    public void deleteUserModel(UserModel userModel) {
        users.remove(userModel);
    }

    public void updateUser(UserModel userModel) {
        Iterator<UserModel> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserModel u = iterator.next();
            if (u.getID() == userModel.getID()) {
                iterator.remove();
                users.add(userModel);
                break;
            }
        }
    }
}
