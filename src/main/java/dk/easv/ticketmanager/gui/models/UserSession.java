package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;

public class UserSession {
    private static UserSession instance;
    private UserModel loggedUserModel;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null)
            instance = new UserSession();
        return instance;
    }

    public void setLoggedUserModel(UserModel userModel) {
        if(userModel == null || userModel.getLoggedSessionToken().isEmpty()){
            throw new RuntimeException("Not authorized to do that.");
        }
        this.loggedUserModel = userModel;
    }

    public UserModel getLoggedUserModel() {
        return loggedUserModel;
    }
//    public String getRoleName() {
//        return user.getRole().getName();
//    }
    public void clearSession() {
        instance = null;
        loggedUserModel = null;
    }
}
