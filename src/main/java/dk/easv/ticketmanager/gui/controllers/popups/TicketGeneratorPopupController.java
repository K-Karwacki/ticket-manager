package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.TicketController;
import dk.easv.ticketmanager.gui.models.DataModelFactory;
import dk.easv.ticketmanager.gui.models.TicketDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.xml.crypto.Data;

import java.net.URL;
import java.util.ResourceBundle;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_GENERATOR_POPUP;

public class TicketGeneratorPopupController {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private final TicketController ticketController = new TicketController();
    private final TicketDataModel ticketDataModel = DataModelFactory.getTicketDataModel();
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
    private void generateTicket() {
        String ticketCode = ticketDataModel.generateTicketNumber();
        Ticket ticket = new Ticket();
        ticket.setType(comboBoxTicketTypes.getValue());
        ticket.setTicketCode(ticketCode);
        Customer customer = new Customer();
        customer.setFirstName(txtFieldCustomerFirstName.getText());
        customer.setLastName(txtFieldCustomerLastName.getText());
        customer.setEmail(txtFieldCustomerEmail.getText());
        ticket.setCustomer(customer);
        ticketDataModel.addTicket(ticket);
        ticketController.displayTicket(ticket);
    }
    @FXML
    private void changePrice(){
        double price = comboBoxTicketTypes.getValue().getPrice();
        String formattedPrice  = String.format("%.2f", price);
        lblTicketPrice.setText("Price: " + formattedPrice + "DKK");
    }
    public void load(Event event) {
        Pair<Parent, TicketGeneratorPopupController> p = fxmlManager.loadFXML(TICKET_GENERATOR_POPUP);
        p.getValue().setDetails(event);
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }

    private void setDetails(Event event) {
        System.out.println(ticketDataModel.getTicketTypesForEvent(event));
        comboBoxTicketTypes.getItems().clear();
        comboBoxTicketTypes.setItems(ticketDataModel.getTicketTypesForEvent(event));
    }

}
