package dk.easv.ticketmanager.gui;

import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.gui.models.UserSession;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;

public class ProfileImageService {

    User user = UserSession.getInstance().getUser();

    public void setProfileImage(ImageView profileImage, Circle profileCircle) {
        if (user.getImagePath() != null && !user.getImagePath().isEmpty()) {
            try {
                Image img = new Image("file:" + user.getImagePath(), false);
                ImagePattern pattern = new ImagePattern(img);
                profileCircle.setFill(pattern); // `profileImage` should be a `Circle`
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
            }
        }
    }

}
