package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.controller.RegisterVehicleController;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.tools.VehicleType;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class RegisterVehicleUI implements Initializable {

    private final RegisterVehicleController controller;

    @FXML
    private TextField txtPlateID;
    @FXML
    private ComboBox<String> cbType;
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtTare;
    @FXML
    private TextField txtGrossWeight;
    @FXML
    private DatePicker dpRegisterDate;
    @FXML
    private DatePicker dpAcquisitionDate;
    @FXML
    private TextField txtCheckupFrequency;
    @FXML
    private TextField txtCurrentKm;

    @FXML
    private Label lblPlateIDError;
    @FXML
    private Label lblTypeError;
    @FXML
    private Label lblBrandError;
    @FXML
    private Label lblModelError;
    @FXML
    private Label lblTareError;
    @FXML
    private Label lblGrossWeightError;
    @FXML
    private Label lblRegisterDateError;
    @FXML
    private Label lblAcquisitionDateError;
    @FXML
    private Label lblCheckupFrequencyError;
    @FXML
    private Label lblCurrentKmError;

    public RegisterVehicleUI() {
        controller = new RegisterVehicleController();
    }

    public RegisterVehicleController getRegisterVehicleController() {
        return controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTypeComboBox();
    }

    private void fillTypeComboBox() {
        for (VehicleType type : VehicleType.values()) {
            cbType.getItems().add(type.toString());
        }
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

    private void submitData() {

        String information = "PlateID: " + txtPlateID.getText() + "\n" + "Type: " + cbType.getValue() + "\n" + "Brand: " + txtBrand.getText() + "\n" + "Model: " + txtModel.getText() + "\n" + "Tare: " + txtTare.getText() + "\n" + "Gross Weight: " + txtGrossWeight.getText() + "\n" + "Register Date: " + dpRegisterDate.getValue().toString() + "\n" + "Acquisition Date: " + dpAcquisitionDate.getValue().toString() + "\n" + "Checkup Frequency: " + txtCheckupFrequency.getText() + "\n" + "Current Km: " + txtCurrentKm.getText() + "\n" + "Do you wish yo proceed ?";
        Alert closeAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, MainApp.APP_TITLE,
                "You're about to register the following vehicle..",
                information);
        if (closeAlert.showAndWait().get() == ButtonType.OK) {
            Optional<Vehicle> vehicle = getRegisterVehicleController().createVehicle(
                    txtPlateID.getText(),
                    txtBrand.getText(),
                    txtModel.getText(),
                    cbType.getValue(),
                    Integer.parseInt(txtTare.getText()),
                    Integer.parseInt(txtGrossWeight.getText()),
                    Integer.parseInt(txtCurrentKm.getText()),
                    convertDate(dpRegisterDate.getValue().toString()),
                    convertDate(dpAcquisitionDate.getValue().toString()),
                    Integer.parseInt(txtCheckupFrequency.getText()));
            String operationStatus;
            if (vehicle.isEmpty()) {
                operationStatus = "Vehicle was already registered.";
            } else {
                operationStatus = "Vehicle registered successfully.";
            }
            Alert operationAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, MainApp.APP_TITLE,
                    "Register Vehicle.",
                    operationStatus);
            operationAlert.show();
        }

    }

    private String convertDate(String formFormat) {
        String[] dateParts = formFormat.split("-");
        return dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];
    }

    private boolean validateAllNotNullOrEmpty(){
        int isAllValid = 0;
        isAllValid += validateNotNullorEmpty(txtPlateID, lblPlateIDError, "Plate ID");
        isAllValid += validateNotNullorEmpty(cbType, lblTypeError, "Type");
        isAllValid += validateNotNullorEmpty(txtBrand, lblBrandError, "Brand");
        isAllValid += validateNotNullorEmpty(txtModel, lblModelError, "Model");
        isAllValid += validateNotNullorEmpty(txtTare, lblTareError, "Tare");
        isAllValid += validateNotNullorEmpty(txtGrossWeight, lblGrossWeightError, "Gross Weight");
        isAllValid += validateNotNullorEmpty(dpRegisterDate, lblRegisterDateError, "Register Date");
        isAllValid += validateNotNullorEmpty(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition Date");
        isAllValid += validateNotNullorEmpty(txtCheckupFrequency, lblCheckupFrequencyError, "Checkup Frequency");
        isAllValid += validateNotNullorEmpty(txtCurrentKm, lblCurrentKmError, "Current Km");
        if(isAllValid != 10){
            return false;
        }
        return true;
    }

    private int validateNotNullorEmpty(Control field, Label label, String context) {
        if(field instanceof TextField){
            TextField textField = (TextField) field;
            if (textField.getText() == null || textField.getText().trim().isEmpty()) {
                setError(textField, label, context + " cannot be empty.");
                return 0;
            }else {
                clearError(textField, label);
            }
        }else if(field instanceof DatePicker){
            DatePicker datePicker = (DatePicker) field;
            if (datePicker.getValue() == null) {
                setError(datePicker, label, context + " cannot be empty.");
                return 0;
            }else {
                clearError(datePicker, label);
            }
        }else if(field instanceof ComboBox){
            ComboBox<String> comboBox = (ComboBox<String>) field;
            if (comboBox.getValue() == null || comboBox.getValue().trim().isEmpty()) {
                setError(comboBox, label,"No " + context + " has been chosen.");
                return 0;
            }else {
                clearError(comboBox, label);
            }
        }
        return 1;
    }

    private boolean validatePlateID(String plateID) {
        Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-[A-Z]{2}$");
        Pattern pattern2 = Pattern.compile("^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");
        Pattern pattern3 = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$");

        if (!pattern1.matcher(plateID).matches() && !pattern2.matcher(plateID).matches() && !pattern3.matcher(plateID).matches()){
            setError(txtPlateID, lblPlateIDError, "Plate ID must follow the format 00-00-AA, 00-AA-00 or AA-00-AA.");
            return false;
        }else {
            clearError(txtPlateID, lblPlateIDError);
        }

        return true;
    }

    private boolean validateTare(String stringTare) {
        double tare;
        try{
            tare = Integer.parseInt(stringTare);
        }catch (Exception e){
            setError(txtTare, lblTareError, "Tare is not a valid number.");
            return false;
        }
        if(tare < 0){
            setError(txtTare, lblTareError, "Tare must be a positive number.");
            return false;
        }else {
            clearError(txtTare, lblTareError);
        }
        return true;
    }

    private boolean validateGrossWeight(String stringGrossWeight, String stringTare) {
        double grossWeight;
        double tare;
        try{
            grossWeight = Integer.parseInt(stringGrossWeight);
        }catch (Exception e){
            setError(txtGrossWeight, lblGrossWeightError, "Gross Weight is not a valid number.");
            return false;
        }
        try {
            tare = Integer.parseInt(stringTare);
        }catch (Exception e){
            setError(txtTare, lblTareError, "Tare is not a valid number");
            return false;
        }
        if(grossWeight < 0 && grossWeight < tare){
            setError(txtGrossWeight, lblGrossWeightError, "Gross weight must be a positive number and greater than the tare.");
            return false;
        }else{
            clearError(txtGrossWeight, lblGrossWeightError);
        }
        return true;
    }

    private boolean validateDate(String registerDate) {
        Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-[A-Z]{2}$");
        Pattern pattern2 = Pattern.compile("^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");
        Pattern pattern3 = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$");
        String[] registerDateParts = registerDate.split("-");
        int registerDateYear = Integer.parseInt(registerDateParts[0]);

        if (registerDateYear > 2005 && pattern1.matcher(txtPlateID.getText()).matches()) {
            setError(dpRegisterDate, lblRegisterDateError, "Invalid date, with the plateID format 00-00-AA, the register date must be before 2005");
            return false;
        } else if ((registerDateYear <= 2005 || registerDateYear > 2020) && pattern2.matcher(txtPlateID.getText()).matches()) {
            setError(dpRegisterDate, lblRegisterDateError, "Invalid date, with the plateID format 00-AA-00, the register date must be between 2005 and 2020");
            return false;
        } else if (registerDateYear <= 2020 && pattern3.matcher(txtPlateID.getText()).matches()) {
            setError(dpRegisterDate, lblRegisterDateError, "Invalid date, with the plateID format AA-00-AA, the register date must be after 2020");
            return false;
        }
        return true;
    }

    private boolean validateAcquisitionDate(String acquisitionDate, String registerDate) {
        String[] acquisitionDateParts = acquisitionDate.split("-");
        String[] registerDateParts = registerDate.split("-");
        if( Integer.parseInt(acquisitionDateParts[0]) < Integer.parseInt(registerDateParts[0]) ){
            setError(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition date must be before the register date.");
            return false;
        }else if ( Integer.parseInt(acquisitionDateParts[1]) < Integer.parseInt(registerDateParts[1]) ){
            setError(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition date must be before the register date.");
            return false;
        }else if ( Integer.parseInt(acquisitionDateParts[2]) < Integer.parseInt(registerDateParts[2]) ){
            setError(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition date must be before the register date.");
            return false;
        }
        return true;
    }

    private boolean validateCurrentKm(String stringCurrentKm) {
        int currentKm;
        try{
            currentKm = Integer.parseInt(stringCurrentKm);
        }catch (Exception e){
            setError(txtCurrentKm, lblCurrentKmError, "Current Km is not a valid number.");
            return false;
        }
        if(currentKm < 0){
            setError(txtCurrentKm, lblCurrentKmError, "Current Km must be a positive number.");
            return false;
        }else{
            clearError(txtCurrentKm, lblCurrentKmError);
        }
        return true;
    }

    private boolean validateCheckupFrequencyKm(String stringCheckupFrequency) {
        int checkupFrequency;
        try{
            checkupFrequency = Integer.parseInt(stringCheckupFrequency);
        }catch (Exception e){
            setError(txtCheckupFrequency, lblCheckupFrequencyError, "Checkup frequency Km is not a valid number.");
            return false;
        }
        if(checkupFrequency < 0){
            setError(txtCheckupFrequency, lblCheckupFrequencyError, "Checkup frequency Km must be a positive number.");
            return false;
        }else{
            clearError(txtCheckupFrequency, lblCheckupFrequencyError);
        }
        return true;
    }

    @FXML
    private void btnSubmitAction() {
        boolean isAllValid = true;
        if(!validatePlateID(txtPlateID.getText())){ isAllValid = false; }
        if(!validateTare(txtTare.getText())){ isAllValid = false; }
        if(!validateGrossWeight(txtGrossWeight.getText(),txtTare.getText())){ isAllValid = false; }
        if(dpRegisterDate.getValue() != null){
            if(!validateDate(dpRegisterDate.getValue().toString())){ isAllValid = false; }
        }
        if(dpAcquisitionDate.getValue() != null && dpRegisterDate.getValue() != null){
            if(!validateAcquisitionDate(dpAcquisitionDate.getValue().toString(), dpRegisterDate.getValue().toString())){ isAllValid = false; }
        }
        System.out.println(isAllValid);
        if(!validateCurrentKm(txtCurrentKm.getText())){ isAllValid = false; }
        if(!validateCheckupFrequencyKm(txtCheckupFrequency.getText())){ isAllValid = false; }
        if(!validateAllNotNullOrEmpty()){ isAllValid = false; }

        if(isAllValid){ submitData(); }else{
            AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "There was an error registering a vehicle.","Please correct the highlighted errors and try again.").show();
        }
    }

    @FXML
    private void btnClearAction() {
        clearError(txtPlateID, lblPlateIDError);
        clearError(cbType, lblTypeError);
        clearError(txtBrand, lblBrandError);
        clearError(txtModel, lblModelError);
        clearError(txtTare, lblTareError);
        clearError(txtGrossWeight, lblGrossWeightError);
        clearError(dpRegisterDate, lblRegisterDateError);
        clearError(dpAcquisitionDate, lblAcquisitionDateError);
        clearError(txtCheckupFrequency, lblCheckupFrequencyError);
        clearError(txtCurrentKm, lblCurrentKmError);
    }

}
