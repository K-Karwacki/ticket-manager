package dk.easv.ticketmanager;

import dk.easv.ticketmanager.bll.services.EmailSenderService;
import dk.easv.ticketmanager.bll.services.implementations.*;
import dk.easv.ticketmanager.bll.services.interfaces.*;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryServiceFactory;
import dk.easv.ticketmanager.dal.repositories.AuthRepository;
import dk.easv.ticketmanager.dal.repositories.UserRepository;
import dk.easv.ticketmanager.gui.*;
import dk.easv.ticketmanager.gui.controllers.components.ChartComponentController;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventHomeController;
import dk.easv.ticketmanager.gui.controllers.event.popups.AssignCoordinatorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.EventEditorController;
import dk.easv.ticketmanager.gui.controllers.event.popups.ImageSelectorController;
import dk.easv.ticketmanager.gui.controllers.main.ForgottenPasswordViewController;
import dk.easv.ticketmanager.gui.controllers.menu.ProfileDashboardEditorController;
import dk.easv.ticketmanager.gui.controllers.ticket.TicketController;
import dk.easv.ticketmanager.gui.controllers.main.LoginWindowController;
import dk.easv.ticketmanager.gui.controllers.user.components.UserCardController;
import dk.easv.ticketmanager.gui.controllers.user.dashboards.UserHomeController;
import dk.easv.ticketmanager.gui.controllers.user.popup.UserFormController;
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
  protected final UserSession userSession = UserSession.INSTANCE;

  protected final RepositoryServiceFactory repositoryServiceFactory = new RepositoryServiceFactory();
  protected final RepositoryService repositoryService = repositoryServiceFactory.getRepositoryService();


  protected final AuthenticationService authenticationService = new AuthenticationServiceImpl(repositoryService);
  protected final AuthorizationService authorizationService = new AuthorizationServiceImpl(repositoryService);
  protected final EmailSenderService emailSenderService = new EmailSenderService();

  protected final TicketAnalysisService ticketAnalysisService = new TicketAnalysisServiceImpl(repositoryService, authorizationService);
  protected final EventManagementService eventManagementService = new EventManagementServiceImpl(repositoryService, authorizationService);
  protected final UserManagementService userManagementService = new UserManagementServiceImpl(repositoryService, authorizationService, emailSenderService);

  private void setControllersDependencies(){
    LoginWindowController loginWindowController = (LoginWindowController) fxmlManager.getFXML(FXMLPath.LOGIN).getValue();
    EventEditorController eventEditorController = (EventEditorController) fxmlManager.getFXML(FXMLPath.EVENT_EDITOR_POPUP).getValue();

    EventHomeController eventHomeController = (EventHomeController) fxmlManager.getFXML(FXMLPath.EVENTS_DASHBOARD).getValue();

    ForgottenPasswordViewController forgottenPasswordViewController = (ForgottenPasswordViewController) fxmlManager.getFXML(FXMLPath.FORGOTTEN_PASSWORD_VIEW).getValue();
    ProfileDashboardEditorController profileDashBoardEditor = (ProfileDashboardEditorController)  fxmlManager.getFXML(FXMLPath.PROFILE_DASHBOARD_EDITOR).getValue();
    AssignCoordinatorController assignCoordinatorController = (AssignCoordinatorController) fxmlManager.getFXML(FXMLPath.COORDINATOR_LIST_POPUP).getValue();
    UserFormController userFormController = new UserFormController();
    UserCardController userCardController = (UserCardController) fxmlManager.getFXML(FXMLPath.USER_CARD_COMPONENT).getValue();
    TicketController ticketController = (TicketController) fxmlManager.getFXML(FXMLPath.TICKET_COMPONENT).getValue();
    ImageSelectorController imageSelectorController = (ImageSelectorController) fxmlManager.getFXML(FXMLPath.IMAGE_SELECTOR_POPUP).getValue();
    ChartComponentController chartComponentController = (ChartComponentController) fxmlManager.getFXML(FXMLPath.CHART_COMPONENT).getValue();
    UserHomeController userHomeController = (UserHomeController) fxmlManager.getFXML(FXMLPath.USERS_DASHBOARD).getValue();

    loginWindowController.setServices(authenticationService);

    eventHomeController.setServices(eventManagementService, authorizationService);

    userHomeController.setServices(userManagementService, authorizationService);

    assignCoordinatorController.setServices(userManagementService, eventManagementService);

    profileDashBoardEditor.setServices(userManagementService, authenticationService);

//    ticketGeneratorController.setServices(ticketAnalysisService);
    userFormController.setServices(userManagementService, authorizationService);

//    ticketGeneratorController.setServices(ticketAnalysisService);
    userCardController.setServices(userManagementService);
    forgottenPasswordViewController.setServices(userManagementService);
    ticketController.setServices(ticketAnalysisService);
    imageSelectorController.setServices(eventManagementService);
    chartComponentController.setServices(ticketAnalysisService);
  }


  @Override public void start(Stage primaryStage)
  {
    setControllersDependencies();
    Locale.setDefault(Locale.ENGLISH);

//    userSession.clearSession();

    stageManager.setCurrentStage(primaryStage);
    viewManager.setStageManager(stageManager);


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
        userManagementService.registerNewUser("Admin","Admin", authorizationService.findRoleByName(RoleType.ADMIN.name()).getId(), "admin", "admin phone", "admin", null);
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