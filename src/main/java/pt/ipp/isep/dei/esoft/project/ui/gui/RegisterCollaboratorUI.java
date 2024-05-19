package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import org.w3c.dom.Text;
import pt.ipp.isep.dei.esoft.project.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.MobileOperator;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Register Collaborator UI class
 */
public class RegisterCollaboratorUI implements Initializable {

    private final RegisterCollaboratorController controller;
    private List<Job> jobs;

    @FXML
    private TextField txtName;
    @FXML
    private DatePicker dpBirthdate;
    @FXML
    private DatePicker dpAdmissionDate;
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
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtDocNumber;
    @FXML
    private ComboBox<String> cbDocType;
    @FXML
    private ComboBox<String> cbJob;

    @FXML
    private Label lblBirthdateError;

    @FXML
    private Label lblAdmissionDateError;

    @FXML
    private Label lblNameError;

    @FXML
    private Label lblEmailError;

    @FXML
    private Label lblPhoneError;

    @FXML
    private Label lblJobError;

    @FXML
    private Label lblDocTypeError;

    @FXML
    private Label lblDocNumberError;

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


    /**
     * Constructor for the RegisterCollaboratorUI class
     */
    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
        jobs = getRegisterCollaboratorController().getJobList();
    }

    /**
     * Returns the controller
     *
     * @return the controller
     */
    public RegisterCollaboratorController getRegisterCollaboratorController() {
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillDocTypeComboBox();
        fillJobComboBox();

    }

    @FXML
    private void btnSubmitAction() {

        // String cfAdmissionDate = "";
        try {

/*
            try {
                cfAdmissionDate = convertFormat(dpAdmissionDate.getValue().toString());
            } catch (NullPointerException nullPointerException) {
                dpAdmissionDate.setStyle("-fx-border-color: red");
                lblAdmissionDateError.setVisible(true);
                lblAdmissionDateError.setText("Field cannot be empty");
            }
            String name = "";
            try {
                name = txtName.getText().trim();
            } catch (IllegalArgumentException e) {
                txtName.setStyle("-fx-border-color: red");
                lblNameError.setVisible(true);
                lblNameError.setText(e.getMessage());
            }
*/
            if (validateAllErrors()) {
                Job selectedJob = getSelectedJob();
                IdDocType selectedIdDocType = getSelectedIdDocType();
                Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(
                        txtName.getText().trim(),
                        convertFormat(dpBirthdate.getValue().toString()),
                        convertFormat(dpAdmissionDate.getValue().toString()),
                        txtStreet.getText().trim(),
                        Integer.parseInt(txtStreetNumber.getText().trim()),
                        txtPostalCode.getText().trim(),
                        txtCity.getText().trim(),
                        txtDistrict.getText().trim(),
                        txtEmail.getText().trim(),
                        txtPhoneNumber.getText().trim(),
                        selectedIdDocType,
                        txtDocNumber.getText().trim(),
                        selectedJob
                );

                AlertUI.createAlert(Alert.AlertType.INFORMATION, MainApp.APP_TITLE, "Register a collaborator.",
                        collaborator.isPresent() ? "Collaborator added with success."
                                : "This collaborator is already in the system").show();
            } else {
                throw new IllegalArgumentException("Please correct the highlighted errors and try again.");
            }
        } catch (IllegalArgumentException e) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "ERROR", "Register Collaborator", e.getMessage());
        }
    }


    private String convertFormat(String date) {
        // yyyy-mm-dd to dd/mm/yyyy
        String[] dateSplit = date.split("-");

        return dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0];
    }

    private void fillDocTypeComboBox() {
        cbDocType.getItems().addAll("CC", "BI", "PASSPORT");
    }

    private void fillJobComboBox() {
        for (Job job : jobs) {
            cbJob.getItems().add(job.getJobName());
        }
    }

    private Job getSelectedJob() {
        String selectedJob = cbJob.getValue();

        for (Job job : jobs) {
            if (job.getJobName().equals(selectedJob)) {
                return job;
            }
        }
        return null;
    }

    private IdDocType getSelectedIdDocType() {
        int selectedIndex = cbDocType.getSelectionModel().getSelectedIndex();

        switch (selectedIndex) {
            case 0:
                return IdDocType.CC;
            case 1:
                return IdDocType.BI;
            case 2:
                return IdDocType.PASSPORT;
        }
        return null;
    }

    public boolean validateAllErrors() {
        boolean nameValid = validateName(txtName);
        boolean emailValid = validateEmail(txtEmail);
        boolean mobileNumberValid = validatePhoneNumber(txtPhoneNumber);
        boolean jobValid = validateJob(getSelectedJob());
        boolean docTypeValid = validateIdDocType(getSelectedIdDocType());
        boolean docNumberValid = validateDocumentNumber(txtDocNumber, getSelectedIdDocType());
        boolean birthdateValid = validateBirthdate(dpBirthdate);
        boolean admissionDateValid = validateAdmissionDate(dpAdmissionDate, dpBirthdate);
        boolean streetValid = validateStreet(txtStreet);
        boolean streetNumberValid = validateStreetNumber(txtStreetNumber);
        boolean postalCodeValid = validatePostalCode(txtPostalCode);
        boolean cityValid = validateCity(txtCity);
        boolean districtValid = validateDistrict(txtDistrict);

        return nameValid && emailValid && mobileNumberValid && jobValid && docTypeValid && docNumberValid && birthdateValid && admissionDateValid && streetValid && streetNumberValid && postalCodeValid && cityValid && districtValid;
    }

    private boolean validateCity(TextField txtCity) {

        if (txtCity.getText().trim().isEmpty()) {
            displayErrorLayout(txtCity, lblCityError, "City is empty");
            return false;
        }
        clearLabelError(txtCity, lblCityError);
        return true;
    }

    private boolean validateDistrict(TextField txtDistrict) {

        if (txtDistrict.getText().trim().isEmpty()) {
            displayErrorLayout(txtDistrict, lblDistrictError, "District is empty");
            return false;
        }
        clearLabelError(txtDistrict, lblDistrictError);
        return true;
    }

    private boolean validatePostalCode(TextField txtPostalCode) {

        String postalCode = txtPostalCode.getText().trim();
        if (postalCode.isEmpty()) {
            displayErrorLayout(txtPostalCode, lblPostalCodeError, "Postal code is empty");
            return false;
        }

        if (postalCode.length() != 8 || postalCode.charAt(4) != '-') {
            displayErrorLayout(txtPostalCode, lblPostalCodeError, "Format must be XXXX-XXX");
            return false;
        }

        char[] postalCodeByLetters = postalCode.toCharArray();

        for (int i = 0; i < 4; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                displayErrorLayout(txtPostalCode, lblPostalCodeError, "Postal code can't contain letters");
                return false;
            }
        }

        for (int i = 5; i < postalCodeByLetters.length; i++) {
            if (!Character.isDigit(postalCodeByLetters[i])) {
                displayErrorLayout(txtPostalCode, lblPostalCodeError, "Postal code can't contain letters");
                return false;
            }
        }
        clearLabelError(txtPostalCode, lblPostalCodeError);
        return true;
    }

    private boolean validateStreetNumber(TextField txtStreetNumber) {
        String streetNumber = txtStreetNumber.getText().trim();
        String positiveIntegerPattern = "^[1-9]\\d*$";

        if (streetNumber.isEmpty()) {
            displayErrorLayout(txtStreetNumber, lblStreetNumberError, "Number is empty");
            return false;
        }

        if (streetNumber.matches(positiveIntegerPattern)) {
            clearLabelError(txtStreetNumber, lblStreetNumberError);
            return true;
        } else {
            displayErrorLayout(txtStreetNumber, lblStreetNumberError, "Invalid number");
            return false;
        }


    }

    private boolean validateStreet(TextField txtStreet) {
        if (txtStreet.getText().trim().isEmpty()) {
            displayErrorLayout(txtStreet, lblStreetError, "Street is empty");
            return false;
        }
        clearLabelError(txtStreet, lblStreetError);
        return true;
    }

    private boolean validateBirthdate(DatePicker dpBirthdate) {
        try {
            LocalDate originalBirthdate = dpBirthdate.getValue();

            if (dpBirthdate.getValue() == null) {
                displayErrorLayoutDatePicker(dpBirthdate, lblBirthdateError, "Birthdate is empty");
                return false;
            }

            LocalDate selectedDate = dpBirthdate.getValue();
            LocalDate currentDate = LocalDate.now();

            int year = selectedDate.getYear();
            int month = selectedDate.getMonthValue();
            int day = selectedDate.getDayOfMonth();

            if (year < 1 || month < 1 || month > 12 || day < 1 || day > selectedDate.lengthOfMonth()) {
                displayErrorLayoutDatePicker(dpBirthdate, lblBirthdateError, "Not a valid date");
                return false;
            }

            if (selectedDate.isAfter(currentDate)) {
                displayErrorLayoutDatePicker(dpBirthdate, lblBirthdateError, "Cant be a future date");
                dpBirthdate.setValue(originalBirthdate);
                return false;
            }
            clearLabelErrorDatePicker(dpBirthdate, lblBirthdateError);
            return true;

        } catch (Exception e) {
            displayErrorLayoutDatePicker(dpBirthdate, lblBirthdateError, "Invalid birthdate");
            return false;
        }

    }

    private boolean validateAdmissionDate(DatePicker dpAdmissionDate, DatePicker dpBirthdate) {
        try {
            if (dpAdmissionDate.getValue() == null) {
                displayErrorLayoutDatePicker(dpAdmissionDate, lblAdmissionDateError, "Admission date is empty");
                return false;
            }

            if (dpBirthdate.getValue() == null) {
                displayErrorLayoutDatePicker(dpBirthdate, lblBirthdateError, "Birthdate is empty");
                return false;
            }

            LocalDate selectedAdmissionDate = dpAdmissionDate.getValue();
            LocalDate birthdate = dpBirthdate.getValue();

            int year = selectedAdmissionDate.getYear();
            int month = selectedAdmissionDate.getMonthValue();
            int day = selectedAdmissionDate.getDayOfMonth();

            if (year < 1 || month < 1 || month > 12 || day < 1 || day > selectedAdmissionDate.lengthOfMonth()) {
                displayErrorLayoutDatePicker(dpAdmissionDate, lblAdmissionDateError, "Not a valid date");
                dpAdmissionDate.setPromptText(dpAdmissionDate.getPromptText());
                return false;
            }

            if (!isOlderThan18Years(birthdate, selectedAdmissionDate)) {
                displayErrorLayoutDatePicker(dpAdmissionDate, lblAdmissionDateError, "Collaborator must be at least 18 years old");
                return false;
            }

            clearLabelErrorDatePicker(dpAdmissionDate, lblAdmissionDateError);
            return true;

        } catch (Exception e) {
            displayErrorLayoutDatePicker(dpAdmissionDate, lblAdmissionDateError, "Invalid admission date");
            return false;
        }
    }

    private boolean isOlderThan18Years(LocalDate birthdate, LocalDate admissionDate) {
        return admissionDate.isAfter(birthdate.plusYears(18)) || admissionDate.isEqual(birthdate.plusYears(18));
    }


    private boolean validateDocumentNumber(TextField txtDocNumber, IdDocType idDocType) {
        String idDocNumber = txtDocNumber.getText().trim();
        String nineNumericDigits = "[0-9]{9}";
        String passportPattern = "\\p{Alpha}{2}\\d{6}";

        if (idDocNumber.isEmpty()) {
            displayErrorLayout(txtDocNumber, lblDocNumberError, "ID Number must not be empty");
            return false;
        }

        if (idDocType == IdDocType.PASSPORT) {
            if (idDocNumber.matches(passportPattern)) {
                clearLabelError(txtDocNumber, lblDocNumberError);
                return true;
            } else {
                displayErrorLayout(txtDocNumber, lblDocNumberError, "Passport in wrong format. Must be two letters + 6 numeric digits (Example: AB222222)");
                return false;
            }

        } else {
            if (idDocNumber.matches(nineNumericDigits)) {
                clearLabelError(txtDocNumber, lblDocNumberError);
                return true;
            } else {
                displayErrorLayout(txtDocNumber, lblDocNumberError, "NIF in wrong format. Must be 9 numeric digits");
                return false;
            }
        }

    }

    private boolean validateJob(Job selectedJob) {
        if (selectedJob == null) {
            displayErrorLayoutComboBox(cbJob, lblJobError, "Job must be selected");
            return false;
        }
        clearLabelErrorComboBox(cbJob, lblJobError);
        return true;
    }

    private boolean validateIdDocType(IdDocType idDocType) {
        if (idDocType == null) {
            displayErrorLayoutComboBox(cbDocType, lblDocTypeError, "Document type must be selected");
            return false;
        }
        clearLabelErrorComboBox(cbDocType, lblDocTypeError);
        return true;
    }

    private boolean validatePhoneNumber(TextField txtPhoneNumber) {
        String mobileNumber = txtPhoneNumber.getText().trim();
        if (mobileNumber.isEmpty()) {
            displayErrorLayout(txtPhoneNumber, lblPhoneError, "Mobile Number must not be empty");
            return false;
        }

        String onlyNumericDigits = "[0-9]+";
        if (!mobileNumber.matches(onlyNumericDigits)) {
            displayErrorLayout(txtPhoneNumber, lblPhoneError, "Mobile Number is not in a correct format");
            return false;
        }

        char[] mobileNumberChars = mobileNumber.toCharArray();

        if (mobileNumberChars.length != 9 || mobileNumberChars[0] != '9' || (mobileNumberChars[1] != MobileOperator.OPERATOR1.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR2.getOperatorCode() && mobileNumberChars[1] != MobileOperator.OPERATOR3.getOperatorCode())) {
            displayErrorLayout(txtPhoneNumber, lblPhoneError, "Mobile Number is not in a correct format");
            return false;
        } else {
            clearLabelError(txtPhoneNumber, lblPhoneError);
            return true;
        }

    }

    public boolean validateName(TextField txtName) {
        String name = txtName.getText().trim();

        if (name.isEmpty()) {
            displayErrorLayout(txtName, lblNameError, "Name must not be empty");
            return false;
        }

        //("\\s+") means one or more character space
        if (name.split("\\s+").length < 2) {
            displayErrorLayout(txtName, lblNameError, "Name must include at least one first name and one last name");
            return false;
        }

        //Name, according to Portuguese law must not contain more than 6 words
        if (name.split("\\s+").length > 6) {
            displayErrorLayout(txtName, lblNameError, "Can't contain more than 6 words, according to Portuguese Law");
            return false;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-\\p{L}]+");

        if (!namePattern.matcher(name).matches()) {
            displayErrorLayout(txtName, lblNameError, "Name must not contain special characters");
            return false;
        } else {
            clearLabelError(txtName, lblNameError);
            return true;
        }
    }

    public boolean validateEmail(TextField txtEmail) {
        String email = txtEmail.getText().trim();

        if (email.trim().isEmpty()) {
            displayErrorLayout(txtEmail, lblEmailError, "Email must not be empty");
            return false;
        }

        String[] parts = email.split("@");

        if (parts.length != 2) {
            displayErrorLayout(txtEmail, lblEmailError, "Email format must follow the pattern prefix@domain");
            return false;
        }

        String prefix = parts[0];
        String domain = parts[1];

        if (prefix.isEmpty() || domain.isEmpty()) {
            displayErrorLayout(txtEmail, lblEmailError, "Email format must follow the pattern prefix@domain");
            return false;
        }

        String prefixPattern = "^[a-zA-Z0-9_.-]+$";
        String domainPattern = "^(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (!prefix.matches(prefixPattern)) {
            displayErrorLayout(txtEmail, lblEmailError, "Email prefix not valid. Only letters, numbers and _ . - are accepted");
            return false;
        }

        if (!domain.matches(domainPattern)) {
            displayErrorLayout(txtEmail, lblEmailError, "Email domain not valid.");
            return false;
        }
        clearLabelError(txtEmail, lblEmailError);
        return true;
    }


    private void displayErrorLayout(TextField textFieldToShowError, Label labelToShowError, String errorMessage) {
        textFieldToShowError.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLabelError(TextField textFieldWithError, Label labelWithError) {
        textFieldWithError.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    private void displayErrorLayoutComboBox(ComboBox<?> comboBoxToShowError, Label labelToShowError, String
            errorMessage) {
        comboBoxToShowError.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLabelErrorComboBox(ComboBox<?> comboBoxWithError, Label labelWithError) {
        comboBoxWithError.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    private void displayErrorLayoutDatePicker(DatePicker datePickerToShowError, Label labelToShowError, String
            errorMessage) {
        datePickerToShowError.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLabelErrorDatePicker(DatePicker datePickerWithError, Label labelWithError) {
        datePickerWithError.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    @FXML
    private void btnClearAction() {
        txtName.clear();
        dpBirthdate.setValue(null);
        dpAdmissionDate.setValue(null);
        txtStreet.clear();
        txtStreetNumber.clear();
        txtPostalCode.clear();
        txtCity.clear();
        txtDistrict.clear();
        txtEmail.clear();
        txtPhoneNumber.clear();
        cbDocType.getSelectionModel().clearSelection();
        cbJob.getSelectionModel().clearSelection();
        txtDocNumber.clear();
        clearErrorStylesAndLabels();

    }

    private void clearErrorStylesAndLabels() {
        txtName.setStyle("");
        txtStreet.setStyle("");
        txtStreetNumber.setStyle("");
        txtPostalCode.setStyle("");
        txtCity.setStyle("");
        txtDistrict.setStyle("");
        txtEmail.setStyle("");
        txtPhoneNumber.setStyle("");
        txtDocNumber.setStyle("");
        dpBirthdate.setStyle("");
        dpAdmissionDate.setStyle("");
        cbDocType.setStyle("");
        cbJob.setStyle("");
        lblNameError.setVisible(false);
        lblStreetError.setVisible(false);
        lblStreetNumberError.setVisible(false);
        lblPostalCodeError.setVisible(false);
        lblCityError.setVisible(false);
        lblDistrictError.setVisible(false);
        lblEmailError.setVisible(false);
        lblPhoneError.setVisible(false);
        lblDocNumberError.setVisible(false);
        lblBirthdateError.setVisible(false);
        lblAdmissionDateError.setVisible(false);
        lblDocTypeError.setVisible(false);
        lblJobError.setVisible(false);
    }

}

