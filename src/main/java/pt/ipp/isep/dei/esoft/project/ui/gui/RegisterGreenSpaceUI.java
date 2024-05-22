package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.RegisterGreenSpaceController;
import pt.ipp.isep.dei.esoft.project.tools.GreenSpaceType;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterGreenSpaceUI implements Initializable {
    private final RegisterGreenSpaceController controller;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtStreetNumber;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtDistrict;
    @FXML
    private TextField txtArea;
    @FXML
    private ComboBox<GreenSpaceType> cbType;
    @FXML
    private Label lblNameError;
    @FXML
    private Label lblStreetError;
    @FXML
    private Label lblStreetNumberError;
    @FXML
    private Label lblPostalCodeError;
    @FXML
    private Label lblCityError;
    @FXML
    private Label lblDistrictError;
    @FXML
    private Label lblAreaError;
    @FXML
    private Label lblTypeError;


    public RegisterGreenSpaceUI() {
        this.controller = new RegisterGreenSpaceController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGreenSpaceType();
    }

    private void fillGreenSpaceType() {
    }

    @FXML
    private void btnSubmit() {

    }

    @FXML
    private void btnClear() {
        clearAllFieldsAndErrors();
    }

    public boolean validateAllInputs() {
        boolean streetValid = validateStreet();
        boolean streetNumberValid = validateStreetNumber();
        boolean postalCodeValid = validatePostalCode();
        boolean cityValid = validatePostalCode();
        boolean districtValid = validateDistrict();

        return streetValid
                && streetNumberValid
                && postalCodeValid
                && cityValid
                && districtValid;
    }

    private boolean validateStreet() {
        String streetString = txtStreet.getText().trim();
        if (streetString.isEmpty()) {
            displayErrorLayout(txtStreet, lblStreetError, "Street is empty");
            return false;
        }
        clearLayoutErrors(txtStreet, lblStreetError);
        return true;
    }

    private boolean validateStreetNumber() {
        String streetNumber = txtStreetNumber.getText().trim();
        String positiveIntegerPattern = "^[1-9]\\d*$";

        if (streetNumber.isEmpty()) {
            displayErrorLayout(txtStreetNumber, lblStreetNumberError, "Number is empty");
            return false;
        }

        if (streetNumber.matches(positiveIntegerPattern)) {
            clearLayoutErrors(txtStreetNumber, lblStreetNumberError);
            return true;
        } else {
            displayErrorLayout(txtStreetNumber, lblStreetNumberError, "Invalid number");
            return false;
        }
    }

    private boolean validatePostalCode() {

        String postalCode = txtPostalCode.getText().trim();
        if (postalCode.isEmpty()) {
            displayErrorLayout(txtPostalCode, lblPostalCodeError, "Can't be empty");
            return false;
        }

        if (postalCode.length() != 8 || postalCode.charAt(4) != '-') {
            displayErrorLayout(txtPostalCode, lblPostalCodeError, "Format must be XXXX-XXX");
            return false;
        }

        char[] postalCodeByLetters = postalCode.toCharArray();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                displayErrorLayout(txtPostalCode, lblPostalCodeError, "Can't contain letters");
                return false;
            }
        }

        for (int i = 5; i < postalCodeByLetters.length; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                displayErrorLayout(txtPostalCode, lblPostalCodeError, "Can't contain letters");
                return false;
            }
        }
        clearLayoutErrors(txtPostalCode, lblPostalCodeError);
        return true;
    }

    private boolean validateCity() {

        if (txtCity.getText().trim().isEmpty()) {
            displayErrorLayout(txtCity, lblCityError, "City is empty");
            return false;
        }
        clearLayoutErrors(txtCity, lblCityError);
        return true;
    }

    private boolean validateDistrict() {

        if (txtDistrict.getText().trim().isEmpty()) {
            displayErrorLayout(txtDistrict, lblDistrictError, "District is empty");
            return false;
        }
        clearLayoutErrors(txtDistrict, lblDistrictError);
        return true;
    }


    private void displayErrorLayout(Control controlObject, Label labelToShowError, String errorMessage) {
        controlObject.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLayoutErrors(Control controlObject, Label labelWithError) {
        controlObject.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    private void clearAllFieldsAndErrors() {
        txtName.clear();
        txtName.setStyle("");
        txtStreet.clear();
        txtStreet.setStyle("");
        txtStreetNumber.clear();
        txtStreetNumber.setStyle("");
        txtPostalCode.clear();
        txtPostalCode.setStyle("");
        txtCity.clear();
        txtCity.setStyle("");
        txtDistrict.clear();
        txtDistrict.setStyle("");
        txtArea.clear();
        txtArea.setStyle("");
        cbType.getSelectionModel().clearSelection();
        cbType.setStyle("");
        lblNameError.setVisible(false);
        lblStreetError.setVisible(false);
        lblStreetNumberError.setVisible(false);
        lblPostalCodeError.setVisible(false);
        lblCityError.setVisible(false);
        lblDistrictError.setVisible(false);
        lblAreaError.setVisible(false);
        lblTypeError.setVisible(false);
    }
}
