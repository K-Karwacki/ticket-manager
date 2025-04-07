package dk.easv.ticketmanager.gui.controllers.ticket;

import dk.easv.ticketmanager.dal.entities.Customer;
import dk.easv.ticketmanager.dal.entities.Ticket;
//import dk.easv.ticketmanager.dal.entities.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.TicketAnalysisService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.IOException;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class TicketGeneratorController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private TicketAnalysisService ticketAnalysisService;
    private Ticket ticket;
    @FXML
    private TextField txtFieldCustomerFirstName;

    @FXML
    private TextField txtFieldCustomerLastName;

    @FXML
    private TextField txtFieldCustomerEmail;

//    @FXML
////    private ComboBox<TicketType> comboBoxTicketTypes;
//
//    @FXML
//    private Label lblTicketPrice;

    @FXML
    private void displayTicketOptions() throws IOException {
        setTicketData();
        Image ticketImage = getTicketImage();
        loadTicketOptions(ticketImage);
    }
    @FXML
    private void changePrice(){
//        double price = comboBoxTicketTypes.getValue().getPrice();
//        String formattedPrice  = String.format("%.2f", price).repla/*ce(".", ",");
//        lblTicketPrice.setText("Price: " + formattedPrice + "DKK");*/
    }

//    public void addTicketTypes(EventModel eventModel) {
//        comboBoxTicketTypes.getItems().clear();
//        List<TicketType> ticketTypes = ticketManagementService.getTicketTypesForEvent(eventModel);
//        comboBoxTicketTypes.getItems().addAll(ticketTypes);
//    }


    private Image getTicketImage() {
        Pair<Parent, TicketController> p = fxmlManager.loadFXML(TICKET_COMPONENT);
        p.getValue().setTicket(ticket);
        p.getValue().setTicketDetails(ticket);
        Scene scene = new Scene(p.getKey());
        return scene.snapshot(null);
    }
    private void setTicketData(){
        ticket = new Ticket();
        String ticketCode = ticketAnalysisService.generateTicketNumber();
//        ticket.setType(comboBoxTicketTypes.getValue());
//        ticket.setTicketCode(ticketCode);
        Customer customer = new Customer();
        customer.setFirstName(txtFieldCustomerFirstName.getText());
        customer.setLastName(txtFieldCustomerLastName.getText());
        customer.setEmail(txtFieldCustomerEmail.getText());
//        ticket.setCustomer(customer);
        ticketAnalysisService.addTicket(ticket);
    }

    public void loadTicketOptions(Image image){
        ViewManager.INSTANCE.showPopup(TICKET_OPTIONS_POPUP, "Ticket Options");
        TicketOptionsController ticketOptionsController = (TicketOptionsController) fxmlManager.getFXML(TICKET_OPTIONS_POPUP).getValue();
        ticketOptionsController.setTicketImage(image);
        ticketOptionsController.setTicket(ticket);
    }
    public void setServices(TicketAnalysisService ticketAnalysisService) {
        this.ticketAnalysisService = ticketAnalysisService;
    }
}

