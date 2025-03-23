package dk.easv.ticketmanager.dal.implementations;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.dal.interfaces.ITicketRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TicketRepository implements ITicketRepository {
    @Override
    public void addCustomer(Customer customer) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addTicket(Ticket ticket) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Event managedEvent = em.merge(ticket.getEvent());
            ticket.setEvent(managedEvent);

            em.persist(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    @Override
    public int getAmountOfTicketsForEvent(Event event, String type) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("SELECT u FROM Ticket u WHERE u.event = :eventId and u.type = :ticketType", Ticket.class)
                .setParameter("eventId", event.getId())
                .setParameter("ticketType", type)
                .getResultList()
                .size();
    }

}
