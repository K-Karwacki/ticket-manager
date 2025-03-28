package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository
{
    @Override
    public List<User> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override public User getById(long id)
    {
        return null;
    }

    @Override
    public List<User> getUsersByRoleName(String name) {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery("SELECT u FROM User u WHERE u.role = :roleId", User.class)
                .setParameter("roleName", name)
                .getResultList();
    }

    @Override
    public User save(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User saved = em.merge(user);
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

    @Override
    public boolean delete(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
        finally {
            em.close();
        }
    }

    @Override public boolean deleteById(long id)
    {
        return delete(getById(id));
    }

    @Override public User update(User oldEntity, User newEntity)
    {
        return null;
    }


    @Override public Optional<User> findUserByEmail(String email)
    {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<User> query = em.createQuery(
            "SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);

        return query.getResultStream().findFirst();
    }


}
