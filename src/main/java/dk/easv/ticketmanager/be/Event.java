package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.gui.models.LocationModel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Event")
public class Event {
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
  private String time;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location location;

  @ManyToMany(mappedBy = "coordinatingEvents")
  private Set<User> coordinators;


  public Event(){};

  public long getID()
  {
    return ID;
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

  public String getTime()
  {
    return time;
  }

  public void setTime(String time)
  {
    this.time = time;
  }

  public LocationModel getLocation()
  {
    return location;
  }

  public void setLocation(Location location)
  {
    this.location = location;
  }

  public Set<User> getCoordinators()
  {
    return coordinators;
  }

  public void setCoordinators(Set<User> coordinators)
  {
    this.coordinators = coordinators;
  }

  public void assignCoordinatorToEvent(User user)
  {
    coordinators.add(user);
  }

  public void removeCoordinatorFromEvent(User user)
  {
    coordinators.remove(user);
  }

}
