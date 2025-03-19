package dk.easv.ticketmanager.be;

import jakarta.persistence.*;
import javafx.scene.image.Image;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role_id")
    private long role_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(mappedBy = "coordinators")
    private Set<Event> coordinatedEvents = new HashSet<>();

    // Default constructor
    public User() {
        // Minimal defaults
    }

    // Parameterized constructor
    public User(long id, String firstName, String lastName, String email, String password, String phoneNumber, String imagePath, long role_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.imagePath = imagePath;
        this.role_id = role_id;
    }

    // Getters and setters (updated naming)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getRoleId() {
        return role_id;
    }

    public void setRoleId(long roleId) {
        this.role_id = roleId;
    }

    // Other getters/setters unchanged

    public Set<Event> getCoordinatedEvents() {
        return coordinatedEvents;
    }

    public void setCoordinatedEvents(Set<Event> coordinatedEvents) {
        this.coordinatedEvents = coordinatedEvents;
    }

    public Image getUserImage() {
        try {
            return new Image(imagePath);
        } catch (Exception e) {
            return new Image("images/event-template.jpg"); // Fallback image
        }
    }

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return firstName;
    }
    public String getLast_name() {
        return lastName;
    }
    public void assignCoordinator(Event event) {
        this.coordinatedEvents.add(event);
    }
}