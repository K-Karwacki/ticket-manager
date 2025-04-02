package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class EventModel
{
  private final long ID;
  private final SimpleStringProperty name = new SimpleStringProperty();
  private final SimpleStringProperty description = new SimpleStringProperty();
  private final SimpleStringProperty time = new SimpleStringProperty();
  private final SimpleStringProperty date = new SimpleStringProperty();
  private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
  private LocationModel location;

  public EventModel(Event event){
    this.ID = event.getID();
    this.name.set(event.getName());
    this.description.set(event.getDescription());
    this.time.set(event.getTime());
    this.date.set(String.valueOf(event.getDate()));
    this.image.set(new Image(event.getImagePath()));
    this.location = new LocationModel(event.getLocation());
  }

  public long getID()
  {
    return this.ID;
  }
  public void setName(String name){
    this.name.set(name);
  }
  public void setDescription(String description){
    this.description.set(description);
  }
  public void setTime(String time){
    this.time.set(time);
  }
  public void setDate(LocalDate date){
    this.date.set(String.valueOf(date));
  }
  public void setImage(String imagePath){
    this.image.set(new Image(imagePath));
  }

  public SimpleStringProperty nameProperty(){
    return this.name;
  }
  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }
  public SimpleStringProperty timeProperty(){
    return this.time;
  }
  public SimpleStringProperty dateProperty(){
    return this.date;
  }
  public SimpleObjectProperty<Image> imageProperty(){
    return this.image;
  }
  public void setLocation(LocationModel location){
    this.location = location;
  }

  public LocationModel getLocation() {
    return location;
  }

  public SimpleObjectProperty<Image> getImage() {
    return image;
  }

}
