package dk.easv.ticketmanager.be;

import jakarta.persistence.*;
import javafx.scene.image.Image;

@Entity
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "role_id")
    private long role_id;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image_path")
    private String imagePath;

    // Default constructor
    public User() {
        this.id = 0;
        this.first_name = "Ronaldihno";
        this.last_name = "Gaucho";
        this.email = "ronaldihnogaucho123@gmail.com";
        this.password = "password";
        this.imagePath = "images/event-template.jpg"; // Default image path
    }

    // Parameterized constructor
    public User(int id, String first_name,String last_name, String email, String password, String phone_number, String imagePath, long role_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.role_id = role_id;
//        this.imagePath = imagePath;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone_number() { return phone_number;}
    public void setPhone_number(String phone_number) {this.phone_number = phone_number;}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Method to get the JavaFX Image from the image path (similar to Event)
    public Image getUserImage() {
        return new Image(imagePath);
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}