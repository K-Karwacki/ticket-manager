package dk.easv.ticketmanager.bll.services.factories;

import dk.easv.ticketmanager.dal.repositories.BaseRepository;

public interface RepositoryService
{
//  <T extends BaseRepository<?>> T getRepository(Class<? extends BaseRepository<?>> repositoryInterfaceClass);
  <R extends BaseRepository<T>, T> R getRepository(Class<R> repositoryClass);
}
