package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.gui.models.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;

public interface UserManagementService {
    UserListModel getUserListModel();
    boolean deleteUser(UserModel userModel);
    boolean updateUser(UserModel userModel);
    void addUser(UserModel savedUser);
}
