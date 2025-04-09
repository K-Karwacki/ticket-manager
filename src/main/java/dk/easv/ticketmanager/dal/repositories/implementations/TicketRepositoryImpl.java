package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.dal.entities.Customer;
import dk.easv.ticketmanager.dal.entities.GeneratedTicket;
import dk.easv.ticketmanager.dal.entities.Ticket;
//import dk.easv.ticketmanager.dal.entities.TicketType;
import dk.easv.ticketmanager.dal.repositories.TicketRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository {

    @Override public Set<Ticket> getTicketsForEventID(long ID)
    {
        try(EntityManager em = JPAUtil.getEntityManager()) {
//            em.getTransaction().begin();
            return em.createQuery("select t from Ticket t where event_id =: id", Ticket.class).setParameter("id", ID).getResultStream().collect(
                Collectors.toSet());
        }
    }

    @Override public Set<Ticket> getGeneratedTicketsForEventID(long ID)
    {
        try(EntityManager em = JPAUtil.getEntityManager()){
            return em.createQuery("select gt from GeneratedTicket gt join Ticket t on gt.ticket_id = t.id where t.event_id = :id ", Ticket.class).setParameter("id", ID).getResultStream().collect(
                Collectors.toSet());
        }
    }

    @Override public boolean saveTicketForEventID(Ticket ticket, long ID)
    {
        return false;
    }

    @Override public boolean generateTicketForCustomer(Ticket ticket,
        Customer customer)
    {
//        try (EntityManager em = JPAUtil.getEntityManager()) {
//
//        }
        return false;
    }

    @Override public List<Ticket> getGeneratedTicketsForCustomerEmail(
        String email)
    {
        return null;
    }

    //    @Override
//    public boolean saveTicketType(TicketType ticketType) {
//        try(EntityManager em = JPAUtil.getEntityManager()){
//            em.getTransaction().begin();
//            em.merge(ticketType);
//            em.getTransaction().commit();
//            return true;
//        }
//    }

//    @Override
//    public boolean deleteTicketType(TicketType ticketType) {
//        try(EntityManager em = JPAUtil.getEntityManager()){
//            em.getTransaction().begin();
//            em.remove(ticketType);
//            em.getTransaction().commit();
//            return true;
//        }
//    }

//    @Override
//    public List<TicketType> getAllTicketTypesForTicket() {
//        return List.of();
//    }

    @Override
    public List<LocalDateTime> getAllDatesForPurchasedTickets() {
        try(EntityManager em = JPAUtil.getEntityManager()){
            return new ArrayList<>();
//            em.getTransaction().begin();
//            return em.createQuery("select issued_date from Ticket", LocalDateTime.class).getResultList();
        }
    }

    @Override public void saveGeneratedTicket(GeneratedTicket generatedTicket)
    {
        try(EntityManager em = JPAUtil.getEntityManager()){
            em.getTransaction().begin();
            em.persist(generatedTicket);
            em.flush();
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Ticket> getAll() {
        return List.of();
    }

    @Override
    public Optional<Ticket> getById(long id) {
        try(EntityManager em = JPAUtil.getEntityManager()){
            return Optional.of(em.find(Ticket.class, id));
        }
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
