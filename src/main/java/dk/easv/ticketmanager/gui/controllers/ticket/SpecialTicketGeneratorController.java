package dk.easv.ticketmanager.gui.controllers.ticket;


import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagementService;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

public class SpecialTicketGeneratorController {

    @FXML
    private ComboBox<TicketType> comboBoxTicketTypes;
    private TicketManagementService ticketManagementService;


}
