package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class UserModel
{
  private final SimpleLongProperty ID = new SimpleLongProperty();
  private final SimpleObjectProperty<Role> role = new SimpleObjectProperty<>();
  private final SimpleStringProperty fullName = new SimpleStringProperty();
  private final SimpleStringProperty email = new SimpleStringProperty();
  private final SimpleStringProperty phoneNumber = new SimpleStringProperty();
  private String loggedSessionToken;

  public UserModel(User user){
    ID.set(user.getId());
    role.set(user.getRole());
    fullName.set(user.getFullName());
    email.set(user.getEmail());
    phoneNumber.set(user.getPhoneNumber());
    loggedSessionToken = null;
  }

  public void setLoggedSessionToken(String sessionToken) {
    this.loggedSessionToken = sessionToken;
  }

  public String getLoggedSessionToken() {
    return loggedSessionToken;
  }

  public SimpleLongProperty getIDProperty()
  {
    return ID;
  }
  public SimpleObjectProperty<Role> getRoleProperty() {
    return role;
  }
  public SimpleStringProperty getFullNameProperty(){
    return fullName;
  }
  public SimpleStringProperty getEmailProperty(){
    return email;
  }
  public SimpleStringProperty getPhoneNumberProperty(){
    return phoneNumber;
  }

}
