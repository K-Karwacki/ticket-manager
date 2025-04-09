package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.gui.models.lists.UserListModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Optional;

public interface UserManagementService {
    UserListModel getUserListModel();
    UserModel registerNewUser(String firstName, String lastName, long role_id, String email, String phone, String Password, Image image);
    boolean deleteUser(UserModel userModel);
    boolean updateUser(UserModel userModel);
    boolean updateUser(User user);
    Optional<User> getUserByEmail(String email);
    void sendTemporaryPassword(User user) throws IOException;
    Optional<User> getUserById(long id);
}
