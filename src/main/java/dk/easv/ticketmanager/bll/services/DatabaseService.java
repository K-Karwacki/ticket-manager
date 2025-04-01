package dk.easv.ticketmanager.bll.services;

import dk.easv.ticketmanager.dal.repositories.BaseRepository;
import dk.easv.ticketmanager.gui.models.EventListModel;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.gui.models.UserListModel;

import java.util.List;

public interface DatabaseService
{
    RepositoryService getRepositoryService();

    default <T extends BaseRepository<?>> T getRepository(Class<T> repositoryInterfaceClass) {
        return getRepositoryService().getRepository(repositoryInterfaceClass);
    }
    UserListModel getUserListModel();
    EventListModel getEventListModel();

}
