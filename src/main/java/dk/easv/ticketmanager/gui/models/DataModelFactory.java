package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;

public class DataModelFactory
{
  private final UserDataModel userDataModel = new UserDataModel();
  private final EventDataModel eventDataModel = new EventDataModel();
  private static final TicketDataModel ticketDataModel = new TicketDataModel();

  public UserDataModel getUserDataModel()
  {
    return userDataModel;
  }

  public EventDataModel getEventDataModel()
  {
    return eventDataModel;
  }

  public static TicketDataModel getTicketDataModel(){return ticketDataModel;}

  public void loadModels(){
    userDataModel.load();
    eventDataModel.load();
    ticketDataModel.load();
  }
}
