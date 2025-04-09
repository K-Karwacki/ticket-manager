package dk.easv.ticketmanager.dal.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "generated_ticket")
public class GeneratedTicket
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long ID;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "ticket_id")
  private Ticket ticket;

  @Column(name = "bar_code")
  private UUID barCode;

  public GeneratedTicket(){}

  public long getID()
  {
    return ID;
  }

  public Ticket getTicket()
  {
    return ticket;
  }

  public void setTicket(Ticket ticket)
  {
    this.ticket = ticket;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  public UUID getBarCode()
  {
    return barCode;
  }

  public void setBarCode(UUID barCode)
  {
    this.barCode = barCode;
  }
}
