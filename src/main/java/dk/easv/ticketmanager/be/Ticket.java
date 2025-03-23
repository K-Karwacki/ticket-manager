package dk.easv.ticketmanager.be;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "price")
    private double price;

    @Column(name = "type")
    private String type;

    @Column(name = "ticket_code")
    private String ticket_code;

    public Ticket(Event event, Customer customer, double price, String type, String ticket_code) {
        this.event = event;
        this.customer = customer;
        this.price = price;
        this.type = type;
        this.ticket_code = ticket_code;
    }

    public Ticket() {

    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
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
