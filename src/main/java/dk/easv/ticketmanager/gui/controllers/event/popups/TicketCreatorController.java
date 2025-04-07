package dk.easv.ticketmanager.gui.controllers.event.popups;


//import dk.easv.ticketmanager.dal.entities.TicketType;
import dk.easv.ticketmanager.bll.services.interfaces.EventManagementService;
import dk.easv.ticketmanager.gui.FXMLPath;
import dk.easv.ticketmanager.gui.ViewManager;
import dk.easv.ticketmanager.gui.models.event.EventModel;
import dk.easv.ticketmanager.gui.models.event.TicketModel;
import dk.easv.ticketmanager.utils.FieldValidator;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.Objects;

public class TicketCreatorController
{
    private EventManagementService eventManagementService;
    private EventModel eventModel;


    @FXML
    private TextField txtFieldOtherType;

    @FXML
    private Label otherTypeLbl;

    @FXML
    private ComboBox<String> comboBoxTicketType;

    @FXML
    private TextField txtFieldTicketPrice;

    @FXML
    private TextArea txtAreaTicketInfo;


    @FXML
    private void initialize(){
        comboBoxTicketType.setValue("other");
        comboBoxTicketType.getItems().addAll("standard", "vip", "other");

        comboBoxTicketType.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue != null && newValue.equals("standard")
                || Objects.equals(newValue, "vip")){
                comboBoxTicketType.setStyle("-fx-effect: dropshadow(gaussian, green, 0, 0, -3, 3);");
                txtFieldOtherType.setVisible(false);
                txtFieldOtherType.setManaged(false);
                otherTypeLbl.setVisible(false);
                otherTypeLbl.setManaged(false);
            }else{
                comboBoxTicketType.setStyle("-fx-effect: dropshadow(gaussian, #555, 0, 0, -3, 3);");
                txtFieldOtherType.setVisible(true);
                txtFieldOtherType.setManaged(true);
                otherTypeLbl.setVisible(true);
                otherTypeLbl.setManaged(true);

            }
        }));
        txtFieldOtherType.textProperty().addListener(((observable, oldVal, newVal) -> {
            if(newVal.isEmpty()){
                txtFieldOtherType.setStyle("-fx-effect: dropshadow(gaussian, red, 0, 0, -3, 3);");
                comboBoxTicketType.setStyle("-fx-effect: dropshadow(gaussian, #555, 0, 0, -3, 3);");

            }else{
                txtFieldOtherType.setStyle("-fx-effect: dropshadow(gaussian, green, 0, 0, -3, 3);");
                comboBoxTicketType.setStyle("-fx-effect: dropshadow(gaussian, green, 0, 0, -3, 3);");
            }
        }));
        txtFieldTicketPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(Double.parseDouble(newValue) > 0){
                    txtFieldTicketPrice.setStyle("-fx-effect: dropshadow(gaussian, green, 0, 0, -3, 3);");
                }else{
                    txtFieldTicketPrice.setStyle("-fx-effect: dropshadow(gaussian, #555, 0, 0, -3, 3);");
                }
            }catch (NumberFormatException e){
                txtFieldTicketPrice.setStyle("-fx-effect: dropshadow(gaussian, #555, 0, 0, -3, 3);");
                System.out.println("Error while parsing price: " + e.getMessage());
            }

        });
        txtAreaTicketInfo.textProperty().addListener(((observable, oldValue, newValue) -> {
            if(!newValue.isEmpty()){
                txtAreaTicketInfo.setStyle("-fx-effect: dropshadow(gaussian, green, 0, 0, -3, 3);");
            }else{
                txtAreaTicketInfo.setStyle("-fx-effect: dropshadow(gaussian, #555, 0, 0, -3, 3);");
            }
        }));


    }

    @FXML
    private void onClickSubmit(){
//        this.eventModel.addTicket(new TicketModel());
        TicketModel ticket = new TicketModel();
        if(comboBoxTicketType.getValue().isEmpty()){
            throw new RuntimeException("ticket type is empty");
        }
        double price = Double.parseDouble(txtFieldTicketPrice.getText());;

        if(price < 0){
            System.out.println("cannot be lower then 0");
            return;
        }

        txtFieldTicketPrice.textProperty().addListener((observableValue, oldValue, newValue)->{
        });
        String ticketType;
        if(comboBoxTicketType.getValue().toLowerCase(Locale.ROOT).equals("other")){
            ticketType = txtFieldOtherType.getText();
        }else {
            ticketType = comboBoxTicketType.getValue();
        }

        ticket.setEventModel(this.eventModel);
        ticket.setPrice(price);
        ticket.setType(ticketType);
        ticket.setInfo(txtAreaTicketInfo.getText());

        if(!eventModel.getTickets().contains(ticket)) {
            eventModel.addTicket(ticket);
        }
        ViewManager.INSTANCE.hidePopup(FXMLPath.TICKET_CREATOR_POPUP);
        FieldValidator.clearFields(comboBoxTicketType.getParent().getParent());
        comboBoxTicketType.setValue("other");
//        System.out.println(this);
//        String ticketTypeName = txtFieldTicketTypeName.getText();
//        String ticketPrice = txtFieldTicketPrice.getText();
//        TicketType ticketType = new TicketType();
////        ticketType.setEvent(eventManagementService.getEventById(eventModel.getID()).get());
//        ticketType.setPrice(Double.parseDouble(ticketPrice));
//        ticketType.setName(ticketTypeName);
//        ticketManagementService.addTicketType(ticketType);
//        Stage stage = (Stage) txtFieldTicketPrice.getScene().getWindow();
//        stage.close();
    }
    public void setEvent(EventModel eventModel) {
        this.eventModel = eventModel;
    }



    public void setServices(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }
}
