package dk.easv.ticketmanager.bll.services.factories;

import dk.easv.ticketmanager.dal.repositories.*;
import dk.easv.ticketmanager.dal.repositories.implementations.*;
import dk.easv.ticketmanager.utils.JPAUtil;

import java.util.HashMap;
import java.util.Map;

public class RepositoryServiceFactory
{
  private final RepositoryService repositoryService;
  private final Map<Class<? extends BaseRepository<?>>, BaseRepository<?>> repositoryMap;

  public RepositoryServiceFactory(){
    repositoryMap = new HashMap<>();
    repositoryService = new RepositoryServiceImpl(repositoryMap);

    loadRepositories();
  };

  public void loadRepositories(){
    repositoryMap.putIfAbsent(UserRepository.class, new UserRepositoryImpl());
    repositoryMap.putIfAbsent(EventRepository.class, new EventRepositoryImpl());
    repositoryMap.putIfAbsent(TicketRepository.class, new TicketRepositoryImpl());
    repositoryMap.putIfAbsent(AuthRepository.class, new AuthRepositoryImpl());
    repositoryMap.putIfAbsent(CustomerRepository.class, new CustomerRepositoryImpl());
  }

  public void addRepository(Class<?extends BaseRepository<?>> repositoryInterfaceClass, BaseRepository<?> instance){
    repositoryMap.putIfAbsent(repositoryInterfaceClass, instance);
  }

  public RepositoryService getRepositoryService(){
    return repositoryService;
  }


  private static class RepositoryServiceImpl implements RepositoryService{

    private final Map<Class<? extends BaseRepository<?>>, BaseRepository<?>> repositoryMap;

    public RepositoryServiceImpl(Map<Class<? extends BaseRepository<?>>, BaseRepository<?>> repositoryMap){
      this.repositoryMap = repositoryMap;
    }

    @Override public <R extends BaseRepository<T>, T> R getRepository(Class<R> repositoryClass) {
      return (R) repositoryMap.get(repositoryClass);
    }
  }

}
