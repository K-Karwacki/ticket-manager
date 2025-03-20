package dk.easv.ticketmanager.be;

import jakarta.persistence.*;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Event")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "image_path")
  private String imagePath;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "time")
  private LocalTime time;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location location;

  @Column(name = "normal_ticket_amount")
  private int normalTicketAmount;

  @Column(name = "vip_ticket_amount")
  private int vipTicketAmount;

  @ManyToMany
  @JoinTable(
          name = "EventCoordinator",
          joinColumns = @JoinColumn(name = "event_id"),
          inverseJoinColumns = @JoinColumn(name = "coordinator_id")
  )
  private Set<User> coordinators = new HashSet<>();

  // Constructors, getters, setters (unchanged except for normalTicketAmount and vipTicketAmount)
  public int getNormalTicketAmount() {
    return normalTicketAmount;
  }

  public void setNormalTicketAmount(int normalTicketAmount) {
    this.normalTicketAmount = normalTicketAmount;
  }

  public int getVipTicketAmount() {
    return vipTicketAmount;
  }

  public void setVipTicketAmount(int vipTicketAmount) {
    this.vipTicketAmount = vipTicketAmount;
  }

  public Set<User> getCoordinators() {
    return coordinators;
  }

  public void setCoordinators(Set<User> coordinators) {
    this.coordinators = coordinators;
  }

  public Image getImage() {
    try {
      return new Image(imagePath);
    } catch (Exception e) {
      return new Image("images/event-template.jpg"); // Fallback image
    }
  }

  public Location getLocation() {
    return location;
  }
  public void setLocation(Location location) {
    this.location = location;
  }
  public LocalDate getDate() {
    return date;
  }
  public void setDate(LocalDate date) {
    this.date = date;
  }
  public LocalTime getTime() {
    return time;
  }
  public void setTime(LocalTime time) {
    this.time = time;
  }
  public long getId() {
    return id;
  }

  public int getNormal_ticket_amount() {
    return normalTicketAmount;
  }
  public void setNormal_ticket_amount(int normal_ticket_amount) {
    this.normalTicketAmount = normal_ticket_amount;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getImagePath() {
    return imagePath;
  }
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public int getVip_ticket_amount() {
    return vipTicketAmount;
  }
  public void assignCoordinatorToEvent(User user) {
    coordinators.add(user);
  }
  public void removeCoordinatorFromEvent(User user) {
    coordinators.remove(user);
  }
  public Set<User> getAssignedCoordinators() {
    return coordinators;
  }
}
