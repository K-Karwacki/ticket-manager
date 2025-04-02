package dk.easv.ticketmanager.bll.services.implementations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dk.easv.ticketmanager.be.Ticket;
import dk.easv.ticketmanager.be.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.AuthorizationService;
import dk.easv.ticketmanager.bll.services.interfaces.TicketManagmentService;
import dk.easv.ticketmanager.bll.services.factories.RepositoryService;
import dk.easv.ticketmanager.dal.repositories.TicketRepository;
import dk.easv.ticketmanager.gui.models.EventModel;
import dk.easv.ticketmanager.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TicketManagmentServiceImpl implements TicketManagmentService {
    private final RepositoryService repositoryService;
    private final AuthorizationService authorizationService;

    public TicketManagmentServiceImpl(){
        repositoryService = null;
        authorizationService = null;
    }

    public TicketManagmentServiceImpl(RepositoryService repositoryService, AuthorizationService authorizationService){
        this.repositoryService = repositoryService;
        this.authorizationService = authorizationService;
    }
    @Override
    public void addTicketType(TicketType ticketType){
        this.repositoryService.getRepository(TicketRepository.class).addTicketType(ticketType);
    }


    @Override
    public void addTicket(Ticket ticket){
        this.repositoryService.getRepository(TicketRepository.class).save(ticket);
    }

    @Override
    public List<TicketType> getTicketTypesForEvent(EventModel eventModel) {
        EntityManager em = JPAUtil.getEntityManager();
        List<TicketType> ticketTypes = em.createQuery("select e from TicketType e", TicketType.class).getResultList();
        ticketTypes = ticketTypes.stream().filter(ticketType -> ticketType.getEvent().getID() == eventModel.getID()).toList();
        return  ticketTypes;
    }
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
}
