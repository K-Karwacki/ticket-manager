package dk.easv.ticketmanager.dal.repositories.implementations;

import dk.easv.ticketmanager.dal.entities.Customer;
import dk.easv.ticketmanager.dal.repositories.CustomerRepository;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository
{
  @Override public Collection<Customer> getAll()
  {
    return null;
  }

  @Override public Optional<Customer> getById(long id)
  {
    return Optional.empty();
  }

  @Override public Customer save(Customer entity)
  {
    try(EntityManager entityManager = JPAUtil.getEntityManager()){
      entityManager.getTransaction().begin();
      Customer saved = entityManager.merge(entity);
      entityManager.getTransaction().commit();
      return saved;
    }
  }

  @Override public boolean delete(Customer entity)
  {
    return false;
  }

  @Override public boolean deleteById(long id)
  {
    return false;
  }

  @Override public Customer update(Customer newEntity)
  {
    return null;
  }
}
