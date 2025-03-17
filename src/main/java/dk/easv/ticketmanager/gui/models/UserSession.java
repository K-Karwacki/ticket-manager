package dk.easv.ticketmanager.gui.models;

public class UserSession {
    private static UserSession instance;
    private String username;
    private Role role;

    enum Role{
        Admin,
        Coordinator;
    }

    private UserSession(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public static void createSession(String username, Role role) {
        instance = new UserSession(username, role);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public Role getRole() {
        return role;
    }

    public void clearSession() {
        instance = null;
    }
}
