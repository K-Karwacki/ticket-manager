package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventModel
{
  private final SimpleLongProperty ID = new SimpleLongProperty();
  private final SimpleStringProperty name = new SimpleStringProperty();
  private final SimpleStringProperty description = new SimpleStringProperty();
  private final SimpleObjectProperty<LocalTime> time = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<ImagePattern> image = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<LocationModel> location = new SimpleObjectProperty<>();

  public EventModel(){
    this.ID.set(-1);
    this.name.set(null);
    this.description.set(null);
    this.time.set(null);
    this.date.set(null);
    this.image.set(null);
    this.location.set(new LocationModel());
  }

  // Mapping constructor from Event
  public EventModel(Event event){
    this.ID.set(event.getID());
    this.name.set(event.getName());
    this.description.set(event.getDescription());
    this.time.set(LocalTime.parse(event.getTime()));
    this.date.set(event.getDate());
//    this.image.set(new ImagePattern(event.getImage()));
    this.location.set(event.getLocation());
  }

  // GUI constructor for creating event f.e
  public EventModel(String name, String description, LocalTime time, LocalDate date, Image image, LocationModel locationModel){
    this.name.set(name);
    this.description.set(description);
    this.time.set(time);
    this.date.set(date);
    this.image.set(new ImagePattern(image));
    this.location.set(locationModel);
  }

  public long getID()
  {
    return ID.get();
  }

  public SimpleLongProperty IDProperty()
  {
    return ID;
  }

  public void setID(long ID)
  {
    this.ID.set(ID);
  }

  public String getName()
  {
    return name.get();
  }

  public SimpleStringProperty nameProperty()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name.set(name);
  }

  public String getDescription()
  {
    return description.get();
  }

  public SimpleStringProperty descriptionProperty()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description.set(description);
  }

  public LocalTime getTime()
  {
    return time.get();
  }

  public SimpleObjectProperty<LocalTime> timeProperty()
  {
    return time;
  }

  public void setTime(LocalTime time)
  {
    this.time.set(time);
  }

  public LocalDate getDate()
  {
    return date.get();
  }

  public SimpleObjectProperty<LocalDate> dateProperty()
  {
    return date;
  }

  public void setDate(LocalDate date)
  {
    this.date.set(date);
  }

  public Image getImage()
  {
    return image.get().getImage();
  }

  public SimpleObjectProperty<ImagePattern> imageProperty()
  {
    return image;
  }

  public void setImage(Image image)
  {
    this.image.set(new ImagePattern(image));
  }

  public LocationModel getLocation()
  {
    return location.get();
  }

  public SimpleObjectProperty<LocationModel> locationProperty()
  {
    return location;
  }

  public void setLocation(LocationModel locationModel)
  {
    this.location.set(locationModel);
  }
}
