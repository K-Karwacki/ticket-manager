package dk.easv.ticketmanager.be;

import jakarta.persistence.*;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Event")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long ID;

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

  @OneToOne
  @JoinColumn(name = "location_id", referencedColumnName = "ID")
  private Location location;

  @Column(name = "normal_ticket_amount")
  private int normalTicketAmount;

  @Column(name = "vip_ticket_amount")
  private int vipTicketAmount;

  @Column(name = "normal_ticket_amount_limit")
  private int normalTicketAmountLimit;

  @Column(name = "vip_ticket_amount_limit")
  private int vipTicketAmountLimit;

  // Default constructor for event entity
  public Event() {
    this.ID = 0;
    this.name = "Default name";
    this.description = "Default description";
    this.imagePath = "images/event-template.jpg";
    this.date = LocalDate.now();
    this.time = LocalTime.now();
    this.location = new Location(); // Assuming Location has a default constructor
    this.normalTicketAmount = 100;
    this.vipTicketAmount = 100;
    this.normalTicketAmountLimit = 200;
    this.vipTicketAmountLimit = 150;
  }

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
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

  public Image getImage() {
    return new Image(this.getImagePath());
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

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

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

  public int getNormalTicketAmountLimit() {
    return normalTicketAmountLimit;
  }

  public void setNormalTicketAmountLimit(int normalTicketAmountLimit) {
    this.normalTicketAmountLimit = normalTicketAmountLimit;
  }

  public int getVipTicketAmountLimit() {
    return vipTicketAmountLimit;
  }

  public void setVipTicketAmountLimit(int vipTicketAmountLimit) {
    this.vipTicketAmountLimit = vipTicketAmountLimit;
  }
}