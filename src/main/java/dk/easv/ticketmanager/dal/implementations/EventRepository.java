package dk.easv.ticketmanager.dal.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.interfaces.IEventRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepository implements IEventRepository
{


  @Override
  public List<Event> getAll() {
    EntityManager em = JPAUtil.getEntityManager();
    return em.createQuery("select e from Event e", Event.class).getResultList();
  }

  @Override
  public Event getById(long id) {
    EntityManager em = JPAUtil.getEntityManager();
    return em.find(Event.class, id);
  }

  @Override
  public void save(Event event){
    EntityManager em = JPAUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      em.persist(event.getLocation());
      em.persist(event);
      tx.commit();
    } catch (Exception e) {
      if (tx.isActive()) tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
  }

  @Override
  public void delete(Event event) {
    EntityManager em = JPAUtil.getEntityManager();
    em.remove(event);
  }
  @Override
  public List<Location> getAllLocations() {
    return new ArrayList<>();
  }

  public void assignCoordinatorToEvent(Event event, User user) {
    EntityManager em = JPAUtil.getEntityManager(); // Might be bugged because of using the same em
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
        event = em.find(Event.class, event.getId());
        event.assignCoordinatorToEvent(user);
        em.merge(event);
      if (event != null) {
      }
      tx.commit();
    } catch (Exception e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      em.close();
    }
  }


  public void dissociateEventFromCoordinator(Event event, User user) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      EntityTransaction tx = em.getTransaction();
      tx.begin();
      event.removeCoordinatorFromEvent(user);
      em.merge(event);
      tx.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  }

