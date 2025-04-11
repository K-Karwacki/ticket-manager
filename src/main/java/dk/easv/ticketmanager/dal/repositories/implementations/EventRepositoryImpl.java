package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.dal.entities.Event;
import dk.easv.ticketmanager.dal.entities.EventImage;
import dk.easv.ticketmanager.dal.entities.Location;
import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Lob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository
{
  private List<Event> cachedEvents = new ArrayList<>();

  @Override
  public List<Event> getAll() {
    try(EntityManager em = JPAUtil.getEntityManager()){
      em.getTransaction().begin();
      List<Event> events = em.createQuery("select e from Event e", Event.class).getResultList();
      if(!events.isEmpty()){
        em.getTransaction().commit();
        return events;
      }
    }catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    return Collections.emptyList();
  }

  @Override
  public Optional<Event> getById(long id) {
    try(EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      Event event = em.find(Event.class, id);
      if(event != null){
        em.getTransaction().commit();
        return Optional.of(event);
      }
    }catch (Exception ex) {
      ex.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Event save(Event event) {
    try (EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      Event e = em.merge(event);
      em.getTransaction().commit();
      return e;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

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
  @Override
  public Event update(Event oldEntity) {
    EntityManager em = JPAUtil.getEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      Event e = em.merge(oldEntity);
      tx.commit();
      return e;
    } catch (Exception ex) {
      if (tx.isActive()) {
        tx.rollback();
      }
      ex.printStackTrace();
    } finally {
      em.close();
    }
    return oldEntity;
  }


  @Override
  public List<Location> getAllLocations() {
    return new ArrayList<>();
  }

  @Override
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

@Override
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


  @Override
  public List<EventImage> getAllEventImages(){
      try(EntityManager em = JPAUtil.getEntityManager()){
        return em.createQuery("select e from EventImage e", EventImage.class).getResultList();
      }
    }

  @Override
  public EventImage saveEventImage(EventImage eventImage) {
    try (EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      em.persist(eventImage);
      em.getTransaction().commit();
      em.flush();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public EventImage getEventImageByID(Long eventImageID)
  {
    try(EntityManager em = JPAUtil.getEntityManager()) {
      return em.find(EventImage.class, eventImageID);
    }
  }

}

