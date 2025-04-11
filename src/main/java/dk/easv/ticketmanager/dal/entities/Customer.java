package dk.easv.ticketmanager.dal.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<GeneratedTicket> generatedTickets = new ArrayList<>();




    public Customer() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() { return getId(); }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    public void setId(int id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }

    public List<GeneratedTicket> getGeneratedTickets()
    {
        return generatedTickets;
    }

    public void addAllGeneratedTickets(Collection<GeneratedTicket> generatedTickets){
        this.generatedTickets.addAll(generatedTickets);
    }
}
