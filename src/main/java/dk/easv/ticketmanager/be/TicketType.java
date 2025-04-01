package dk.easv.ticketmanager.be;

import jakarta.persistence.*;

@Entity
@Table(name = "TicketType")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "price")
    private double price;

    @Column(name = "name")
    private String name;

    public TicketType() {

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
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String ticketTypeName) {
        this.name = ticketTypeName;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
