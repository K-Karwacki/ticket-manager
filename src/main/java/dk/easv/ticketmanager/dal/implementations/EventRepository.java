package dk.easv.ticketmanager.dal.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.interfaces.IEventRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepository implements IEventRepository {

  // Remove class-level EntityManager
  // Always create new EntityManager per operation

  @Override
  public List<Event> getAll() {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      return em.createQuery("select e from Event e", Event.class).getResultList();
    }
  }

  @Override
  public Event getById(long id) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      return em.find(Event.class, id);
    }
  }

  @Override
  public void save(Event event) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      EntityTransaction tx = em.getTransaction();
      try {
        tx.begin();
        em.persist(event.getLocation());
        em.persist(event);
        tx.commit();
      } catch (Exception e) {
        if (tx.isActive()) tx.rollback();
        throw e;
      }
    }
  }

  @Override
  public void delete(Event event) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      EntityTransaction tx = em.getTransaction();
      try {
        tx.begin();
        Event managedEvent = em.merge(event);
        em.remove(managedEvent);
        tx.commit();
      } catch (Exception e) {
        if (tx.isActive()) tx.rollback();
        throw e;
      }
    }
  }

  @Override
  public List getAllLocations() {
    return new ArrayList<>();
  }

  public void assignCoordinatorToEvent(Event event, User user) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      EntityTransaction tx = em.getTransaction();
      try {
        tx.begin();
        event.assignCoordinatorToEvent(user);
        em.merge(event);
        tx.commit();
      } catch (Exception e) {
        if (tx.isActive()) tx.rollback();
        throw e;
      }
    }
  }

  public void dissociateEventFromCoordinator(Event event, User user) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      EntityTransaction tx = em.getTransaction();
      try {
        tx.begin();
        event.removeCoordinatorFromEvent(user);
        em.merge(event);
        tx.commit();
      } catch (Exception e) {
        if (tx.isActive()) tx.rollback();
        throw e;
      }
    }
  }
}