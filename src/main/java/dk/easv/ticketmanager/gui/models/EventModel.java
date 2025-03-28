package dk.easv.ticketmanager.gui.models;

import javafx.beans.property.SimpleStringProperty;

public class EventModel
{
  private final long ID;
  private final SimpleStringProperty simpleStringProperty = new SimpleStringProperty();

  public EventModel(long id, String name)
  {
    this.ID = id;
    this.simpleStringProperty.set(name);
  }

  public SimpleStringProperty getSimpleStringProperty()
  {
    return simpleStringProperty;
  }

  public long getID()
  {
    return this.ID;
  }
}
