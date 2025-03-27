package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.bll.EmailSender;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.models.DataModelFactory;
import dk.easv.ticketmanager.gui.models.TicketDataModel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static dk.easv.ticketmanager.gui.FXMLPath.TICKET_COMPONENT;

public class TicketController {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private final TicketDataModel ticketDataModel = DataModelFactory.getTicketDataModel();
    private final EmailSender emailSender = new EmailSender();
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
        Image QRCode = ticketDataModel.generateQRCode(ticket.getTicketCode());
        Image Barcode = ticketDataModel.generateBarcode(ticket.getTicketCode());
        imgQR.setImage(QRCode);
        imgBarcode.setImage(Barcode);

    }
    public void displayTicket(Ticket ticket) throws IOException {
        Pair<Parent, TicketController> p = fxmlManager.loadFXML(TICKET_COMPONENT);
        p.getValue().setTicket(ticket);
        p.getValue().setTicketDetails(ticket);
        Stage stage = new Stage();
        stage.setTitle("Ticket");
        Scene scene = new Scene(p.getKey());
        stage.setScene(scene);
        Image fxmlImage = scene.snapshot(null);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxmlImage, null);
        File outputFile = new File("src/main/resources/dk/easv/ticketmanager/images/output.png");
        ImageIO.write(bufferedImage, "png", outputFile);
        emailSender.sendEmail(ticket.getCustomer().getEmail(), outputFile, "Ticket");
        stage.show();
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
