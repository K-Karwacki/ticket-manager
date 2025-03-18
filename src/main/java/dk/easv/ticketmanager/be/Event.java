package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.utils.DateTimeUtil;
import jakarta.persistence.*;
import javafx.scene.image.Image;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Event")
public class Event
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location location;

  @Column(name = "normal_ticket_amount")
  private int normal_ticket_amount;
  private int vip_ticket_amount;

  //Default constructor for event entity
  public Event(){
    this.ID = 0;
    this.name = "Default name";
    this.description = "Default description";
    this.imagePath = "images/event-template.jpg";
    this.date = LocalDate.now();
    this.time = LocalTime.now();
    this.location = new Location();
    this.normal_ticket_amount = 0;
    this.vip_ticket_amount = 0;
  }

  public Event(String name, String description, String imagePath, LocalDate date, LocalTime time, Location location){
    this.name = name;
    this.description = description;
    this.imagePath = imagePath;
    this.date = date;
    this.time = time;
    this.location = location;
  }

  public long getID()
  {
    return ID;
  }

  public void setID(long ID)
  {
    this.ID = ID;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getImagePath()
  {
    return imagePath;
  }

  public void setImagePath(String imagePath)
  {
    this.imagePath = imagePath;
  }

  public LocalDate getDate()
  {
    return date;
  }

  public void setDate(LocalDate date)
  {
    this.date = date;
  }

  public LocalTime getTime()
  {
    return time;
  }

  public void setTime(LocalTime time)
  {
    this.time = time;
  }

  public Location getLocation()
  {
    return location;
  }

  public void setLocation(Location location)
  {
    this.location = location;
  }

  public int getNormal_ticket_amount()
  {
    return normal_ticket_amount;
  }

  public void setNormal_ticket_amount(int normal_ticket_amount)
  {
    this.normal_ticket_amount = normal_ticket_amount;
  }

  public int getVip_ticket_amount()
  {
    return vip_ticket_amount;
  }

  public void setVip_ticket_amount(int vip_ticket_amount)
  {
    this.vip_ticket_amount = vip_ticket_amount;
  }

  public Image getImage() {
    return new Image(imagePath);
  }
}
