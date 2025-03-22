package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Customer;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.components.TicketController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_COMPONENT;
import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_GENERATOR_POPUP;

public class TicketGeneratorPopupController {

    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private final TicketController ticketController = new TicketController();
    private Event event;

    @FXML
    private TextField txtFieldTicketPrice;

    @FXML
    private TextField txtFieldCustomerFirstName;

    @FXML
    private TextField txtFieldCustomerLastName;

    @FXML
    private TextField txtFieldCustomerEmail;

    @FXML
    private ComboBox<String> comboBoxTicketTypes;

    @FXML
    private void generateTicket() {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setPrice(Integer.parseInt(txtFieldTicketPrice.getText()));
        Customer customer = new Customer();
        customer.setFirstName(txtFieldCustomerFirstName.getText());
        customer.setLastName(txtFieldCustomerLastName.getText());
        customer.setEmail(txtFieldCustomerEmail.getText());
        ticket.setCustomer(customer);
        ticket.setType(comboBoxTicketTypes.getValue());
        ticketController.displayTicket(ticket);
    }
    public void load(Event event) {
        Pair<Parent, TicketGeneratorPopupController> p = fxmlManager.loadFXML(TICKET_GENERATOR_POPUP);
        p.getValue().setEvent(event);
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
    private void setEvent(Event event) {
        this.event = event;
    }
    public Event getEvent() {
        return event;
    }
}
