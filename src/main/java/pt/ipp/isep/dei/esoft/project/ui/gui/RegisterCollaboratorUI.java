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
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;
import pt.ipp.isep.dei.esoft.project.tools.MobileOperator;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.ipp.isep.dei.esoft.project.ui.gui.authorization.AuthenticationUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Register Collaborator UI class
 */
public class RegisterCollaboratorUI implements Initializable {

    private final RegisterCollaboratorController controller;

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

            int selectedIndex = cbDocType.getSelectionModel().getSelectedIndex();
            IdDocType idDocType = null;
            switch (selectedIndex) {
                case 0:
                    idDocType = IdDocType.CC;
                    break;
                case 1:
                    idDocType = IdDocType.BI;
                    break;
                case 2:
                    idDocType = IdDocType.PASSPORT;

            }

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
                    idDocType,
                    txtDocNumber.getText().trim(),
                    null
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
        List<Job> jobs = getRegisterCollaboratorController().getJobList();
        for (Job job : jobs) {
            cbJob.getItems().add(job.getJobName());
        }
    }

}

