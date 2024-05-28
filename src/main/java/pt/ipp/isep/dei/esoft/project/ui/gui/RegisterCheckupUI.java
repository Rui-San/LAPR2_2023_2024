package pt.ipp.isep.dei.esoft.project.ui.gui;

import com.sun.source.tree.TryTree;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.RegisterCheckupController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.VehicleCheckup;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterCheckupUI implements Initializable {

    private final RegisterCheckupController controller;

    @FXML
    private TableView<Vehicle> tbVehicles;
    @FXML
    private TableColumn<Vehicle, String> tcPlateID;
    @FXML
    private TableColumn<Vehicle, String> tcBrand;
    @FXML
    private TableColumn<Vehicle, String> tcModel;
    @FXML
    private TableColumn<Vehicle, String> tcCurrentKm;
    @FXML
    private Label lblVehicleError;
    @FXML
    private TextField txtCheckupKm;
    @FXML
    private Label lblCheckupKmError;
    @FXML
    private DatePicker dpCheckupDate;
    @FXML
    private Label lblCheckupDateError;

    public RegisterCheckupUI() {
        controller = new RegisterCheckupController();
    }

    private RegisterCheckupController getController() {
        return controller;
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        tcPlateID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlateId()));
        tcBrand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        tcModel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        tcCurrentKm.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCurrentKm())));

        tbVehicles.getItems().addAll(getController().getVehicles());
    }

    private void submitData() {

        String information = "Vehicle: " + tbVehicles.getSelectionModel().getSelectedItem().getPlateId() + "\n" +
                "Checkup Date: " + dpCheckupDate.getValue().toString() + "\n" +
                "Checkup Km: " + txtCheckupKm.getText() + "\n\n" +
                "Do you want to proceed?";
        Alert closeAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, MainApp.APP_TITLE,
                "You're about to register the following vehicle..",
                information);
        if (closeAlert.showAndWait().get() == ButtonType.OK) {
            Optional<VehicleCheckup> checkup = getController().registerVehicleCheckup(
                    tbVehicles.getSelectionModel().getSelectedItem(),
                    convertDate(dpCheckupDate.getValue().toString()),
                    Integer.parseInt(txtCheckupKm.getText())
            );
            String operationStatus;
            if (checkup.isEmpty()) {
                operationStatus = "Checkup was already registered.";
            } else {
                operationStatus = "Checkup registered successfully.";
            }
            Alert operationAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, MainApp.APP_TITLE,
                    "Register Checkup.",
                    operationStatus);
            operationAlert.show();
            btnClearAction();
        }

    }

    private String convertDate(String date) {
        String[] dateParts = date.split("-");
        return dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];
    }

    private void setError(Control field, Label lblError, String message) {
        field.setStyle("-fx-border-color: red;");
        lblError.setText(message);
        lblError.setVisible(true);
    }

    private void clearError(Control field, Label lblError) {
        field.setStyle("-fx-border-color: none;");
        lblError.setText("");
        lblError.setVisible(false);
    }

    private int validateCheckupDate(LocalDate checkupDateField) {
        String checkupDate = "";
        try {
            checkupDate = checkupDateField.toString();
        } catch (Exception e) {
            setError(dpCheckupDate, lblCheckupDateError, "Invalid date.");
            return 0;
        }

        if (checkupDate.isEmpty() || checkupDate.isBlank()) {
            setError(dpCheckupDate, lblCheckupDateError, "Checkup date cannot be null or empty.");
            return 0;
        }

        Date checkupDateObj;
        try {
            checkupDate = convertDate(checkupDate);
            checkupDateObj = new Date(checkupDate);
        }catch (Exception e){
            setError(dpCheckupDate, lblCheckupDateError, "Invalid date format.");
            return 0;
        }

        if (checkupDateObj.compareTo(tbVehicles.getSelectionModel().getSelectedItem().getRegisterDate()) < 0) {
            setError(dpCheckupDate, lblCheckupDateError, "Checkup date must be after vehicle registration date.");
            return 0;
        }

        clearError(dpCheckupDate, lblCheckupDateError);
        return 1;
    }

    private int validateCheckupKms(String checkupKm) {
        try{
            Integer.parseInt(checkupKm);
        }catch (Exception e){
            setError(txtCheckupKm, lblCheckupKmError, "Checkup km must be a number.");
            return 0;
        }

        if(Integer.parseInt(checkupKm) < 0 ){
            setError(txtCheckupKm, lblCheckupKmError, "Checkup km must be a positive number.");
            return 0;
        }else if( Integer.parseInt(checkupKm) > tbVehicles.getSelectionModel().getSelectedItem().getCurrentKm()){
            setError(txtCheckupKm, lblCheckupKmError, "Checkup km must be less than the current km.");
            return 0;
        }

        clearError(txtCheckupKm, lblCheckupKmError);
        return 1;
    }

    private int validateSelectedVehicle() {
        if (tbVehicles.getSelectionModel().getSelectedItem() == null) {
            setError(tbVehicles, lblVehicleError, "You must select a vehicle.");
            return 0;
        }

        clearError(tbVehicles, lblVehicleError);
        return 1;
    }

    @FXML
    private void btnSubmitAction() {
        int isAllValid = 0;
        isAllValid += validateSelectedVehicle();
        isAllValid += validateCheckupDate(dpCheckupDate.getValue());
        isAllValid += validateCheckupKms(txtCheckupKm.getText());
        if(isAllValid == 3){ submitData(); }
    }

    @FXML
    private void btnClearAction() {
        tbVehicles.getSelectionModel().clearSelection();
        clearError(tbVehicles, lblVehicleError);
        dpCheckupDate.setValue(null);
        clearError(dpCheckupDate, lblCheckupDateError);
        txtCheckupKm.clear();
        clearError(txtCheckupKm, lblCheckupKmError);
    }

}
