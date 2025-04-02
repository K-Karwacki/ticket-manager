package dk.easv.ticketmanager.gui.models;

import dk.easv.ticketmanager.be.Location;
import javafx.beans.property.SimpleStringProperty;

public class LocationModel {
    private final SimpleLongProperty ID = new SimpleLongProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty address = new SimpleStringProperty();
    private final SimpleStringProperty city = new SimpleStringProperty();
    private final SimpleStringProperty post_code = new SimpleStringProperty();

    public LocationModel(){
        ID.set(-1);
        name.set(null);
        address.set(null);
        city.set(null);
        post_code.set(null);
    }

    public LocationModel(Location location) {
        ID.set(location.getID());
        name.set(location.getName());
        address.set(location.getAddress());
        city.set(location.getCity());
        post_code.set(location.getPostCode());

    }
    public long getID(){return ID.get();}
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

    @Override
    public String toString() {
        return this.getName() + " " + this.getAddress() + " " + this.getPost_code() + " " + this.getCity();
    }
}
