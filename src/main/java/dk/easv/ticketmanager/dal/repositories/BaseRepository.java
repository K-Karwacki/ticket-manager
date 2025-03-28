package dk.easv.ticketmanager.dal.repositories;

import dk.easv.ticketmanager.be.Event;

import java.util.List;

public interface BaseRepository <T>
{
  List<T> getAll();
  T getById(long id);
  T save(T entity);
  boolean delete(T entity);
  boolean deleteById(long id);
  T update(T oldEntity, T newEntity);

}
