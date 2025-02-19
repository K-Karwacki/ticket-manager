package dk.easv.ticketmanager.gui.controllers.dashboards;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.gui.controllers.components.EventCardComponentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventListDashboardController implements Initializable
{
  @FXML
  private FlowPane eventListRoot;
  private ArrayList<Event> events = new ArrayList<>();


  public EventListDashboardController() {
    for(int i = 0; i<10; i++){
      events.add(new Event());
    }



  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    for (Event event : events) {
      try{
        addEventCardToContainer(event);

      }catch (Exception e){
        e.printStackTrace();
      }
    }
  }

  private void addEventCardToContainer(Event event) throws IOException
  {
    FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/components/event_card.fxml"));
    Parent node = loader.load();
    EventCardComponentController eventCardComponentController = loader.getController();
    eventCardComponentController.setEventData(event);

    eventListRoot.getChildren().add(node);
  }


}
