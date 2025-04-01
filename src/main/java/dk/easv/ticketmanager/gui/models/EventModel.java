package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventModel
{
  private final long ID;
  private final SimpleStringProperty name = new SimpleStringProperty();
  private final SimpleStringProperty description = new SimpleStringProperty();
  private final SimpleStringProperty normal_ticket_amount = new SimpleStringProperty();
  private final SimpleStringProperty vip_ticket_amount = new SimpleStringProperty();
  private final SimpleStringProperty time = new SimpleStringProperty();
  private final SimpleStringProperty date = new SimpleStringProperty();
  private final SimpleStringProperty imagePath = new SimpleStringProperty();
//  private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();
  private LocationModel location;

  public EventModel(Event event){
    this.ID = event.getID();
    this.name.set(event.getName());
    this.description.set(event.getDescription());
//    this.normal_ticket_amount.set(String.valueOf(event.get));
//    this.vip_ticket_amount.set(String.valueOf(vip_ticket_amount));
    this.time.set(event.getTime());
    this.date.set(String.valueOf(event.getDate()));
    this.imagePath.set(event.getImagePath());
//    this.image.set(image);
    this.location = new LocationModel(event.getLocation());
  }
//
//  public EventModel(long id, String name, String description, int normal_ticket_amount, int vip_ticket_amount, String time, LocalDate date, String imagePath, LocationModel location)
//  {
//    this.ID = id;
//    this.name.set(name);
//    this.description.set(description);
//    this.normal_ticket_amount.set(String.valueOf(normal_ticket_amount));
//    this.vip_ticket_amount.set(String.valueOf(vip_ticket_amount));
//    this.time.set(time);
//    this.date.set(date.toString());
//    this.imagePath.set(imagePath);
////    this.image.set(image);
//    this.location = location;
//  }

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
  public void setNormal_ticket_amount(String normal_ticket_amount){
    this.normal_ticket_amount.set(normal_ticket_amount);
  }
  public void setVip_ticket_amount(String vip_ticket_amount){
    this.vip_ticket_amount.set(vip_ticket_amount);
  }
  public void setTime(String time){
    this.time.set(time);
  }
  public void setDate(LocalDate date){
    this.date.set(String.valueOf(date));
  }
//  public void setImage(Image image){
//    this.image.set(image);
//  }

  public SimpleStringProperty nameProperty(){
    return this.name;
  }
  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }
  public SimpleStringProperty normalTicketAmountProperty(){
    return this.normal_ticket_amount;
  }
  public SimpleStringProperty vipTicketAmountProperty(){
    return this.vip_ticket_amount;
  }
  public SimpleStringProperty timeProperty(){
    return this.time;
  }
  public SimpleStringProperty dateProperty(){
    return this.date;
  }
//  public SimpleObjectProperty<Image> imageProperty(){
//    return this.image;
//  }
  public SimpleStringProperty imagePathProperty(){
    return this.imagePath;
  }
  public void setImagePath(String imagePath){
    this.imagePath.set(imagePath);
  }
  public void setLocation(LocationModel location){
    this.location = location;
  }

  public LocationModel getLocation() {
    return location;
  }

  public Image getImage() {
    return new Image(this.imagePath.get());
  }

}
