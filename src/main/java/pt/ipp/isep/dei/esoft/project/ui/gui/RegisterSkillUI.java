package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.awt.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegisterSkillUI implements Initializable {

    private final RegisterSkillController controller;

    @FXML
    private TextField txtSkillName;
    @FXML
    private Label lblSkillNameError;

    public RegisterSkillUI() {
        controller = new RegisterSkillController();
    }

    public RegisterSkillController getRegisterSkillController() {
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }


    @FXML
    public void btnSubmitAction() {
        try {
            if (validateSkill()) {

                StringBuilder sb = getConfirmationText(txtSkillName.getText().trim());

                Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Register Skill", "Confirm the operation", sb.toString());
                if (alertConfirmation.showAndWait().get() == ButtonType.OK) {

                    Optional<Skill> skill = getRegisterSkillController().registerSkill(txtSkillName.getText().trim());

                    if (skill.isPresent()) {
                        clearLayoutErrors(txtSkillName, lblSkillNameError);
                        AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Skill", "Register Skill", "Skill successfully registered!").show();
                        clearErrors();
                    } else {
                        AlertUI.createAlert(Alert.AlertType.ERROR, "Register Skill", "Register Skill", "This Skill is already registered!").show();
                    }

                }
            }

        } catch (IllegalArgumentException e) {
            txtSkillName.setStyle("-fx-border-color: red");
            lblSkillNameError.setText(e.getMessage());
            lblSkillNameError.setVisible(true);

        } catch (NullPointerException npe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Skill", "Error occured", npe.getMessage()).show();
        }
    }

    private boolean validateSkill() {
        String skillString = txtSkillName.getText().trim();
        if (skillString.isEmpty()) {
            displayErrorLayout(txtSkillName, lblSkillNameError, "Skill can't be empty");
            return false;
        }
        if (!skillString.matches("[a-zA-Z\\s-\\p{L}]+")) {
            displayErrorLayout(txtSkillName, lblSkillNameError, "Skill can't contain special characters");
            return false;
        }
        clearLayoutErrors(txtSkillName, lblSkillNameError);
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

    private StringBuilder getConfirmationText(String skillName) {

        StringBuilder sb = new StringBuilder();
        sb.append("You are about to register the following skill:").append("\n").append(skillName);

        return sb;
    }

    @FXML
    public void btnClearAction() {
        clearErrors();
    }

    private void clearErrors() {
        txtSkillName.clear();
        lblSkillNameError.setVisible(false);
        txtSkillName.setStyle(null);
    }
}
