package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.dal.repositories.BaseRepository;

public interface DatabaseService
{
    RepositoryService getRepositoryService();

    default <T extends BaseRepository<?>> T getRepository(Class<T> repositoryInterfaceClass) {
        return getRepositoryService().getRepository(repositoryInterfaceClass);
    }
}
