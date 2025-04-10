package dk.easv.ticketmanager.gui.controllers.components;

import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HeaderComponentController
{
    @FXML
    private void onClickOpenSettings(MouseEvent event)
    {
        ViewManager.INSTANCE.switchDashboard(FXMLPath.SETTINGS_DASHBOARD, "Settings");
    }
}
