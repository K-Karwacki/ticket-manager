package dk.easv.ticketmanager.utils;

import javafx.scene.image.Image; // JavaFX Image
import javafx.embed.swing.SwingFXUtils; // For conversion
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageConverter {
    public static Image convertToImage(byte[] imageBytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage bufferedImage = ImageIO.read(bis);
        bis.close();

        if (bufferedImage == null) {
            throw new IOException("Failed to decode image data");
        }

        // Convert BufferedImage to JavaFX Image
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    public static byte[] convertToByteArray(Image fxImage) throws IOException {
        if (fxImage == null) {
            throw new IllegalArgumentException("Image is null");
        }

        // Convert JavaFX Image to BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);

        // Write BufferedImage to byte[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos); // Use "png" or "jpg" based on your needs
        baos.close();

        return baos.toByteArray();
    }
}