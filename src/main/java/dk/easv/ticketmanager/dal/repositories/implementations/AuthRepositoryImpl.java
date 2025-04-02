package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.be.Permission;
import dk.easv.ticketmanager.be.Role;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.utils.CustomHashSet;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class AuthRepositoryImpl implements AuthRepository
{
  @Override public List<Permission> getPermissionsForRole(Role role)
  {
    return null;
  }

  @Override public Optional<Role> findRoleByName(String roleName)
  {
    EntityManager em = JPAUtil.getEntityManager();
    TypedQuery<Role> query = em.createQuery(
        "Select r from Role r where name = :name", Role.class);
    query.setParameter("name", roleName);

    if(query.getResultStream().findFirst().isEmpty()){
      System.out.println("No role with given name");
      return Optional.empty();
    }

    return query.getResultStream().findFirst();
  }

  @Override public List<Role> getRoles()
  {
    EntityManager em = JPAUtil.getEntityManager();
    try {
      em.getTransaction().begin();
      return em.createQuery("Select r from Role r", Role.class).getResultList();
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

  @Override public List<Role> getAll()
  {
    return null;
  }

  @Override public Optional<Role> getById(long id)
  {
    try(EntityManager em = JPAUtil.getEntityManager()) {
      TypedQuery<Role> query = em.createQuery("Select r from Role r where id = :id", Role.class);
      query.setParameter("id", id);
      if(query.getResultStream().findFirst().isEmpty()){
        throw new RuntimeException("No role with given id.");
      }
      return query.getResultStream().findFirst();
    }
  }

  @Override public Role save(Role newRole)
  {
    try (EntityManager em = JPAUtil.getEntityManager())
    {
      em.getTransaction().begin();
      Role saveRole = em.merge(newRole);
      em.getTransaction().commit();
      return saveRole;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @Override public boolean delete(Role entity)
  {
    return false;
  }

  @Override public boolean deleteById(long id)
  {
    return false;
  }

  @Override public Role update(Role oldEntity)
  {
    return null;
  }
}
