package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.bll.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventDataModel {
    private final EventService eventService = new EventService();
    private static final ObservableList<Event> events = FXCollections.observableArrayList();

    public void loadEvents(){
        events.setAll(eventService.getAllEvents());
    }
    public ObservableList<Event> getEvents() {
        return events;
    }

}
