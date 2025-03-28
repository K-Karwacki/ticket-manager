package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.bll.services.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.BaseRepository;

import java.util.Map;

public record RepositoryServiceImpl(
    Map<Class<? extends BaseRepository<?>>, BaseRepository<?>> repositoryMap)
    implements RepositoryService
{

  @Override public <T extends BaseRepository<?>> T getRepository(
      Class<T> repositoryInterfaceClass)
  {
    return repositoryInterfaceClass.cast(repositoryMap.get(repositoryInterfaceClass));
  }
}
