package dk.easv.ticketmanager.dal.implementations;

import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.interfaces.IUserRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository implements IUserRepository {
    private final EntityManager em = JPAUtil.getEntityManager();

    @Override
    public List<User> getAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public List<User> getByRole(Role role) {
//        long roleId = role.getId();
//        return em.createQuery("SELECT u FROM User u WHERE u.role_id = :roleId", User.class)
//                .setParameter("roleId", roleId)
//                .getResultList();
        return null;
    }



    @Override
    public void save(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

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

    @Override
    public void delete(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }

    }

}
