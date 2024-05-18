package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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
            Optional<Skill> skill = getRegisterSkillController().registerSkill(
                    txtSkillName.getText().trim()
            );


            if (skill.isPresent()) {
                txtSkillName.clear();
                txtSkillName.setStyle(null);
                lblSkillNameError.setVisible(false);
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Skill", "Register Skill", "Skill successfully registered!").show();
            } else {
                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Register Skill", "Register Skill", "This Skill is already registered!").show();
            }


        } catch (IllegalArgumentException e) {
            txtSkillName.setStyle("-fx-border-color: red");
            lblSkillNameError.setText(e.getMessage());
            lblSkillNameError.setVisible(true);

        } catch (NullPointerException npe) {
            AlertUI.createAlert(Alert.AlertType.ERROR, "Register Skill", "Error occured", npe.getMessage()).show();
        }
    }

    @FXML
    public void btnClearAction() {
        txtSkillName.clear();
        lblSkillNameError.setVisible(false);
        txtSkillName.setStyle(null);
    }
}
