package dk.easv.ticketmanager.be;

public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;



public Customer() {
    this.id = 0;
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNumber = 0;
}

public Customer(int id, String firstName, String lastName, String email, int phoneNumber) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    }

public int getId() { return getId(); }
public String getFirstName() { return firstName; }
public String getLastName() { return lastName; }
public String getEmail() { return email; }
public int getPhoneNumber() {  return phoneNumber; }

public void setId(int id) { this.id = id; }
public void setFirstName(String firstName) { this.firstName = firstName; }
public void setLastName(String lastName) { this.lastName = lastName; }
public void setEmail(String email) { this.email = email; }
public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
}
