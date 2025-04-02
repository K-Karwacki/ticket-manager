package dk.easv.ticketmanager.gui.controllers.event.popups;

import dk.easv.ticketmanager.be.EventImage;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.controllers.event.dashboards.EventCreatorController;
import dk.easv.ticketmanager.utils.ImageConverter;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static dk.easv.ticketmanager.gui.FXMLPath.EVENT_CREATOR_POPUP;
import static dk.easv.ticketmanager.gui.FXMLPath.IMAGE_SELECTOR_POPUP;

public class ImageSelectorController
{
    private final FXMLManager fxmlManager = FXMLManager.INSTANCE;
    private Consumer<EventImage> imageConsumer;

    private EventManagementService eventManagementService;

    @FXML
    private FlowPane flowPaneImageContainer;

    // Set the consumer (lambda) to handle the image selection
    public void setImageConsumer(Consumer<EventImage> imageConsumer) {
        this.imageConsumer = imageConsumer;
    }


    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;

        loadEventImages();
    }

    private void loadEventImages(){

        for (EventImage eventImage : eventManagementService.getEventListModel()
            .getEventImagesObservable())
        {
            System.out.println(eventImage);
            Image image = ImageConverter.convertToImage(eventImage.getImageData());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(300);
            imageView.setFitHeight(150);
            imageView.setCursor(Cursor.HAND);
            flowPaneImageContainer.getChildren().add(imageView);

            imageView.setOnMouseClicked(event -> {
                imageConsumer.accept(eventImage);
                ViewManager.INSTANCE.hidePopup(IMAGE_SELECTOR_POPUP);
            });

        }
    }

    @FXML
    private void addNewImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");

        // Add image file filters
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(flowPaneImageContainer.getScene().getWindow());
            if (selectedFile != null) {
                try {
                    Image image = new Image(new FileInputStream(selectedFile));
                    if(eventManagementService.uploadEventImage(image, false)){
                        loadEventImages();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

}