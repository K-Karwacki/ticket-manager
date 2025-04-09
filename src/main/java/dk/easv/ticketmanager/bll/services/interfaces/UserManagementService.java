package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.gui.models.lists.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.scene.image.Image;

public interface UserManagementService {
    UserListModel getUserListModel();
    UserModel registerNewUser(String firstName, String lastName, long role_id, String email, String phone, String Password, Image image);
    boolean deleteUser(UserModel userModel);
    boolean updateUser(UserModel userModel);

}
