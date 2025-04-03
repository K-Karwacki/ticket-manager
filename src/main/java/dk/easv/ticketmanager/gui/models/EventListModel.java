package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.EventImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EventListModel {

    private final ObservableList<EventModel> events = FXCollections.observableArrayList();
    private final ObservableList<EventImage> eventImagesObservable = FXCollections.observableArrayList();

    public EventListModel() {

    }

    public void setEvents(Collection<EventModel> events) {
        this.events.setAll(events);
    }
    public ObservableList<EventModel> getEventsObservable() {
        return events;
    }

    public void addEventModel(EventModel eventModel){
        events.add(eventModel);
    }

    public void updateEventModel(EventModel eventModel){
        events.set(events.indexOf(eventModel), eventModel);
    }

    public void deleteEventModel(EventModel eventModel){
        events.remove(eventModel);
    }

    public ObservableList<EventImage> getEventImagesObservable()
    {
        return eventImagesObservable;
    }

    public void setEventImages(Collection<EventImage> eventImages){
        this.eventImagesObservable.setAll(eventImages);
    }
}
