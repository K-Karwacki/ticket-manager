package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.bll.AuthenticationService;
import dk.easv.ticketmanager.bll.ImageConverter;
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

    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Lob
    @Column(name = "image")
    private byte[] imageData;

    @ManyToMany(mappedBy = "coordinators")
    private Set<Event> coordinatedEvents = new HashSet<>();

    // Default constructor
    public User() {
        // Minimal defaults
    }
    public User(String firstName, String lastName, String email, String password, String phone, byte[] imageData, Role role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = AuthenticationService.hashPassword(password);
         this.phoneNumber = phone;
        this.imageData = imageData;
        this.role = role;
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

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = AuthenticationService.hashPassword(password);}

    public byte[] getImagePath() {return imageData;}
    public void setImagePath(byte[] imageData) {this.imageData = imageData;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}


    // Other getters/setters unchanged

    public Set<Event> getCoordinatedEvents() {
        return coordinatedEvents;
    }

    public void setCoordinatedEvents(Set<Event> coordinatedEvents) {
        this.coordinatedEvents = coordinatedEvents;
    }

    public Image getUserImage() {
        try {
            return ImageConverter.convertToImage(imageData);
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
    public String getPhoneNumber() { return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}


    public void assignEventToCoordinator(Event event) {
        this.coordinatedEvents.add(event);
    }

    @Override public String toString()
    {
        return firstName + " " + lastName + " " + role.toString();
    }

}