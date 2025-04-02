package dk.easv.ticketmanager.gui.controllers.ticket;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.bll.services.EmailSenderService;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagmentService;
import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_COMPONENT;

public class TicketController {
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private final EmailSenderService emailSenderService = new EmailSenderService();
    private static TicketManagmentService ticketManagmentService;
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
        lblEventDate.setText(ticket.getEvent().getDate().toString());
        String price = String.valueOf(ticket.getType().getPrice()).replace(".", ",");
        lblEventPrice.setText(price + " DKK");
        lblTicketType.setText(ticket.getType().getName());
        lblFullName.setText(ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName());
        lblEventAddress.setText(String.valueOf(ticket.getEvent().getLocation()));
        Image QRCode = ticketManagmentService.generateQRCode(ticket.getTicketCode());
        Image Barcode = ticketManagmentService.generateBarcode(ticket.getTicketCode());
        imgQR.setImage(QRCode);
        imgBarcode.setImage(Barcode);

    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setDatabaseService(TicketManagmentService ticketManagmentService) {
        this.ticketManagmentService = ticketManagmentService;
    }
}

