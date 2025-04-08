package dk.easv.ticketmanager.gui.models;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class UserSession {
    private static UserSession instance;
    private UserModel loggedUserModel;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null)
            instance = new UserSession();
        return instance;
    }
    public void setProfileImage(Circle profileCircle) {
        if (loggedUserModel.getImage() != null) {
            try {
                ImagePattern pattern = new ImagePattern(loggedUserModel.getImage());
                profileCircle.setFill(pattern); // profileImage should be a Circle
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }
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
