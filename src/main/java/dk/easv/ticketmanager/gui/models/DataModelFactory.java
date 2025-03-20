package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;

public class DataModelFactory
{
  private final UserDataModel userDataModel = new UserDataModel();
  private final EventDataModel eventDataModel = new EventDataModel();

  public UserDataModel getUserDataModel()
  {
    return userDataModel;
  }

  public EventDataModel getEventDataModel()
  {
    return eventDataModel;
  }
}
