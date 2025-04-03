package dk.easv.ticketmanager.be;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private TicketType type;

    @Column(name = "ticket_code")
    private String ticket_code;

    @Column(name = "issued_date")
    private LocalDateTime issued_date;

    public Ticket(Event event, Customer customer, double price, TicketType type, String ticket_code) {
        this.customer = customer;
        this.type = type;
        this.ticket_code = ticket_code;
        this.issued_date = LocalDateTime.now();
    }

    public Ticket() {
        this.issued_date = LocalDateTime.now();
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
    public long getID() {
        return ID;
    }
    public void setTicketCode(String ticket_code) {
        this.ticket_code = ticket_code;
    }
    public String getTicketCode() {
        return ticket_code;
    }
    public LocalDateTime getIssuedDate() {
        return issued_date;
    }
}

