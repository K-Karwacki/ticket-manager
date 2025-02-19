package dk.easv.ticketmanager.be;

import dk.easv.ticketmanager.utils.DateTimeUtil;
import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class Event
{
  private int eventId;
  private String eventName;
  private String eventDescription;
  private Image eventImage;
  private String eventDate;
  private String eventTime;
  private String eventLocation;
  private int normalTicketsAmount;
  private int vipTicketsAmount;

  //Default constructor for event entity
  public Event(){
    this.eventId = 0;
    this.eventName = "Default name";
    this.eventDescription = "Default description";
    this.eventImage = new Image(String.valueOf(Main.class.getResource("images/event-template.jpg")));
    LocalDateTime now = LocalDateTime.now();
    this.eventDate = DateTimeUtil.formatDate(now);
    this.eventTime = DateTimeUtil.formatTime(now);
    this.eventLocation = "123 Main Street, Anytown, USA, 12345";
    this.normalTicketsAmount = 100;
    this.vipTicketsAmount = 100;
  }

  public Event(int eventId, String eventName, String eventDescription, String imagePath, LocalDateTime eventDate, String eventLocation, int normalTicketsAmount, int vipTicketsAmount){
    this.eventId = eventId;
    this.eventName = eventName;
    this.eventDescription = eventDescription;
    this.eventImage = new Image(imagePath);
    this.eventDate = DateTimeUtil.formatDate(eventDate);
    this.eventTime = DateTimeUtil.formatTime(eventDate);
    this.eventLocation = eventLocation;
    this.normalTicketsAmount = normalTicketsAmount;
    this.vipTicketsAmount = vipTicketsAmount;
  }

  public void setEventDate(String eventDate)
  {
    this.eventDate = eventDate;
  }

  public void setEventTime(String eventTime)
  {
    this.eventTime = eventTime;
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

  public void setEventDate(LocalDateTime eventDate)
  {
    this.eventDate = DateTimeUtil.formatDate(eventDate);
  }


  public void setNormalTicketsAmount(int normalTicketsAmount)
  {
    this.normalTicketsAmount = normalTicketsAmount;
  }

  public void setVipTicketsAmount(int vipTicketsAmount)
  {
    this.vipTicketsAmount = vipTicketsAmount;
  }


  public String getEventDate()
  {
    return eventDate;
  }

  public String getEventTime()
  {
    return eventTime;
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
