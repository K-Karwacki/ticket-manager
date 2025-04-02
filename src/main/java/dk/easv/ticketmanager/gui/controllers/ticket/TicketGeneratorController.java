package dk.easv.ticketmanager.gui.controllers.ticket;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.EventModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class TicketGeneratorController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private static TicketManagementService ticketManagementService;
    private Ticket ticket;
    @FXML
    private TextField txtFieldCustomerFirstName;

    @FXML
    private TextField txtFieldCustomerLastName;

    @FXML
    private TextField txtFieldCustomerEmail;

    @FXML
    private ComboBox<TicketType> comboBoxTicketTypes;

    @FXML
    private Label lblTicketPrice;

    @FXML
    private void displayTicketOptions() throws IOException {
        setTicketData();
        Image ticketImage = getTicketImage();
        loadTicketOptions(ticketImage);
    }
    @FXML
    private void changePrice(){
        double price = comboBoxTicketTypes.getValue().getPrice();
        String formattedPrice  = String.format("%.2f", price).replace(".", ",");
        lblTicketPrice.setText("Price: " + formattedPrice + "DKK");
    }

    public void addTicketTypes(EventModel eventModel) {
        comboBoxTicketTypes.getItems().clear();
        List<TicketType> ticketTypes = ticketManagementService.getTicketTypesForEvent(eventModel);
        comboBoxTicketTypes.getItems().addAll(ticketTypes);
    }

    private Image getTicketImage() {
        Pair<Parent, TicketController> p = fxmlManager.loadFXML(TICKET_COMPONENT);
        p.getValue().setTicket(ticket);
        p.getValue().setTicketDetails(ticket);
        Scene scene = new Scene(p.getKey());
        return scene.snapshot(null);
    }
    private void setTicketData(){
        ticket = new Ticket();
        String ticketCode = ticketManagementService.generateTicketNumber();
        ticket.setType(comboBoxTicketTypes.getValue());
        ticket.setTicketCode(ticketCode);
        Customer customer = new Customer();
        customer.setFirstName(txtFieldCustomerFirstName.getText());
        customer.setLastName(txtFieldCustomerLastName.getText());
        customer.setEmail(txtFieldCustomerEmail.getText());
        ticket.setCustomer(customer);
        ticketManagementService.addTicket(ticket);
    }

    public void loadTicketOptions(Image image){
        TicketOptionsController ticketOptionsController = ViewManager.INSTANCE.showPopup(TICKET_OPTIONS_POPUP, "Ticket Options");
        ticketOptionsController.setTicketImage(image);
        ticketOptionsController.setTicket(ticket);
    }
    public void setDatabaseService(TicketManagementService ticketManagementService) {
        this.ticketManagementService = ticketManagementService;
    }
}

