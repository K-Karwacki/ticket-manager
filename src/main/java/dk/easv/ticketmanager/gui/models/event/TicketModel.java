package dk.easv.ticketmanager.gui.models.event;

import dk.easv.ticketmanager.dal.entities.Ticket;
import javafx.beans.property.*;

public class TicketModel
{
  private final SimpleLongProperty ID = new SimpleLongProperty();
  private final SimpleObjectProperty<EventModel> eventModel = new SimpleObjectProperty<>();
  private final SimpleStringProperty type = new SimpleStringProperty();
  private final SimpleStringProperty info = new SimpleStringProperty();
  private final SimpleDoubleProperty price = new SimpleDoubleProperty();

  public TicketModel(){
    ID.set(-1);
    eventModel.set(null);
    type.set(null);
    info.set(null);
    price.set(-1);
  }
  public TicketModel(Ticket ticket){
    ID.set(ticket.getID());
    eventModel.set(new EventModel(ticket.getEvent()));
    type.set(ticket.getType());
    info.set(ticket.getInfo());
    price.set(ticket.getPrice());
  }

  public long getID()
  {
    return ID.get();
  }


  public EventModel getEventModel()
  {
    return eventModel.get();
  }

  public SimpleObjectProperty<EventModel> eventModelProperty()
  {
    return eventModel;
  }

  public void setEventModel(EventModel eventModel)
  {
    this.eventModel.set(eventModel);
  }

  public String getType()
  {
    return type.get();
  }

  public SimpleStringProperty typeProperty()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type.set(type);
  }

  public String getInfo()
  {
    return info.get();
  }

  public SimpleStringProperty infoProperty()
  {
    return info;
  }

  public void setInfo(String info)
  {
    this.info.set(info);
  }

  public double getPrice()
  {
    return price.get();
  }

  public SimpleDoubleProperty priceProperty()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price.set(price);
  }
}
