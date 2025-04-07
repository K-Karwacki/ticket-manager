package dk.easv.ticketmanager.gui.controllers.ticket;

import dk.easv.ticketmanager.dal.entities.Ticket;
import dk.easv.ticketmanager.bll.services.interfaces.TicketAnalysisService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TicketController {
    private static TicketAnalysisService ticketAnalysisService;
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
//        lblEventName.setText(ticket.getEvent().getName());
//        lblEventDate.setText(ticket.getEvent().getDate().toString());
//        String price = String.valueOf(ticket.getType().getPrice()).replace(".", ",");
//        lblEventPrice.setText(price + " DKK");
//        lblTicketType.setText(ticket.getType().getName());
//        lblFullName.setText(ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName());
//        lblEventAddress.setText(ticket.getEvent().getLocation().toString());
//        Image QRCode = ticketManagementService.generateQRCode(ticket.getTicketCode());
//        Image Barcode = ticketManagementService.generateBarcode(ticket.getTicketCode());
//        imgQR.setImage(QRCode);
//        imgBarcode.setImage(Barcode);

    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setServices(TicketAnalysisService ticketAnalysisService) {
        this.ticketAnalysisService = ticketAnalysisService;
    }
}

