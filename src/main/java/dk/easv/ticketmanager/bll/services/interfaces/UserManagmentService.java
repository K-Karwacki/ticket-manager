package dk.easv.ticketmanager.bll.services.interfaces;

import dk.easv.ticketmanager.gui.models.UserListModel;

public interface UserManagmentService {
    UserListModel getUserListModel();
    void setUserListModel();
}
