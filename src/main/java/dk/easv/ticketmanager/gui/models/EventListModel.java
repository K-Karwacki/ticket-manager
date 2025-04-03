package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.EventImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
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
    public EventModel findClosestUpcomingEvent() {
        LocalDateTime now = LocalDateTime.now();

        return events.stream()
                .map(event -> new Object() {
                    final EventModel eventModel = event;
                    final LocalDateTime dateTime = LocalDateTime.of(event.dateProperty().get(), event.timeProperty().get());
                })
                .filter(event -> event.dateTime.isAfter(now))
                .min((e1, e2) -> e1.dateTime.compareTo(e2.dateTime))
                .map(e -> e.eventModel)
                .orElse(null);
    }
}
