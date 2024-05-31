package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterJobUI implements Initializable {

    private final RegisterJobController controller;

    @FXML
    private TextField txtJobName;
    @FXML
    private Label lblJobNameError;

    public RegisterJobUI() {
        controller = new RegisterJobController();
    }

    public RegisterJobController getRegisterJobController() {
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    @FXML
    public void btnSubmitAction() {
        try {

            if (validateJob()) {

                StringBuilder sb = getConfirmationText(txtJobName.getText().trim());

                Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Register Job", "Confirm the operation", sb.toString());
                if (alertConfirmation.showAndWait().get() == ButtonType.OK) {

                    Optional<Job> job = getRegisterJobController().registerJob(txtJobName.getText().trim());

                    if (job.isPresent()) {
                        clearLayoutErrors(txtJobName, lblJobNameError);
                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Job", "Register Job", "Job successfully registered!").show();
                        clearErrors();
                    } else {
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Register Job", "Register Job", "This Job is already registered!").show();
                    }
                }

            }
        } catch (IllegalArgumentException e) {
            txtJobName.setStyle("-fx-border-color: red");
            lblJobNameError.setText(e.getMessage());
            lblJobNameError.setVisible(true);

        } catch (NullPointerException npe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Job", "Error occured", npe.getMessage()).show();
        }
    }

    private boolean validateJob() {
        String jobString = txtJobName.getText().trim();
        if (jobString.isEmpty()) {
            displayErrorLayout(txtJobName, lblJobNameError, "Job can't be empty");
            return false;
        }
        if (!jobString.matches("[a-zA-Z\\s-\\p{L}]+")) {
            displayErrorLayout(txtJobName, lblJobNameError, "Job can't contain special characters");
            return false;
        }
        clearLayoutErrors(txtJobName, lblJobNameError);
        return true;
    }


    @FXML
    public void btnClearAction() {
        clearErrors();
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

    private void clearErrors() {
        txtJobName.clear();
        lblJobNameError.setVisible(false);
        txtJobName.setStyle(null);
    }


    private StringBuilder getConfirmationText(String jobName) {

        StringBuilder sb = new StringBuilder();
        sb.append("You are about to register the following job:").append("\n").append(jobName);

        return sb;
    }
}
