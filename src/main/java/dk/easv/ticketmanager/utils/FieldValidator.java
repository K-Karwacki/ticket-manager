package dk.easv.ticketmanager.utils;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

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

  public static void clearFields(Parent parent) {
    for (Node node : parent.getChildrenUnmodifiable()) {
      if (node instanceof TextField) {
        ((TextField) node).clear();
      } else if (node instanceof TextArea) {
        ((TextArea) node).clear();
      } else if (node instanceof ComboBox<?>) {
        ((ComboBox<?>) node).getSelectionModel().clearSelection();
        ((ComboBox<?>) node).setValue(null);
      } else if (node instanceof CheckBox) {
        ((CheckBox) node).setSelected(false);
      } else if (node instanceof RadioButton) {
        ((RadioButton) node).setSelected(false);
      } else if (node instanceof DatePicker) {
        ((DatePicker) node).setValue(null);
      } else if (node instanceof Parent) {
        // Recursively clear fields in child containers
        clearFields((Parent) node);
      }
    }
  }
}