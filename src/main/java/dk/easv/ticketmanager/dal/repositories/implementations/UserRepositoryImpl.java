package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.dal.entities.User;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class UserRepositoryImpl implements UserRepository
{
    protected final Map<Long, User> userMap;

    public UserRepositoryImpl(){
        userMap = new HashMap<>();

        updateCache();
    }

    private void updateCache() {
//        for (User user : getAll()) {
////            System.out.println(user.getCoordinatingEvents().size() + " SIZE OF COORDINATING ");
//            userMap.put(user.getId(), user);
//            System.out.println(user.getId());
//        }
    }


    @Override
    public List<User> getAll() {
        try(EntityManager entityManager = JPAUtil.getEntityManager()){
            return entityManager.createQuery("select u from User u", User.class).getResultList();
        }
    }

    @Override public Optional<User> getById(long id)
    {
        try(EntityManager entityManager = JPAUtil.getEntityManager()){
            return entityManager.createQuery("select u from User u where u.id=:userID", User.class).setParameter("userID", id).getResultStream()
                .findFirst();
        }
//        return Optional.of(userMap.get(id));
    }

    @Override
    public List<User> getUsersByRoleName(String name) {
        if(userMap.isEmpty()){
            return null;
        }
        List<User> newColl = new ArrayList<>();
        for (User user : userMap.values())
        {
            if(user.getRole().getName().equals(name)){
                newColl.add(user);
            }
        }
        return newColl;
    }

    @Override
    public User save(User user) {
        try (EntityManager em = JPAUtil.getEntityManager())
        {
            em.getTransaction().begin();
            User u = em.merge(user);
            em.getTransaction().commit();
            return u;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean delete(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User managedUser = em.merge(user);
            em.remove(managedUser);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        Optional<User> optionalUser = getById(id);

        if(optionalUser.isPresent()){
            delete(optionalUser.get());
            return true;
        }
        return false;
    }

    @Override
    public User update(User updatedUser) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User mergedUser = em.merge(updatedUser);
            em.getTransaction().commit();
            return mergedUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return null;
        } finally {
            em.close();
        }
    }


    @Override public Optional<User> findUserByEmail(String email)
    {
        try(EntityManager em = JPAUtil.getEntityManager()){
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst();
        }
    }




}
