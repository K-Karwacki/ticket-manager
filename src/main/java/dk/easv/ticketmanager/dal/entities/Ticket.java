package dk.easv.ticketmanager.dal.entities;

import dk.easv.ticketmanager.gui.models.event.TicketModel;
import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private Double price;

    @Column(name = "info")
    private String info;


    public Ticket(Event event, Customer customer, double price, String type, String ticket_code) {
        this.type = type;
    }

    public Ticket() {
//        this.issued_date = LocalDateTime.now();
    }

    public Ticket(TicketModel ticketModel){
        setType(ticketModel.getType());
        setPrice(ticketModel.getPrice());
        setInfo(ticketModel.getInfo());
    }

    public long getID()
    {
        return ID;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}

