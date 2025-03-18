package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.bll.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventDataModel {
    private final EventService eventService = new EventService();
    private static final ObservableList<Event> events = FXCollections.observableArrayList();
    private static final ObservableList<String> cities = FXCollections.observableArrayList();

    public EventDataModel(){
        loadEvents();
    }
    public void loadEvents(){
        events.setAll(eventService.getAllEvents());
        cities.setAll(eventService.getAllUniqueCities());
    }
    public ObservableList<Event> getEvents() {
        return events;
    }
    public ObservableList<String> getCities() {
        return cities;
    }

}
