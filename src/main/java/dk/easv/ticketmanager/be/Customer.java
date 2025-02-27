package dk.easv.ticketmanager.be;

public class Customer {
    private long ID;
    private String firstName;
    private String lastName;
    private String email;
    private int phoneNumber;



public Customer() {
    this.ID = 0;
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.phoneNumber = 0;
}

public Customer(long id, String firstName, String lastName, String email, int phoneNumber) {
    this.ID = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    }

    public long getID()
    {
        return ID;
    }

    public void setID(long ID)
    {
        this.ID = ID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
