package dk.easv.ticketmanager.gui.controllers.popups;

import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventCreatorController;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;

public class ImageSelectorController
{
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private EventManagementService eventManagementService;

    @FXML
    private FlowPane flowPaneImageContainer;

    private void addImages() {
        flowPaneImageContainer.getChildren().clear();
        List<Image> images = eventManagementService.getAllImages();
        images.forEach(image -> {

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setFitHeight(150);
                imageView.setCursor(Cursor.HAND);
                flowPaneImageContainer.getChildren().add(imageView);

                imageView.setOnMouseClicked(event -> {
                    Pair<Parent, EventCreatorController> p = fxmlManager.getFXML(EVENT_CREATOR_POPUP);
                    p.getValue().setImage(image);
                    Stage stage = (Stage) flowPaneImageContainer.getScene().getWindow();
                    stage.close();
                });

        });
    }

    public void setDatabaseService(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
        addImages();
    }

    @FXML
    private void addNewImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");

        // Add image file filters
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
            File selectedFile = fileChooser.showOpenDialog(flowPaneImageContainer.getScene().getWindow());
            if (selectedFile != null) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    eventManagementService.addEventImage(image);
                    addImages();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}