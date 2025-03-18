package dk.easv.ticketmanager.dal.implementations;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
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
  public Event getById(int id) {
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
    em.remove(event);
  }
  @Override
  public List<Location> getAllLocations() {
    return new ArrayList<>();
  }
}
