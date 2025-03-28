package dk.easv.ticketmanager.bll.services.factories;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.services.RepositoryService;
import dk.easv.ticketmanager.bll.services.implementations.RepositoryServiceImpl;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.BaseRepository;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.dal.repositories.implementations.AuthRepositoryImpl;
import dk.easv.ticketmanager.dal.repositories.implementations.EventRepositoryImpl;
import dk.easv.ticketmanager.dal.repositories.implementations.UserRepositoryImpl;

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
    repositoryMap.putIfAbsent(AuthRepository.class, new AuthRepositoryImpl());
  }

  public void addRepository(Class<?extends BaseRepository<?>> repositoryInterfaceClass, BaseRepository<?> instance){
    repositoryMap.putIfAbsent(repositoryInterfaceClass, instance);
  }

  public RepositoryService getRepositoryService(){
    return repositoryService;
  }


}
