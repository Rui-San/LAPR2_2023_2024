package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import org.w3c.dom.Text;
import pt.ipp.isep.dei.esoft.project.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.MobileOperator;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.net.URL;
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
        resetErrors();

        String cfBirthdate = "";
        String cfAdmissionDate = "";
        try {
            try {
                cfBirthdate = convertFormat(dpBirthdate.getValue().toString());
            } catch (NullPointerException nullPointerException) {
                dpBirthdate.setStyle("-fx-border-color: red");
                lblBirthdateError.setVisible(true);
                lblBirthdateError.setText("Field cannot be empty");
            }

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

            validateAllErrors(txtName, txtEmail, txtPhoneNumber);

            Job selectedJob = getSelectedJob();
            IdDocType selectedIdDocType = getSelectedIdDocType();

            Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(


                    name,
                    cfBirthdate,
                    cfAdmissionDate,
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
    }

    @FXML
    private void resetErrors() {
        dpBirthdate.setStyle("");
        lblBirthdateError.setVisible(false);
        dpAdmissionDate.setStyle("");
        lblAdmissionDateError.setVisible(false);
    }

    public boolean validateAllErrors(TextField txtName, TextField txtEmail, TextField txtPhoneNumber) {
        boolean nameValid = validateName(txtName);
        boolean emailValid = validateEmail(txtEmail);
        boolean mobileNumberValid = validatePhoneNumber(txtPhoneNumber);
        boolean jobValid = validateJob(getSelectedJob());

        return nameValid && emailValid && mobileNumberValid && jobValid;
    }

    private boolean validateJob(Job selectedJob) {
        if (selectedJob == null) {
            displayErrorLayoutComboBox(cbJob, lblJobError, "Job must be selected");
            return false;
        }
        clearLabelErrorComboBox(cbJob, lblJobError);
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
            displayErrorLayout(txtName, lblNameError, "Name must not contain more than 6 words, according to Portuguese Law");
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

    private void displayErrorLayoutComboBox(ComboBox<?> comboBoxToShowError, Label labelToShowError, String errorMessage) {
        comboBoxToShowError.setStyle("-fx-border-color: red");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLabelErrorComboBox(ComboBox<?> comboBoxWithError, Label labelWithError) {
        comboBoxWithError.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

}

