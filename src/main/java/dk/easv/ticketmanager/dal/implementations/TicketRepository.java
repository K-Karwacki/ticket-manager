package dk.easv.ticketmanager.dal.implementations;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.dal.interfaces.ITicketRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

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
            TicketType ticketType = em.merge(ticket.getType());
            ticket.setType(ticketType);
            em.persist(ticket);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
    }


    @Override
    public int getAmountOfTicketsForEvent(Event event, String type) {
//        EntityManager em = JPAUtil.getEntityManager();
//        return em.createQuery("SELECT u FROM Ticket u WHERE u.event = :eventId and u.type = :ticketType", Ticket.class)
//                .setParameter("eventId", event.getId())
//                .setParameter("ticketType", type)
//                .getResultList()
//                .size();
        return 0;
    }

    public void addTicketType(TicketType ticketType) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        Event event = em.merge(ticketType.getEvent());
        ticketType.setEvent(event);
        em.persist(ticketType);
        em.getTransaction().commit();
        em.close();
    }

    public List<TicketType> getAllTicketTypes() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("select e from TicketType e", TicketType.class).getResultList();

    }

}
