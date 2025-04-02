package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventModel
{
  private final SimpleLongProperty ID = new SimpleLongProperty();
  private final SimpleStringProperty name = new SimpleStringProperty();
  private final SimpleStringProperty description = new SimpleStringProperty();
  private final SimpleStringProperty normal_ticket_amount = new SimpleStringProperty();
  private final SimpleStringProperty vip_ticket_amount = new SimpleStringProperty();
  private final SimpleStringProperty time = new SimpleStringProperty();
  private final SimpleStringProperty date = new SimpleStringProperty();
  private final SimpleStringProperty imagePath = new SimpleStringProperty();
//  private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
  private SimpleObjectProperty<Location> location = new SimpleObjectProperty<>();

  public EventModel(Event event){
    this.ID.set(event.getID());
    this.name.set(event.getName());
    this.description.set(event.getDescription());
    this.time.set(event.getTime());
    this.date.set(String.valueOf(event.getDate()));
    this.imagePath.set(event.getImagePath());
    this.location.set(event.getLocation());
  }

  public long getID()
  {
    return ID.get();
  }

  public SimpleLongProperty IDProperty()
  {
    return ID;
  }

  public String getName()
  {
    return name.get();
  }

  public SimpleStringProperty nameProperty()
  {
    return name;
  }

  public String getDescription()
  {
    return description.get();
  }

  public SimpleStringProperty descriptionProperty()
  {
    return description;
  }

  public String getNormal_ticket_amount()
  {
    return normal_ticket_amount.get();
  }

  public SimpleStringProperty normal_ticket_amountProperty()
  {
    return normal_ticket_amount;
  }

  public String getVip_ticket_amount()
  {
    return vip_ticket_amount.get();
  }

  public SimpleStringProperty vip_ticket_amountProperty()
  {
    return vip_ticket_amount;
  }

  public String getTime()
  {
    return time.get();
  }

  public SimpleStringProperty timeProperty()
  {
    return time;
  }

  public String getDate()
  {
    return date.get();
  }

  public SimpleStringProperty dateProperty()
  {
    return date;
  }

  public String getImagePath()
  {
    return imagePath.get();
  }

  public SimpleStringProperty imagePathProperty()
  {
    return imagePath;
  }

  public Location getLocation()
  {
    return location.get();
  }

  public SimpleObjectProperty<Location> locationProperty()
  {
    return location;
  }
}
