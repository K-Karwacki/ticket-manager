package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class UserModel
{
  private final long ID;
  private final Role role;
  private final SimpleStringProperty firstName = new SimpleStringProperty();
  private final SimpleStringProperty lastName = new SimpleStringProperty();
  private final SimpleStringProperty email = new SimpleStringProperty();
  private final SimpleStringProperty phoneNumber = new SimpleStringProperty();

  public UserModel(long ID, Role role){
    this.ID = ID;
    this.role = role;
  }

  public UserModel(long ID, Role role, String firstName, String lastName, String email, String phoneNumber){
    this.ID = ID;
    this.role = role;
    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setPhoneNumber(phoneNumber);
  }

  public long getID()
  {
    return ID;
  }
  
  public void setFirstName(String firstName){
    this.firstName.set(firstName);
  }
  public String getFirstName(){
    return firstName.get();
  }
  public void setLastName(String lastName){
    this.lastName.set(lastName);
  }
  public String getLastName(){
    return lastName.get();
  }
  public void setEmail(String email){
    this.email.set(email);
  }
  public String getEmail(){
    return email.get();
  }
  public void setPhoneNumber(String phoneNumber){
    this.phoneNumber.set(phoneNumber);
  }
  public String getPhoneNumber(){
    return phoneNumber.get();
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (!(o instanceof UserModel userModel))
      return false;
    return getID() == userModel.getID() && role.equals(userModel.role)
        && Objects.equals(getFullName(), userModel.getFullName());
  }

  @Override public int hashCode()
  {
    return Objects.hash(getID());
  }
}
