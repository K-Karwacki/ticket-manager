package dk.easv.ticketmanager;

import dk.easv.ticketmanager.bll.services.implementations.*;
import dk.easv.ticketmanager.bll.services.interfaces.*;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryServiceFactory;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.*;
import dk.easv.ticketmanager.gui.controllers.components.ChartComponentController;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventHomeController;
import dk.easv.ticketmanager.gui.controllers.event.popups.EventEditorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.AssignCoordinatorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.ImageSelectorController;
import dk.easv.ticketmanager.gui.controllers.menu.HomeDashboardController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketTypeCreatorController;
import dk.easv.ticketmanager.gui.controllers.main.LoginWindowController;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventCreatorController;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventDetailsController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketGeneratorController;
import dk.easv.ticketmanager.gui.controllers.user.components.UserCardController;
import dk.easv.ticketmanager.gui.controllers.user.dashboards.UserHomeController;
import dk.easv.ticketmanager.gui.controllers.user.popup.UserCreatorController;
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

  protected final TicketManagementService ticketManagementService = new TicketManagementServiceImpl(repositoryService, authorizationService);
  protected final EventManagementService eventManagementService = new EventManagementServiceImpl(repositoryService, authorizationService);

  private void setControllersDependencies(){
    LoginWindowController loginWindowController = (LoginWindowController) fxmlManager.getFXML(FXMLPath.LOGIN).getValue();
    EventEditorController eventEditorController = (EventEditorController) fxmlManager.getFXML(FXMLPath.EVENT_EDITOR_POPUP).getValue();
    EventDetailsController eventDetailsController = (EventDetailsController)  fxmlManager.getFXML(FXMLPath.EVENT_DETAILS).getValue();
    EventCreatorController eventCreatorPopupController = (EventCreatorController)   fxmlManager.getFXML(FXMLPath.EVENT_CREATOR_POPUP).getValue();
    HomeDashboardController homeDashboardController = (HomeDashboardController) fxmlManager.getFXML(FXMLPath.HOME_DASHBOARD).getValue();
    EventHomeController eventHomeController = (EventHomeController) fxmlManager.getFXML(FXMLPath.EVENTS_DASHBOARD).getValue();
    TicketTypeCreatorController ticketTypeCreatorController = (TicketTypeCreatorController) fxmlManager.getFXML(FXMLPath.TICKET_TYPE_CREATOR_POPUP).getValue();
    TicketGeneratorController ticketGeneratorController = (TicketGeneratorController) fxmlManager.getFXML(FXMLPath.TICKET_GENERATOR_POPUP).getValue();
    TicketController ticketController = (TicketController) fxmlManager.getFXML(FXMLPath.TICKET_COMPONENT).getValue();
    ImageSelectorController imageSelectorController = (ImageSelectorController) fxmlManager.getFXML(FXMLPath.IMAGE_SELECTOR_POPUP).getValue();
    ChartComponentController chartComponentController = (ChartComponentController) fxmlManager.getFXML(FXMLPath.CHART_COMPONENT).getValue();

    loginWindowController.setServices(authenticationService);
    eventEditorController.setServices(eventManagementService);
    eventDetailsController.setServices(eventManagementService);
    eventCreatorPopupController.setServices(eventManagementService);
    eventHomeController.setServices(eventManagementService);
    ticketTypeCreatorController.setServices(ticketManagementService, eventManagementService);
    ticketGeneratorController.setServices(ticketManagementService);
    ticketController.setServices(ticketManagementService);
    imageSelectorController.setServices(eventManagementService);
    chartComponentController.setServices(ticketManagementService);
    homeDashboardController.setServices(eventManagementService);
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
      if(authorizationService.findRoleByName(RoleType.COORDINATOR.name()) == null){
        authorizationService.createNewRole(RoleType.COORDINATOR.name());
        System.out.println("Coordinator role created");
      }
      if(authenticationService.findUserByEmail("admin") == null){
        authenticationService.registerNewUser("Admin","Admin", authorizationService.findRoleByName(RoleType.ADMIN.name()).getId(), "admin", "admin phone", "admin");
        System.out.println("Admin account created");
      }
      if(authenticationService.findUserByEmail("coordinator") == null){
        authenticationService.registerNewUser("coordinator","coordinator", authorizationService.findRoleByName(RoleType.COORDINATOR.name()).getId(), "coordinator", "coordinator phone", "coordinator");
        System.out.println("Coordinator account created");
      }

    }catch(Exception e){
      e.printStackTrace();
    }

    setControllersDependencies();
    viewManager.showStage(FXMLPath.LOGIN, "Login", false);
  }

  public static void main(String[] args)
  {
    launch();
  }
}