package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.utils.DateTimeUtils;
import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class User
{
    private int userId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private Image userImage;

    public User(){
        this.userId = 0;
        this.userImage = new Image(String.valueOf(Main.class.getResource("images/event-template.jpg")));
        LocalDateTime now = LocalDateTime.now();
        this.email = "ronaldihnogaucho123@gmail.com";
        this.password = "password";
        this.fullName = "Ronaldihno Gaucho";
    }

    public User(int userId, String fullName, String email, String password, String phoneNumber, String imagePath)
    {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userImage = new Image(imagePath);
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }
    public int getUserId() {
        return userId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Image getUserImage() {
        return userImage;
    }
}
