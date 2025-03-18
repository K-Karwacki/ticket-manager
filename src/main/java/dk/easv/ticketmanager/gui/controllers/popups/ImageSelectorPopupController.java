package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.gui.FXMLManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;

public class ImageSelectorPopupController implements Initializable {
    private final FXMLManager fxmlManager = FXMLManager.getInstance();
    private static final String IMAGES_DIRECTORY = "/dk/easv/ticketmanager/images/eventImages"; // Rooted at resources/

    @FXML
    private FlowPane flowPaneImageContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int imagesCount = countImages();
        addImages(imagesCount);
    }

    private int countImages() {
        int count = 0;
        try {
            URL dirURL = getClass().getResource(IMAGES_DIRECTORY);
            if (dirURL == null) {
                throw new RuntimeException("Image directory not found: " + IMAGES_DIRECTORY);
            }
            // Temporary filesystem-based counting for development
            String fileSystemPath = "src/main/resources" + IMAGES_DIRECTORY;
            try (Stream<Path> files = Files.list(Paths.get(fileSystemPath))) {
                count = (int) files.filter(path -> path.toString().endsWith(".png")).count();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to count images in " + IMAGES_DIRECTORY, e);
        }
        return count;
    }

    private void addImages(int imagesCount) {
        for (int i = 0; i < imagesCount; i++) {
            String imagePath = IMAGES_DIRECTORY + "/img_" + i + ".png";

            Image image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(150);
            imageView.setCursor(Cursor.HAND);
            flowPaneImageContainer.getChildren().add(imageView);

            imageView.setOnMouseClicked(event -> {
                Pair<Parent, EventCreatorPopupController> p = fxmlManager.getFXML(EVENT_CREATOR_POPUP);
                p.getValue().setImage(imagePath);
                Stage stage = (Stage) flowPaneImageContainer.getScene().getWindow();
                stage.close();
            });
        }
    }
}