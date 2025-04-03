package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import jdk.dynalink.support.SimpleRelinkableCallSite;

import java.util.Objects;

public class UserModel
{
  private final SimpleLongProperty ID = new SimpleLongProperty();
  private final SimpleObjectProperty<Role> role = new SimpleObjectProperty<>();
  private final SimpleStringProperty fullName = new SimpleStringProperty();
  private final SimpleStringProperty name = new SimpleStringProperty();
  private final SimpleStringProperty lastName = new SimpleStringProperty();
  private final SimpleStringProperty email = new SimpleStringProperty();
  private final SimpleStringProperty phoneNumber = new SimpleStringProperty();
  private final SimpleStringProperty password = new SimpleStringProperty();
  private String loggedSessionToken;

  public UserModel(){
    ID.set(-1);
    role.set(null);
    fullName.set(null);
    name.set(null);
    lastName.set(null);
    email.set(null);
    phoneNumber.set(null);
    loggedSessionToken = null;
  }

  public UserModel(User user){
    ID.set(user.getId());
    role.set(user.getRole());
    fullName.set(user.getFullName());
    name.set(user.getFirstName());
    lastName.set(user.getLastName());
    email.set(user.getEmail());
    phoneNumber.set(user.getPhoneNumber());
    password.set(null);
    loggedSessionToken = null;
  }

  public String getName()
  {
    return name.get();
  }

  public String getLastName()
  {
    return lastName.get();
  }

  public void setLoggedSessionToken(String sessionToken) {
    this.loggedSessionToken = sessionToken;
  }

  public String getLoggedSessionToken() {
    return loggedSessionToken;
  }

  public SimpleLongProperty IDProperty()
  {
    return ID;
  }
  public SimpleObjectProperty<Role> roleProperty() {
    return role;
  }
  public SimpleStringProperty fullNameProperty(){
    return fullName;
  }
  public SimpleStringProperty emailProperty(){
    return email;
  }
  public SimpleStringProperty phoneNumberProperty(){
    return phoneNumber;
  }

    public void setFirstName(String firstName) {
    fullName.set(firstName);
    }

  public void setLastName(String lastName) {
    fullName.set(lastName);
  }

  public void setEmail(String email) {
    this.email.set(email);
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber.set(phoneNumber);
  }

  public void setPassword(String password)
  {
    this.password.set(password);
  }

  public String getPassword()
  {
    return password.get();
  }
}
