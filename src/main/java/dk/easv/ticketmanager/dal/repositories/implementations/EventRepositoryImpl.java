package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository
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
  public Event save(Event event) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      Event saved = em.merge(event);
      em.getTransaction().commit();
      return saved;
    }  catch (Exception e) {
      if (em.getTransaction().isActive()){
        em.getTransaction().rollback();
      }
      throw new RuntimeException(e);
    }
    finally {
      em.close();
    }
  }
//  public Event save(Event event){
//    EntityManager em = JPAUtil.getEntityManager();
//
//    try {
//      em.getTransaction().begin();
//      em.persist(event.getLocation());
//      em.persist(event);
//      em.getTransaction().commit();
//      return event;
//    } catch (Exception e) {
//      if (em.getTransaction().isActive()) em.getTransaction().rollback();
//      e.printStackTrace();
//      em
//    } finally {
//      em.close();
//    }
//  }

  @Override
  public boolean delete(Event event) {
    try (EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      em.remove(event);
      em.getTransaction().commit();
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public boolean deleteById(long id)
  {
    try (EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      em.createQuery("delete from Event e where e.id = :id")
          .setParameter("id", id)
          .executeUpdate();
      em.getTransaction().commit();
      return true;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }

  @Override public Event update(Event oldEntity, Event newEntity)
  {
    return null;
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
        event = em.find(Event.class, event.getID());
        event.assignCoordinatorToEvent(user);
        em.merge(event);
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

