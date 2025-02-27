package dk.easv.ticketmanager.dal.DataAccessObjects;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EventDAO
{

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
}
