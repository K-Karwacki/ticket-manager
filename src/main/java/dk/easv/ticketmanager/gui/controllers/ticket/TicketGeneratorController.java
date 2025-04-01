package dk.easv.ticketmanager.gui.controllers.ticket;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.DatabaseService;
import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

import static dk.easv.ticketmanager.gui.FXMLPath.*;

public class TicketGeneratorController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private DatabaseService databaseService;
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

    public void addTicketTypes(Event event) {
        comboBoxTicketTypes.getItems().clear();
        comboBoxTicketTypes.setItems(databaseService.getTicketTypesForEvent(event));
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
        String ticketCode = ticketDataModel.generateTicketNumber();
        ticket.setType(comboBoxTicketTypes.getValue());
        ticket.setTicketCode(ticketCode);
        Customer customer = new Customer();
        customer.setFirstName(txtFieldCustomerFirstName.getText());
        customer.setLastName(txtFieldCustomerLastName.getText());
        customer.setEmail(txtFieldCustomerEmail.getText());
        ticket.setCustomer(customer);
        databaseService.addTicket(ticket);
    }

    public void loadTicketOptions(Image image){
        Pair<Parent, TicketOptionsController> p = fxmlManager.getFXML(TICKET_OPTIONS_POPUP);
        p.getValue().setTicketImage(image);
        p.getValue().setTicket(ticket);
        Stage stage = new Stage();
        stage.setTitle("Ticket Options");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}

