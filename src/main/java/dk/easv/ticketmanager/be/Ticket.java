package dk.easv.ticketmanager.be;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private TicketType type;

    @Column(name = "ticket_code")
    private String ticket_code;

    public Ticket(Event event, Customer customer, double price, TicketType type, String ticket_code) {
        this.customer = customer;
        this.type = type;
        this.ticket_code = ticket_code;
    }

    public Ticket() {

    }

    public Event getEvent() {
        return type.getEvent();
    }
    public double getPrice() {
        return type.getPrice();
    }
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public long getId() {
        return id;
    }
    public void setTicketCode(String ticket_code) {
        this.ticket_code = ticket_code;
    }
    public String getTicketCode() {
        return ticket_code;
    }
}

