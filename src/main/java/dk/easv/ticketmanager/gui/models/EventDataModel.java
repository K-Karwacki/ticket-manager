package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.User;
import dk.easv.ticketmanager.bll.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EventDataModel {
    private final EventService eventService = new EventService();
    private static final ObservableList<Event> events = FXCollections.observableArrayList();
    private static final ObservableList<String> cities = FXCollections.observableArrayList();

    public EventDataModel(){}
    public void load(){
        events.setAll(eventService.getAllEvents());
        cities.setAll(eventService.getAllUniqueCities());
    }
    public ObservableList<Event> getEvents() {
        return events;
    }
    public ObservableList<String> getCities() {
        return cities;
    }
    public void addEvent(Event event) {
        eventService.addEvent(event);
    }
    public void editEvent(Event event) {
        eventService.editEvent(event);
    }
    public void deleteEvent(Event event) {
        eventService.deleteEvent(event);
        events.remove(event);
    }
    public void assignCoordinatorToEvent(Event event, User user){
        eventService.assignCoordinatorToEvent(event, user);
        load();
    }
    public void dissociateEventFromCoordinator(Event event, User user){
        eventService.dissociateEventFromCoordinator(event, user);
        load();
    }
    public Event getEventById(long id) {
        return eventService.getEventById(id);
    }
}
