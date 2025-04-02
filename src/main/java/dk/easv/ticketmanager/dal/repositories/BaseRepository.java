package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.Event;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseRepository <T>
{
  Collection<T> getAll();
  Optional<T> getById(long id);
  T save(T entity);
  boolean delete(T entity);
  boolean deleteById(long id);
  T update(T newEntity);

}
