package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.gui.models.event.TicketModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class CustomerModel
{
  private final SimpleStringProperty firstName = new SimpleStringProperty();
  private final SimpleStringProperty lastName = new SimpleStringProperty();
  private final SimpleStringProperty email = new SimpleStringProperty();
  private final SimpleSetProperty<TicketModel> ownedTickets = new SimpleSetProperty<>();

  public CustomerModel(){
    this.firstName.set(null);
    this.lastName.set(null);
    this.email.set(null);
    this.ownedTickets.set(FXCollections.observableSet());
  }

  public CustomerModel(String firstName, String lastName, String email){
    this.firstName.set(firstName);
    this.lastName.set(lastName);
    this.email.set(email);
  }

  public String getFirstName()
  {
    return firstName.get();
  }

  public SimpleStringProperty firstNameProperty()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName.set(firstName);
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

  public ObservableSet<TicketModel> getOwnedTickets()
  {
    return ownedTickets.get();
  }

  public SimpleSetProperty<TicketModel> ownedTicketsProperty()
  {
    return ownedTickets;
  }

  public void setOwnedTickets(ObservableSet<TicketModel> ownedTickets)
  {
    this.ownedTickets.set(ownedTickets);
  }
}
