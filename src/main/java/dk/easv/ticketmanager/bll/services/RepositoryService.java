package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.BaseRepository;
import dk.easv.ticketmanager.dal.repositories.EventRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import jakarta.persistence.Entity;

public interface RepositoryService
{
  <T extends BaseRepository<?>> T getRepository(Class<T> repositoryInterfaceClass);

}
