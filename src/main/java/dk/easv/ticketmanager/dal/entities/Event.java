package dk.easv.ticketmanager.dal.entities;

import dk.easv.ticketmanager.gui.models.event.EventModel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long ID;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "event_image_id")
  private EventImage eventImage;

//  @Column (name = "image_data")
//  private byte[] imageData;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "time")
  private LocalTime time;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "location_id")
  private Location location;

  @ManyToMany
  @JoinTable(
      name = "event_coordinators",
      joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "coordinator_id")
  )
  private Set<User> coordinators = new HashSet<>();

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private final Set<Ticket> tickets = new HashSet<>();


  public Event(){};

  public Event(EventModel eventModel) {
    setID(eventModel.getID());
    setName(eventModel.nameProperty().get());
    setDate(eventModel.dateProperty().get());
    setDescription(eventModel.descriptionProperty().get());
    setTime(eventModel.timeProperty().get());
//    setCoordinators();
//    setEventImage(eventModel.getEventImage());

  }

  public long getID()
  {
    return ID;
  }
  public void setID(long ID){
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


  public void setEventImage(EventImage eventImage)
  {
    this.eventImage = eventImage;
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

  public EventImage getEventImage()
  {
    return eventImage;
  }

  public void addTicket(Ticket ticket){
    this.tickets.add(ticket);
  }

  public Set<Ticket> getTickets()
  {
    return tickets;
  }
}
