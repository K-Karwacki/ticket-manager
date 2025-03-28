package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.bll.EmailSender;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TicketOptionsPopupController {

    private final EmailSender emailSender = new EmailSender();
    private Ticket ticket;

    @FXML
    private ImageView imgViewTicket;

    @FXML
    private void print() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null && printerJob.showPrintDialog(null)) {
            boolean success = printerJob.printPage(imgViewTicket);

            if (success) {
                printerJob.endJob();
            }
        }
    }
    @FXML
    private void sendEmail(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        String recipient = ticket.getCustomer().getEmail();
        String ticketName = ticket.getEvent().getName() + " Ticket";
        Image ticketImage = imgViewTicket.getImage();
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(ticketImage, null);
        File ticketFile = new File("src/main/resources/dk/easv/ticketmanager/images/ticket.png");
        ImageIO.write(bufferedImage, "png", ticketFile);
        emailSender.sendEmail(recipient, ticketFile, ticketName);
        btn.setDisable(true);
        btn.getStyleClass().remove("blueBtn");
        btn.getStyleClass().add("orangeBtn");
        btn.setText("Email sent!");
    }

    public void setTicketImage(Image image) {
        imgViewTicket.setImage(image);
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
