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

    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String hashedPassword;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany
    @JoinTable(
        name = "EventCoordinator",
        joinColumns = @JoinColumn(name = "coordinator_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private final Set<Event> coordinatingEvents = new HashSet<>();

    // Default constructor
    public User() {
        this.firstName = null;
        this.lastName = null;
        this.role = null;
        this.email = null;
        this.hashedPassword = null;
        // Minimal defaults
    }

//    // Parameterized constructor
//    public User(long id, String firstName, String lastName, String email, String hashedPassword, String phoneNumber, String imagePath, Role role) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.hashedPassword = hashedPassword;
//        this.phoneNumber = phoneNumber;
//        this.imagePath = imagePath;
//        this.role = role;
//    }
//
//    public User(String firstName, String lastName, String email, String hashedPassword, String phone, String imagePath, Role role)
//    {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.hashedPassword = hashedPassword;
//        this.phoneNumber = phone;
//        this.imagePath = imagePath;
//        this.role = role;
//    }

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

    // Other getters/setters unchanged

    public Set<Event> getCoordinatingEvents() {
        return coordinatingEvents;
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


    public void assignEventToCoordinator(Event event) {
        this.coordinatingEvents.add(event);
    }

    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

  public String getFullName()
  {
      return firstName + " " + lastName;
  }

    public void setEmail(String email)
    {
        this.email = email;
    }
}