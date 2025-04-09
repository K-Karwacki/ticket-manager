package dk.easv.ticketmanager.gui.controllers.ticket;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.dal.entities.Customer;
import dk.easv.ticketmanager.dal.entities.Ticket;
//import dk.easv.ticketmanager.dal.entities.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.TicketAnalysisService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.CustomerModel;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.gui.models.event.TicketModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.IOException;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class TicketGeneratorController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private TicketAnalysisService ticketAnalysisService;

    private EventManagementService eventManagementService;
    private EventModel eventModel;

    @FXML
    private TextField txtFieldCustomerFirstName;

    @FXML
    private TextField txtFieldCustomerLastName;

    @FXML
    private TextField txtFieldCustomerEmail;

    @FXML
    private ComboBox<TicketModel> comboBoxTickets;

    @FXML
    private TextField txtFieldTicketQuantity;

//    @FXML
////    private ComboBox<TicketType> comboBoxTicketTypes;
//
//    @FXML
//    private Label lblTicketPrice;

//    @FXML
//    private void displayTicketOptions() throws IOException {
//        setTicketData();
//        Image ticketImage = getTicketImage();
//        loadTicketOptions(ticketImage);
//    }

    public void setEventModel(EventModel eventModel){
        this.eventModel = eventModel;
        comboBoxTickets.getItems().setAll(eventModel.getTickets());
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

    @FXML
    private void onClickGenerateTickets(){
        if(Integer.parseInt(txtFieldTicketQuantity.getText()) < 0){
            return;
        }
        try{
            int quantity = Integer.parseInt(txtFieldTicketQuantity.getText());
            TicketModel ticketModel = comboBoxTickets.getValue();
            CustomerModel customerModel = new CustomerModel();
            customerModel.setFirstName(txtFieldCustomerFirstName.getText());
            customerModel.setLastName(txtFieldCustomerLastName.getText());
            customerModel.setEmail(txtFieldCustomerEmail.getText());
            if(eventManagementService.generateTicketsForCustomer(quantity, ticketModel, customerModel)){
                System.out.println("wygenerowano !");
            }
        }catch (NumberFormatException e){
            System.out.println("quantity not a number");
        }
    }

    public void setServices(EventManagementService eventManagementService)
    {
        this.eventManagementService = eventManagementService;
    }

    //
//    private Image getTicketImage() {
//        Pair<Parent, TicketController> p = fxmlManager.loadFXML(TICKET_COMPONENT);
//        p.getValue().setTicket(ticket);
//        p.getValue().setTicketDetails(ticket);
//        Scene scene = new Scene(p.getKey());
//        return scene.snapshot(null);
//    }
//    private void setTicketData(){
//        ticket = new Ticket();
//        String ticketCode = ticketAnalysisService.generateTicketNumber();
////        ticket.setType(comboBoxTicketTypes.getValue());
////        ticket.setTicketCode(ticketCode);
//        Customer customer = new Customer();
//        customer.setFirstName(txtFieldCustomerFirstName.getText());
//        customer.setLastName(txtFieldCustomerLastName.getText());
//        customer.setEmail(txtFieldCustomerEmail.getText());
////        ticket.setCustomer(customer);
//        ticketAnalysisService.addTicket(ticket);
//    }
//
//    public void loadTicketOptions(Image image){
//        ViewManager.INSTANCE.showPopup(TICKET_OPTIONS_POPUP, "Ticket Options");
//        TicketOptionsController ticketOptionsController = (TicketOptionsController) fxmlManager.getFXML(TICKET_OPTIONS_POPUP).getValue();
//        ticketOptionsController.setTicketImage(image);
//        ticketOptionsController.setTicket(ticket);
//    }
//    public void setServices(TicketAnalysisService ticketAnalysisService) {
//        this.ticketAnalysisService = ticketAnalysisService;
//    }
}

