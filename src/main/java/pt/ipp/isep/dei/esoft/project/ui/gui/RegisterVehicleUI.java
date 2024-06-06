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
import java.time.LocalDate;
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
        Alert closeAlert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Register Vehicle",
                "You're about to register the following vehicle..",
                information);
        if (closeAlert.showAndWait().get() == ButtonType.OK) {

            Optional<Vehicle> vehicle = getRegisterVehicleController().createVehicle(
                    txtPlateID.getText().trim(),
                    txtBrand.getText().trim(),
                    txtModel.getText().trim(),
                    cbType.getValue(),
                    Integer.parseInt(txtTare.getText().trim()),
                    Integer.parseInt(txtGrossWeight.getText().trim()),
                    Integer.parseInt(txtCurrentKm.getText().trim()),
                    convertDate(dpRegisterDate.getValue().toString().trim()),
                    convertDate(dpAcquisitionDate.getValue().toString().trim()),
                    Integer.parseInt(txtCheckupFrequency.getText().trim()));

            AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Vehicle", "Register Vehicle",
                    vehicle.isPresent() ? "Vehicle registered successfully"
                            : "Vehicle was already registered").show();

        }
        btnClearAction();

    }

    private String convertDate(String formFormat) {
        String[] dateParts = formFormat.split("-");
        return dateParts[2] + "/" + dateParts[1] + "/" + dateParts[0];
    }

    private boolean validateAllData() {
        boolean plateIDValid = validatePlateID();
        boolean typeValid = validateType();
        boolean modelValid = validateModel();
        boolean brandValid = validateBrand();
        boolean tareValid = validateTare();
        boolean grossWeightValid = validateGrossWeight();
        boolean registerDateValid = validateRegisterDate();
        boolean acquisitionDateValid = validateAcquisitionDate();
        boolean checkupFrequencyValid = validateCheckupFrequencyKm();
        boolean currentKmValid = validateCurrentKm();


        return plateIDValid
                && typeValid
                && modelValid
                && brandValid
                && tareValid
                && grossWeightValid
                && registerDateValid
                && acquisitionDateValid
                && checkupFrequencyValid
                && currentKmValid;

    }

    private boolean validateType() {
        if (cbType.getValue() == null) {
            setError(cbType, lblTypeError, "Select a type");
            return false;
        }
        clearError(cbType, lblTypeError);
        return true;

    }

    private boolean validateModel() {
        String stringBrand = txtModel.getText().trim();
        if (stringBrand.isEmpty()) {
            setError(txtModel, lblModelError, "Model is empty");
            return false;
        }
        clearError(txtModel, lblModelError);
        return true;
    }

    private boolean validateBrand() {

        String stringBrand = txtBrand.getText().trim();
        if (stringBrand.isEmpty()) {
            setError(txtBrand, lblBrandError, "Brand is empty");
            return false;
        }
        clearError(txtBrand, lblBrandError);
        return true;
    }

    private boolean validateRegisterDate() {

        if (dpRegisterDate.getValue() == null) {
            setError(dpRegisterDate, lblRegisterDateError, "Register date is empty");
            return false;
        }

        //validacao aqui que nao pode ser data de futuro
        LocalDate currentDate = LocalDate.now();
        LocalDate registerDate = dpRegisterDate.getValue();

        if (registerDate.isAfter(currentDate)) {
            setError(dpRegisterDate, lblRegisterDateError, "Register date cannot be in the future");
            return false;
        }

        if (!txtPlateID.getText().isEmpty()) {
            boolean validDate = validateDate();
            if (validDate) {
                clearError(dpRegisterDate, lblRegisterDateError);
                return true;
            } else {
                return false;
            }
        }

        clearError(dpRegisterDate, lblRegisterDateError);
        return true;
    }

    private boolean validatePlateID() {

        String plateID = txtPlateID.getText().trim();

        if (plateID.isEmpty()) {
            setError(txtPlateID, lblPlateIDError, "Plate ID is empty");
            return false;
        }
        Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-[A-Z]{2}$");
        Pattern pattern2 = Pattern.compile("^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");
        Pattern pattern3 = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$");


        if (pattern1.matcher(plateID).matches() || pattern2.matcher(plateID).matches() || pattern3.matcher(plateID).matches()) {
            clearError(txtPlateID, lblPlateIDError);
            return true;
        } else {
            setError(txtPlateID, lblPlateIDError, "Plate ID must follow the format 00-00-AA, 00-AA-00 or AA-00-AA.");
            return false;
        }
    }

    private boolean validateTare() {
        double tare;

        String stringTare = txtTare.getText().trim();
        if (stringTare.isEmpty()) {
            setError(txtTare, lblTareError, "Tare is empty.");
            return false;
        }

        try {
            tare = Integer.parseInt(stringTare);
        } catch (Exception e) {
            setError(txtTare, lblTareError, "Tare is not a valid number.");
            return false;
        }
        if (tare < 0) {
            setError(txtTare, lblTareError, "Tare must be a positive number.");
            return false;
        } else {
            clearError(txtTare, lblTareError);
        }
        clearError(txtTare, lblTareError);
        return true;
    }

    private boolean validateGrossWeight() {
        double grossWeight;
        double tare;

        String stringGrossWeight = txtGrossWeight.getText().trim();
        String stringTare = txtTare.getText().trim();

        if (stringGrossWeight.isEmpty()) {
            setError(txtGrossWeight, lblGrossWeightError, "Gross Weight is empty");
            return false;
        }


        try {
            grossWeight = Double.parseDouble(stringGrossWeight);

        } catch (Exception e) {
            setError(txtGrossWeight, lblGrossWeightError, "Gross Weight is not a valid number.");
            return false;
        }
        try {
            tare = Double.parseDouble(stringTare);
        } catch (Exception e) {
            setError(txtTare, lblTareError, "Tare is not a valid number");
            return false;
        }
        if (grossWeight < 0) {
            setError(txtGrossWeight, lblGrossWeightError, "Gross weight must be a positive number");
            return false;
        }

        if (grossWeight < tare) {
            setError(txtGrossWeight, lblGrossWeightError, "Gross weight must be greater than tare");
            return false;
        }
        clearError(txtGrossWeight, lblGrossWeightError);
        return true;
    }

    private boolean validateDate() {
        String registerDate = dpRegisterDate.getValue().toString().trim();

        Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-[A-Z]{2}$");
        Pattern pattern2 = Pattern.compile("^[0-9]{2}-[A-Z]{2}-[0-9]{2}$");
        Pattern pattern3 = Pattern.compile("^[A-Z]{2}-[0-9]{2}-[A-Z]{2}$");
        String[] registerDateParts = registerDate.split("-");
        int registerDateYear = Integer.parseInt(registerDateParts[0]);

        if (registerDateYear > 2005 && pattern1.matcher(txtPlateID.getText()).matches()) {
            setError(dpRegisterDate, lblRegisterDateError, "With plateID format 00-00-AA, the register date must be before 2005");
            return false;
        } else if ((registerDateYear <= 2005 || registerDateYear > 2020) && pattern2.matcher(txtPlateID.getText()).matches()) {
            setError(dpRegisterDate, lblRegisterDateError, "With plateID format 00-AA-00, the register date must be between 2005 and 2020");
            return false;
        } else if (registerDateYear <= 2020 && pattern3.matcher(txtPlateID.getText()).matches()) {
            setError(dpRegisterDate, lblRegisterDateError, "With plateID format AA-00-AA, the register date must be after 2020");
            return false;
        }
        return true;
    }

    private boolean validateAcquisitionDate() {
        if (dpAcquisitionDate.getValue() == null) {
            setError(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition date is empty");
            return false;
        }

        LocalDate acquisitionDate = dpAcquisitionDate.getValue();
        LocalDate registerDate = dpRegisterDate.getValue();

        //validacao aqui que nao pode ser data de futuro
        LocalDate currentDate = LocalDate.now();
        if (acquisitionDate.isAfter(currentDate)) {
            setError(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition date cannot be in the future");
            return false;
        }

        if (acquisitionDate.isBefore(registerDate)) {
            setError(dpAcquisitionDate, lblAcquisitionDateError, "Acquisition date must be after the register date.");
            return false;
        }
        clearError(dpAcquisitionDate, lblAcquisitionDateError);
        return true;
    }

    private boolean validateCurrentKm() {

        String stringCurrentKm = txtCurrentKm.getText().trim();
        if (stringCurrentKm.isEmpty()) {
            setError(txtCurrentKm, lblCurrentKmError, "Current Km is empty");
            return false;
        }
        int currentKm;

        try {
            currentKm = Integer.parseInt(stringCurrentKm);
        } catch (Exception e) {
            setError(txtCurrentKm, lblCurrentKmError, "Current Km is not a valid number.");
            return false;
        }
        if (currentKm < 0) {
            setError(txtCurrentKm, lblCurrentKmError, "Current Km must be a positive number.");
            return false;
        } else {
            clearError(txtCurrentKm, lblCurrentKmError);
        }
        clearError(txtCurrentKm, lblCurrentKmError);
        return true;
    }

    private boolean validateCheckupFrequencyKm() {

        String stringCheckupFrequency = txtCheckupFrequency.getText().trim();
        if (stringCheckupFrequency.isEmpty()) {
            setError(txtCheckupFrequency, lblCheckupFrequencyError, "Checkup frequency Km is empty.");
            return false;
        }

        int checkupFrequency;


        try {
            checkupFrequency = Integer.parseInt(stringCheckupFrequency);
        } catch (Exception e) {
            setError(txtCheckupFrequency, lblCheckupFrequencyError, "Checkup frequency Km is not a valid number.");
            return false;
        }
        if (checkupFrequency < 0) {
            setError(txtCheckupFrequency, lblCheckupFrequencyError, "Checkup frequency Km must be a positive number.");
            return false;
        } else {
            clearError(txtCheckupFrequency, lblCheckupFrequencyError);
        }
        clearError(txtCheckupFrequency, lblCheckupFrequencyError);
        return true;
    }

    @FXML
    private void btnSubmitAction() {

        try {
            if (validateAllData()) {
                submitData();
            }
        } catch (IllegalArgumentException ie) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Register Vehicle Error", ie.getMessage()).show();
        }

    }

    @FXML
    private void btnClearAction() {
        clearError(txtPlateID, lblPlateIDError);
        txtPlateID.clear();
        clearError(cbType, lblTypeError);
        cbType.getSelectionModel().clearSelection();
        clearError(txtBrand, lblBrandError);
        txtBrand.clear();
        clearError(txtModel, lblModelError);
        txtModel.clear();
        clearError(txtTare, lblTareError);
        txtTare.clear();
        clearError(txtGrossWeight, lblGrossWeightError);
        txtGrossWeight.clear();
        clearError(dpRegisterDate, lblRegisterDateError);
        dpRegisterDate.setValue(null);
        clearError(dpAcquisitionDate, lblAcquisitionDateError);
        dpAcquisitionDate.setValue(null);
        clearError(txtCheckupFrequency, lblCheckupFrequencyError);
        txtCheckupFrequency.clear();
        clearError(txtCurrentKm, lblCurrentKmError);
        txtCurrentKm.clear();
    }
}
