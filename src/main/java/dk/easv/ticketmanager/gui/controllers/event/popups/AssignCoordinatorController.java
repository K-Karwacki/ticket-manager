package dk.easv.ticketmanager.gui.controllers.event.popups;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.interfaces.UserManagementService;
import dk.easv.ticketmanager.dal.entities.Event;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.controllers.user.CoordinatorCardController;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.gui.models.UserModel;
import dk.easv.ticketmanager.utils.RoleType;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.util.Pair;

import java.util.Set;
import java.util.function.Consumer;

public class AssignCoordinatorController
{
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private Consumer<Set<UserModel>> consumer;
    private EventModel eventModel;
    private UserManagementService userManagementService;
    private EventManagementService eventManagementService;

    @FXML
    private FlowPane flowPaneCoordinatorContainer;

    public AssignCoordinatorController(){
        consumer = null;
    }


    public void setServices(UserManagementService userManagementService, EventManagementService eventManagementService){
        this.userManagementService = userManagementService;
        this.eventManagementService = eventManagementService;

//        selectedCoordinators.addListener((SetChangeListener<UserModel>) change ->{
//            if(change.wasAdded()){
//
//            }
//        });
    }

    private void loadCoordinators(){
        flowPaneCoordinatorContainer.getChildren().clear();
        flowPaneCoordinatorContainer.getChildren().removeAll();

        for (UserModel userModel : userManagementService.getUserListModel()
            .getUsersObservable())
        {
             if(userModel.roleProperty().get().getName().equals(RoleType.COORDINATOR.name()) && !eventModel.getAssignedCoordinators().contains(userModel)){
                 Pair<Parent, CoordinatorCardController> p = FXMLManager.INSTANCE.loadFXML(FXMLPath.COORDINATOR_CARD_COMPONENT);

                 CoordinatorCardController coordinatorCardController = p.getValue();

                 coordinatorCardController.setServices(eventManagementService);
                 coordinatorCardController.setModel(userModel, eventModel);
                flowPaneCoordinatorContainer.getChildren().add(p.getKey());
             }
        }
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;

        loadCoordinators();
    }




}
