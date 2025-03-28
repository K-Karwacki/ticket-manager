package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import javafx.beans.property.SimpleStringProperty;

public class UserModel
{
  private final long ID;
  private final Role role;
  private final SimpleStringProperty fullName = new SimpleStringProperty();

  public UserModel(long ID, Role role, String fullName){
    this.ID = ID;
    this.role = role;
    setFullName(fullName);
  }

  public long getID()
  {
    return ID;
  }

  public String getFullName()
  {
    return fullName.get();
  }

  public SimpleStringProperty fullNameProperty()
  {
    return fullName;
  }

  public void setFullName(String fullName)
  {
    this.fullName.set(fullName);
  }
}
