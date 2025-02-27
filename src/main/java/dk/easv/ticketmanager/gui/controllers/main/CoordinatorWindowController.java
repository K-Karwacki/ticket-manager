package dk.easv.ticketmanager.gui.controllers.main;

import dk.easv.ticketmanager.Main;
import dk.easv.ticketmanager.be.Event;
import dk.easv.ticketmanager.be.Location;
import dk.easv.ticketmanager.dal.DataAccessObjects.EventDAO;
import dk.easv.ticketmanager.gui.FXMLManager;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.controllers.components.MenuComponentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.util.Pair;

import javax.swing.border.Border;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class CoordinatorWindowController implements Initializable
{
  @FXML
  private Node menuComponent;

  private EventDAO eventDAO = new EventDAO();

  @FXML
  private BorderPane root;


  @Override public void initialize(URL location, ResourceBundle resources)
  {

    FXMLManager fxmlManager = FXMLManager.getInstance();
    Pair<Parent, MenuComponentController> menuComponentFxmlResult = fxmlManager.getFXML(FXMLPath.MENU_COMPONENT);
    Parent menuRoot = menuComponentFxmlResult.getKey();
    MenuComponentController menuComponentController = menuComponentFxmlResult.getValue();
    menuComponentController.setParentContainer(root);
    root.setLeft(menuRoot);
    //    menuComponentController = fxmlManager.getFXML(FXMLPath.MENU_COMPON/**/ENT).getValue();
    eventDAO.save(new Event("Mega event", "Super duper event", "path", LocalDate.now(),
        LocalTime.now(), new Location("Blue water arena", "adress1", "", "", "Esbjerg", "6700")));
  }
}
