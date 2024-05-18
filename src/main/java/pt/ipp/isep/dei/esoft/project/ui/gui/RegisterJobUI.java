package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.controller.RegisterJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
            Optional<Job> job = getRegisterJobController().registerJob(
                    txtJobName.getText().trim()
            );


            if (job.isPresent()) {
                txtJobName.clear();
                txtJobName.setStyle(null);
                lblJobNameError.setVisible(false);
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Job", "Register Job", "Job successfully registered!").show();
            } else {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Job", "Register Job", "This Job is already registered!").show();
            }


        } catch (IllegalArgumentException e) {
            txtJobName.setStyle("-fx-border-color: red");
            lblJobNameError.setText(e.getMessage());
            lblJobNameError.setVisible(true);

        } catch (NullPointerException npe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Job", "Error occured", npe.getMessage()).show();
        }
    }

    @FXML
    public void btnClearAction() {
        txtJobName.clear();
        lblJobNameError.setVisible(false);
        txtJobName.setStyle(null);
    }
}
