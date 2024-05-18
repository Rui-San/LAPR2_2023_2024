package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator.IdDocType;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.net.URL;
import java.util.*;
import java.util.List;


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
        try {
            String cfBirthdate = convertFormat(dpBirthdate.getValue().toString());
            String cfAdmissionDate = convertFormat(dpAdmissionDate.getValue().toString());
            Job selectedJob = getSelectedJob();
            IdDocType selectedIdDocType = getSelectedIdDocType();

            Optional<Collaborator> collaborator = getRegisterCollaboratorController().createCollaborator(
                    txtName.getText().trim(),
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
                            : "There was an error adding the collaborator.").show();

        } catch (NullPointerException npe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, MainApp.APP_TITLE, "Erro nos dados.",
                    npe.getMessage()).show();
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

}

