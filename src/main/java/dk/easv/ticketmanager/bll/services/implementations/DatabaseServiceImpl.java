package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.bll.services.RepositoryService;

public class DatabaseServiceImpl implements DatabaseService
{
  private final RepositoryService repositoryService;

  public DatabaseServiceImpl(){
    repositoryService = null;
  }

  public DatabaseServiceImpl(RepositoryService repositoryService){
    this.repositoryService = repositoryService;
  }

  @Override
  public RepositoryService getRepositoryService() {
    return repositoryService;
  }
}
