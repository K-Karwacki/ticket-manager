package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.dal.entities.Role;
import dk.easv.ticketmanager.dal.entities.User;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

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
  private final SimpleObjectProperty<Image> image = new SimpleObjectProperty<>();

  private String loggedSessionToken;

  public UserModel(){
    ID.set(-1);
    role.set(null);
    fullName.set(null);
    name.set(null);
    lastName.set(null);
    email.set(null);
    phoneNumber.set(null);
    image.set(null);
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
    image.set(user.getUserImage());
    password.set(null);
    loggedSessionToken = null;
  }

  public long getID()
  {
    return ID.get();
  }

  public Role getRole()
  {
    return role.get();
  }

  public SimpleObjectProperty<Role> roleProperty()
  {
    return role;
  }

  public void setRole(Role role)
  {
    this.role.set(role);
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

  public String getName()
  {
    return name.get();
  }

  public SimpleStringProperty nameProperty()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name.set(name);
  }

  public String getLastName()
  {
    return lastName.get();
  }

  public SimpleStringProperty lastNameProperty()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName.set(lastName);
  }

  public String getEmail()
  {
    return email.get();
  }

  public SimpleStringProperty emailProperty()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email.set(email);
  }

  public String getPhoneNumber()
  {
    return phoneNumber.get();
  }

  public SimpleStringProperty phoneNumberProperty()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber.set(phoneNumber);
  }

  public String getPassword()
  {
    return password.get();
  }

  public SimpleStringProperty passwordProperty()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password.set(password);
  }

  public String getLoggedSessionToken()
  {
    return loggedSessionToken;
  }

  public void setLoggedSessionToken(String loggedSessionToken)
  {
    this.loggedSessionToken = loggedSessionToken;
  }

  public Image getImage()
  {
    return image.get();
  }

  public SimpleObjectProperty<Image> imageProperty(){
    return image;
  }
}
