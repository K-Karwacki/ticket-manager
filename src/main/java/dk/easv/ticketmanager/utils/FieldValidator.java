package dk.easv.ticketmanager.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextInputControl;

import javax.naming.ldap.Control;
import java.util.regex.Pattern;

public class FieldValidator {

  public static boolean areAllFieldsFilled(Parent parent) {
    for (Node node : parent.getChildrenUnmodifiable()) {
      // Recurse into layout containers (Parent nodes that are not Controls)
      if (node instanceof Parent && !(node instanceof Control)) {
        if (!areAllFieldsFilled((Parent) node)) {
          return false;
        }
      }

      // Check if the node is a TextInputControl (TextField, TextArea, etc.)
      if (node instanceof TextInputControl) {
        TextInputControl textField = (TextInputControl) node;
        if (textField.getText() == null || textField.getText().trim().isEmpty()) {
          textField.setStyle("-fx-border-color: red;");
          return false;
        }
      }
      // Check ComboBox
      else if (node instanceof ComboBox) {
        ComboBox<?> comboBox = (ComboBox<?>) node;
        if (comboBox.getValue() == null) {
          return false;
        }
      }
      // Check DatePicker
      else if (node instanceof DatePicker) {
        DatePicker datePicker = (DatePicker) node;
        if (datePicker.getValue() == null) {
          datePicker.setStyle("-fx-border-color: red;");
          return false;
        }
      }
      // Add checks for other control types here
    }
    return true;
  }

  public static boolean isValidTime(String time) {
    String timeRegex = "^([01]\\d|2[0-3]):[0-5]\\d$";
    return Pattern.matches(timeRegex, time);
  }
}