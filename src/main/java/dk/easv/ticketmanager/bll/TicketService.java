package dk.easv.ticketmanager.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import dk.easv.ticketmanager.dal.implementations.TicketRepository;
import dk.easv.ticketmanager.dal.interfaces.ITicketRepository;

public class TicketService {
    private final ITicketRepository ticketRepository = new TicketRepository();
    private final EmailSender emailSender = new EmailSender();

    public String generateTicketNumber() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Image generateQRCode(String text) {
        int width = 200;
        int height = 200;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            return generateImage(width, height, bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Image generateBarcode(String text) {
        int width = 40;
        int height = 100;
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height);
            return generateImage(width, height, bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Image generateImage(int width, int height, BitMatrix bitMatrix) {
        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixelWriter.setColor(x, y, bitMatrix.get(x, y) ? javafx.scene.paint.Color.BLACK : javafx.scene.paint.Color.WHITE);
            }
        }

        return image;
    }

    public void addTicket(Ticket ticket) {
        ticketRepository.addTicket(ticket);
    }

    // To implement
//    public void sendEmail(Ticket ticket, Image ticketImage) {
//        String email = ticket.getCustomer().getEmail();
//        String subject = ticket.getEvent().getName();
//        emailSender.sendEmail(email, subject, ticketImage);
//    }

    public void addTicketType(TicketType ticketType){
        ticketRepository.addTicketType(ticketType);
    }

    public List<TicketType> getAllTicketTypes() {
        return ticketRepository.getAllTicketTypes();
    }
}
