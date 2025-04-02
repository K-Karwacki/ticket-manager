package dk.easv.ticketmanager.bll.services.implementations;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagmentService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.TicketRepository;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TicketManagmentServiceImpl implements TicketManagmentService {
    private final RepositoryService repositoryService;
    private final AuthorizationService authorizationService;

    public TicketManagmentServiceImpl(){
        repositoryService = null;
        authorizationService = null;
    }

    public TicketManagmentServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
        this.repositoryService = repositoryService;
        this.authorizationService = authorizationService;
    }
    @Override
    public void addTicketType(TicketType ticketType){
        this.repositoryService.getRepository(TicketRepository.class).addTicketType(ticketType);
    }


    @Override
    public void addTicket(Ticket ticket){
        this.repositoryService.getRepository(TicketRepository.class).save(ticket);
    }

    @Override
    public List<TicketType> getTicketTypesForEvent(EventModel eventModel) {
        EntityManager em = JPAUtil.getEntityManager();
        List<TicketType> ticketTypes = em.createQuery("select e from TicketType e", TicketType.class).getResultList();
        ticketTypes = ticketTypes.stream().filter(ticketType -> ticketType.getEvent().getID() == eventModel.getID()).toList();
        return  ticketTypes;
    }
}
