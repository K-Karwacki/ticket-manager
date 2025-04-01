package dk.easv.ticketmanager;

import dk.easv.ticketmanager.bll.services.AuthenticationService;
import dk.easv.ticketmanager.bll.services.AuthorizationService;
import dk.easv.ticketmanager.bll.services.EventManagementService;
import dk.easv.ticketmanager.bll.services.UserManagementService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.bll.services.implementations.AuthenticationServiceImpl;
import dk.easv.ticketmanager.bll.services.factories.RepositoryServiceFactory;
import dk.easv.ticketmanager.bll.services.implementations.AuthorizationServiceImpl;
import dk.easv.ticketmanager.bll.services.implementations.EventManagementServiceImpl;
import dk.easv.ticketmanager.bll.services.implementations.UserManagementServiceImpl;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.*;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventHomeController;
import dk.easv.ticketmanager.gui.controllers.event.popups.TicketTypeCreatorPopupController;
import dk.easv.ticketmanager.gui.controllers.main.LoginWindowController;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventCreatorController;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventDetailsController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketController;
import dk.easv.ticketmanager.gui.models.UserSession;
import dk.easv.ticketmanager.utils.RoleType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application
{
  private final ViewManager viewManager = ViewManager.INSTANCE;
  private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
  private final StageManager stageManager = new StageManager();

  protected final RepositoryServiceFactory repositoryServiceFactory = new RepositoryServiceFactory();
  protected final RepositoryService repositoryService = repositoryServiceFactory.getRepositoryService();

  protected final AuthenticationService authenticationService = new AuthenticationServiceImpl(repositoryService.getRepository(
          AuthRepository.class), repositoryService.getRepository(UserRepository.class));
  protected final AuthorizationService authorizationService = new AuthorizationServiceImpl(repositoryService.getRepository(AuthRepository.class));

  protected final EventManagementService eventManagementService = new EventManagementServiceImpl(repositoryService);

  protected final UserManagementService userManagementService = new UserManagementServiceImpl(repositoryService, authorizationService);
//  protected final DatabaseService databaseService = new DatabaseServiceImpl(repositoryService, authorizationService);






  private void setControllersDependencies(){
    LoginWindowController loginWindowController = (LoginWindowController) fxmlManager.getFXML(FXMLPath.LOGIN).getValue();
    EventDetailsController eventDetailsController = (EventDetailsController)  fxmlManager.getFXML(FXMLPath.EVENT_DETAILS).getValue();
    EventCreatorController eventCreatorPopupController = (EventCreatorController)   fxmlManager.getFXML(FXMLPath.EVENT_CREATOR_POPUP).getValue();
    EventHomeController eventHomeController = (EventHomeController) fxmlManager.getFXML(FXMLPath.EVENTS_DASHBOARD).getValue();
    TicketTypeCreatorPopupController ticketTypeCreatorPopupController = (TicketTypeCreatorPopupController) fxmlManager.getFXML(FXMLPath.TICKET_TYPE_CREATOR_POPUP).getValue();

    loginWindowController.setServices(authenticationService);

    eventDetailsController.setServices(eventManagementService);
    eventCreatorPopupController.setServices(eventManagementService);
    eventHomeController.setServices(eventManagementService);
    ticketTypeCreatorPopupController.setServices(eventManagementService);


  }


  @Override public void start(Stage primaryStage)
  {
    Locale.setDefault(Locale.ENGLISH);

    UserSession userSession = UserSession.getInstance();
    userSession.clearSession();

    stageManager.setCurrentStage(primaryStage);
    viewManager.setStageManager(stageManager);

    setControllersDependencies();

    try{
      if(authorizationService.findRoleByName(RoleType.ADMIN.name()) == null){
        authorizationService.createNewRole(RoleType.ADMIN.name());
        System.out.println("Admin role created");
      }
      if(authenticationService.findUserByEmail("admin") == null){
        authenticationService.registerNewUser("Admin","Admin", RoleType.ADMIN.name(), "admin", "admin");
        System.out.println("Admin account created");
      }
    }catch(Exception e){
      e.printStackTrace();
    }

    viewManager.showStage(FXMLPath.LOGIN, "Login", false);
  }

  public static void main(String[] args)
  {
    launch();
  }
}