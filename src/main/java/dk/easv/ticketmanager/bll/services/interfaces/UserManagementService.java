package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.gui.models.lists.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;

public interface UserManagementService {
    UserListModel getUserListModel();
    UserModel registerNewUser(String firstName, String lastName, long role_id, String email, String phone, String Password);
    boolean deleteUser(UserModel userModel);
    boolean updateUser(UserModel userModel);

}
