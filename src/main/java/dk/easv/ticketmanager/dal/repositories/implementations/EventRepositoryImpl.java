package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.dal.entities.Event;
import dk.easv.ticketmanager.dal.entities.EventImage;
import dk.easv.ticketmanager.dal.entities.Location;
import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository
{
  private List<Event> cachedEvents = new ArrayList<>();

  @Override
  public List<Event> getAll() {
    EntityManager em = JPAUtil.getEntityManager();
    return em.createQuery("select e from Event e", Event.class).getResultList();
  }

  @Override
  public Optional<Event> getById(long id) {
    EntityManager em = JPAUtil.getEntityManager();
    return Optional.of(em.find(Event.class, id));
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
      return null;
    }
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


  public List<EventImage> getAllEventImages(){
      try(EntityManager em = JPAUtil.getEntityManager()){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        return em.createQuery("select e from EventImage e", EventImage.class).getResultList();
      }
    }

  @Override
  public EventImage saveEventImage(EventImage eventImage) {
    try (EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      EventImage e = em.merge(eventImage);
      if(e != null){
       em.getTransaction().commit();
        return e;
      }

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
      Optional<EventImage> eventImageOptional = em.createQuery("Select i from EventImage i where id = :imageId", EventImage.class).setParameter("imageId", eventImageID).getResultList().stream().findAny();
      return eventImageOptional.orElse(null);
    }
  }

}

