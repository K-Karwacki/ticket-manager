package dk.easv.ticketmanager.gui.models.lists;

import dk.easv.ticketmanager.dal.entities.EventImage;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;

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
