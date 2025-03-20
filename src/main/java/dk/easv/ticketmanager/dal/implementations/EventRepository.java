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

  private final EntityManager em = JPAUtil.getEntityManager();

  @Override
  public List<Event> getAll() {
    return em.createQuery("select e from Event e", Event.class).getResultList();
  }

  @Override
  public Event getById(long id) {
    return em.find(Event.class, id);
  }

  @Override
  public void save(Event event){
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
    em.remove(event);
  }
  @Override
  public List<Location> getAllLocations() {
    return new ArrayList<>();
  }

  public void assignCoordinatorToEvent(Event event, User user) {
    EntityManager em = JPAUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      event = em.find(Event.class, event.getId());
      if (event != null) {
        event.assignCoordinatorToEvent(user);
        em.merge(event);
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

