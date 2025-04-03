package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.repositories.TicketRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryImpl implements TicketRepository {


    @Override
    public boolean saveTicketType(TicketType ticketType) {
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            em.merge(ticketType);
            em.getTransaction().commit();
            return true;
        }
    }

    @Override
    public boolean deleteTicketType(TicketType ticketType) {
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            em.remove(ticketType);
            em.getTransaction().commit();
            return true;
        }
    }

    @Override
    public List<TicketType> getAllTicketTypesForTicket() {
        return List.of();
    }

    @Override
    public List<LocalDateTime> getAllDatesForPurchasedTickets() {
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            return em.createQuery("select issued_date from Ticket", LocalDateTime.class).getResultList();
        }
    }

    @Override
    public List<Ticket> getAll() {
        return List.of();
    }

    @Override
    public Optional<Ticket> getById(long id) {
        return null;
    }

    @Override
    public Ticket save(Ticket entity) {
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public boolean delete(Ticket entity) {
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override public Ticket update(Ticket newEntity)
    {
        return null;
    }
}
