package dk.easv.ticketmanager.gui.controllers.menu;


import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagementService;

import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.ChartComponentController;
import dk.easv.ticketmanager.gui.controllers.event.components.EventCardController;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CARD_COMPONENT;

public class HomeDashboardController {
    private EventManagementService eventManagementService;

    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
        EventModel eventModel = eventManagementService.getEventListModel().findClosestUpcomingEvent();
        Pair<Parent, EventCardController> p = FXMLManager.INSTANCE.loadFXML(EVENT_CARD_COMPONENT);
        p.getValue().setEventModel(eventModel);
    }
}