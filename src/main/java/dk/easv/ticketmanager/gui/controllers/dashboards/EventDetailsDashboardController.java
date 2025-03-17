package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.be.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EventDetailsDashboardController implements Initializable {
    private Event event;

    @FXML
    private Label lblEventDate;

    @FXML
    private Label lblEventTime;

    @FXML
    private Label lblEventLocation;

    @FXML
    private Label lblNormalEventTickets;

    @FXML
    private Label lblSpecialEventTickets;

    @FXML
    private Label lblEventDescription;

    @FXML
    private Label lblEventName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        setEventDetails();
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public void setEventDetails(Event event) {
        lblEventDate.setText(event.getDate().toString());
        lblEventTime.setText(event.getTime().toString());
        lblEventLocation.setText(event.getLocation().toString());
        lblNormalEventTickets.setText(String.valueOf(event.getNormal_ticket_amount()));
        lblSpecialEventTickets.setText(String.valueOf(event.getVip_ticket_amount()));
        lblEventDescription.setText(event.getDescription());
        lblEventName.setText(event.getName());
    }


}
