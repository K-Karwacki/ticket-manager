package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class EventListModel {

    private ObservableList<EventModel> events = FXCollections.observableArrayList();

    public EventListModel() {

    }
    public void setEvents(List<EventModel> events) {
        this.events.setAll(events);
    }
    public ObservableList<EventModel> getEvents() {
        return events;
    }
}
