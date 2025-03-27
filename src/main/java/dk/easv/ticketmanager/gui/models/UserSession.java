package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.User;

public class UserSession {
    private static UserSession instance;
    private static User user;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null)
            instance = new UserSession();
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getRoleName() {
        return user != null ? user.getRole().getName() : null; // Add null check
    }

}