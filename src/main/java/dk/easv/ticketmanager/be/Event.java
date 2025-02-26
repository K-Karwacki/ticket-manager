package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.utils.DateTimeUtil;
import jakarta.persistence.*;
import javafx.scene.image.Image;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Event")
public class Event
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ID")
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
  @Column(name = "location")
  @OneToOne(targetEntity = )
  private Location location;

  private int normal_ticket_amount;
  private int vip_ticket_amount;
  private int normal_ticket_amount_limit;
  private int vip_ticket_amount_limit;

  //Default constructor for event entity
  public Event(){
    this.ID = 0;
    this.name = "Default name";
    this.description = "Default description";
    this.imagePath = "images/event-template.jpg";
    this.dateTime = new Timestamp();
    this.location = "123 Main Street, Anytown, USA, 12345";
    this.normal_ticket_amount = 100;
    this.vip_ticket_amount = 100;
    this.normal_ticket_amount_limit = 200;
    this.vip_ticket_amount_limit = 150;
  }

  public int getID()
  {
    return ID;
  }

  public void setID(int ID)
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

  public String getImage_path()
  {
    return image_path;
  }

  public void setImage_path(String image_path)
  {
    this.image_path = image_path;
  }

  public Image getImage(){
    return new Image(this.getImage_path());
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getTime()
  {
    return time;
  }

  public void setTime(String time)
  {
    this.time = time;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
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

  public int getNormal_ticket_amount_limit()
  {
    return normal_ticket_amount_limit;
  }

  public void setNormal_ticket_amount_limit(int normal_ticket_amount_limit)
  {
    this.normal_ticket_amount_limit = normal_ticket_amount_limit;
  }

  public int getVip_ticket_amount_limit()
  {
    return vip_ticket_amount_limit;
  }

  public void setVip_ticket_amount_limit(int vip_ticket_amount_limit)
  {
    this.vip_ticket_amount_limit = vip_ticket_amount_limit;
  }
}
