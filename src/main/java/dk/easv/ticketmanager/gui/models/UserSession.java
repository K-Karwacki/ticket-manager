package dk.easv.ticketmanager.gui.models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public enum UserSession {
    INSTANCE;
    private SimpleObjectProperty<UserModel> loggedUserModel = new SimpleObjectProperty<>();

    private UserSession() {}

    public void setProfileImage(Circle profileCircle) {
        if (getLoggedUserModel().getImage() != null) {
            try {
                ImagePattern pattern = new ImagePattern(getLoggedUserModel().getImage());
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
        this.loggedUserModel.set(userModel);
    }


    public UserModel getLoggedUserModel()
    {
        return loggedUserModel.get();
    }

    public SimpleObjectProperty<UserModel> loggedUserModelProperty()
    {
        return loggedUserModel;
    }

    //
    public void clearSession() {
        loggedUserModel.set(null);
    }
}
