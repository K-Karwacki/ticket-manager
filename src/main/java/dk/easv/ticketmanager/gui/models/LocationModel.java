package dk.easv.ticketmanager.gui.models;

import javafx.beans.property.SimpleStringProperty;

public class LocationModel {
    private final long ID;
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty address = new SimpleStringProperty();
    private final SimpleStringProperty city = new SimpleStringProperty();
    private final SimpleStringProperty post_code = new SimpleStringProperty();

    public LocationModel(long id) {
        ID = id;
    }
    public long getID() {
        return ID;
    }
    public String getName() {
        return name.get();
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public String getAddress() {
        return address.get();
    }
    public SimpleStringProperty addressProperty() {
        return address;
    }
    public String getCity() {
        return city.get();
    }
    public SimpleStringProperty cityProperty() {
        return city;
    }
    public String getPost_code() {
        return post_code.get();
    }
    public SimpleStringProperty post_codeProperty() {
        return post_code;
    }
    public void setName(String newName) {
        name.set(newName);
    }
    public void setAddress(String newAddress) {
        address.set(newAddress);
    }
    public void setCity(String newCity) {
        city.set(newCity);
    }
    public void setPost_code(String newPost_code) {
        post_code.set(newPost_code);
    }
}
