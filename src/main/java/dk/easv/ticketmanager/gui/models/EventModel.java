package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.EventImage;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class EventModel {
  private final SimpleLongProperty ID = new SimpleLongProperty();
  private final SimpleStringProperty name = new SimpleStringProperty();
  private final SimpleStringProperty description = new SimpleStringProperty();
  private final SimpleObjectProperty<LocalTime> time = new SimpleObjectProperty<>(  );
  private final SimpleObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<ImagePattern> image = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<LocationModel> location = new SimpleObjectProperty<>();
  private final SimpleObjectProperty<EventImage> eventImage = new SimpleObjectProperty<>();


  public EventModel(){
    this.ID.set(-1);
    this.name.set(null);
    this.description.set(null);
    this.time.set(null);
    this.date.set(null);
    this.image.set(null);
    this.location.set(new LocationModel());
  }

  public EventModel(Event event){
    this.ID.set(event.getID());
    this.name.set(event.getName());
    this.description.set(event.getDescription());
    this.time.set(event.getTime());
    this.date.set(event.getDate());
    this.eventImage.set(event.getEventImage());
    this.image.set(new ImagePattern(ImageConverter.convertToImage(event.getEventImage().getImageData())));
    this.location.set(new LocationModel(event.getLocation()));
//    this.eventImage.set(event.getEventImage());
  }

  public long getID()
  {
    return this.ID.get();
  }
  public String getName() { return this.name.get(); }
  public void setName(String name){
    this.name.set(name);
  }
  public void setDescription(String description){
    this.description.set(description);
  }
  public void setTime(LocalTime time){
    this.time.set(time);
  }
  public void setDate(LocalDate date){
    this.date.set(date);
  }

//  public void setImage(Image image) {
//    this.image.set(image);
//  }

  public SimpleStringProperty nameProperty(){
    return this.name;
  }
  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }
  public SimpleObjectProperty<LocalTime> timeProperty(){
    return this.time;
  }
  public SimpleObjectProperty<LocalDate> dateProperty(){
    return this.date;
  }
  public SimpleObjectProperty<ImagePattern> imageProperty(){
    return this.image;
  }
  public void setLocation(LocationModel location){
    this.location.set(location);
  }

  public LocationModel getLocation() {
    return location.get();
  }

  public SimpleObjectProperty<LocationModel> locationProperty()
  {
    return location;
  }

  public EventImage getEventImage()
  {
    return eventImage.get();
  }

  public void setEventImage(EventImage eventImage) {
    this.eventImage.set(eventImage);
  }

  //  public EventImage getEventImage() {
//    return image.get();
//  }

  public ImagePattern getImage(){return image.get();}

  public void setImage(Image image){
    this.image.set(new ImagePattern(image));
  }

}
