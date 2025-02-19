package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Event
{
  private int eventId;
  private String eventName;
  private String eventDescription;
  private Image eventImage;
  private Date eventDate;
  private String eventLocation;
  private int normalTicketsAmount;
  private int vipTicketsAmount;

  //Default constructor for event entity
  public Event(){
    this.eventId = 0;
    this.eventName = "Default name";
    this.eventDescription = "Default description";
//    this.eventImage = new Image(
//        String.valueOf(Main.class.getResource("event-template.jpg")));
    this.eventDate = new Date();
    this.eventLocation = "Default location";
    this.normalTicketsAmount = 100;
    this.vipTicketsAmount = 100;
  }

  public Event(int eventId, String eventName, String eventDescription, String imagePath, Date eventDate, String eventLocation, int normalTicketsAmount, int vipTicketsAmount){
    this.eventId = eventId;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventImage = new Image(imagePath);
    this.eventDate = eventDate;
    this.eventLocation = eventLocation;
    this.normalTicketsAmount = normalTicketsAmount;
    this.vipTicketsAmount = vipTicketsAmount;

  }

  public void setEventId(int eventId)
  {
    this.eventId = eventId;
  }

  public void setEventName(String eventName)
  {
    this.eventName = eventName;
  }

  public void setEventDescription(String eventDescription)
  {
    this.eventDescription = eventDescription;
  }

  public void setEventImageFromPath(String path)
  {
    this.eventImage = new Image(path);
  }

  public void setEventLocation(String eventLocation)
  {
    this.eventLocation = eventLocation;
  }

  public void setEventDate(Date eventDate)
  {
    this.eventDate = eventDate;
  }

  public void setNormalTicketsAmount(int normalTicketsAmount)
  {
    this.normalTicketsAmount = normalTicketsAmount;
  }

  public void setVipTicketsAmount(int vipTicketsAmount)
  {
    this.vipTicketsAmount = vipTicketsAmount;
  }


  public Date getEventDate()
  {
    return eventDate;
  }

  public Image getEventImage()
  {
    return eventImage;
  }

  public int getEventId()
  {
    return eventId;
  }

  public int getNormalTicketsAmount()
  {
    return normalTicketsAmount;
  }

  public int getVipTicketsAmount()
  {
    return vipTicketsAmount;
  }

  public String getEventDescription()
  {
    return eventDescription;
  }

  public String getEventLocation()
  {
    return eventLocation;
  }

  public String getEventName()
  {
    return eventName;
  }
}
