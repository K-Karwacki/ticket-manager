package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.models.DataModelFactory;
import dk.easv.ticketmanager.gui.models.TicketDataModel;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_COMPONENT;

public class TicketController {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private final TicketDataModel ticketDataModel = DataModelFactory.getTicketDataModel();
    private Ticket ticket;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblEventTime;
    @FXML
    private Label lblEventDate;
    @FXML
    private Label lblEventPrice;
    @FXML
    private Label lblEventAddress;
    @FXML
    private Label lblTicketType;
    @FXML
    private Label lblFullName;
    @FXML
    private ImageView imgQR;
    @FXML
    private ImageView imgBarcode;

    public void setTicketDetails(Ticket ticket) {
        lblEventName.setText(ticket.getEvent().getName());
        lblEventTime.setText(ticket.getEvent().getTime().toString());
        lblEventDate.setText(ticket.getEvent().getDate().toString());
        lblEventPrice.setText(ticket.getType().getPrice() + " DKK");
        lblTicketType.setText(ticket.getType().getName());
        lblFullName.setText(ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName());
        lblEventAddress.setText(String.valueOf(ticket.getEvent().getLocation()));
        Image QRCode = ticketDataModel.generateQRCode(ticket.getTicketCode());
        Image Barcode = ticketDataModel.generateBarcode(ticket.getTicketCode());
        imgQR.setImage(QRCode);
        imgBarcode.setImage(Barcode);
    }
    public void displayTicket(Ticket ticket){
        Pair<Parent, TicketController> p = fxmlManager.loadFXML(TICKET_COMPONENT);
        p.getValue().setTicket(ticket);
        p.getValue().setTicketDetails(ticket);
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        stage.setScene(new Scene(p.getKey()));
        stage.show();
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
