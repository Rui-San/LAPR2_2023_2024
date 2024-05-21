package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.awt.TextField;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamProposalUIteste implements Initializable {
    private final TeamProposalController controller;

    @FXML
    private TextField minTeamSizeField;
    @FXML
    private TextField maxTeamSizeField;
    @FXML
    private ListView<Skill> skillsListView;
    @FXML
    private TextField quantityNeededField;
    @FXML
    private ListView<String> selectedSkillsListView;
    @FXML
    private ListView<Team> generatedTeamsListView;
    @FXML
    private Label messageLabel;

    public TeamProposalUIteste() {
        controller = new TeamProposalController();
    }

    @FXML
    public void initialize() {
        loadSkills();
    }

    private void loadSkills() {
        List<Skill> skills = controller.getSkillList();
        skillsListView.getItems().addAll(skills);
    }

    @FXML
    private void handleAddSkill() {
        Skill selectedSkill = skillsListView.getSelectionModel().getSelectedItem();
        if (selectedSkill != null) {
            String quantityText = quantityNeededField.getText();
            try {
                int quantity = Integer.parseInt(quantityText);
                selectedSkillsListView.getItems().add(selectedSkill.getSkillName() + " - " + quantity);
            } catch (NumberFormatException e) {
                messageLabel.setText("Invalid quantity.");
            }
        } else {
            messageLabel.setText("Please select a skill.");
        }
    }
/*
    @FXML
    private void handleGenerateTeams() {
        try {
            int minTeamSize = Integer.parseInt(minTeamSizeField.getText());
            int maxTeamSize = Integer.parseInt(maxTeamSizeField.getText());

            if (minTeamSize < 2) {
                messageLabel.setText("Minimum team size must be at least 2.");
                return;
            }

            if (maxTeamSize < minTeamSize) {
                messageLabel.setText("Maximum team size must be greater than or equal to minimum team size.");
                return;
            }

            List<Skill> skillsNeeded = controller.getSkillList();
            List<Integer> quantityNeeded = // Extract quantities from selectedSkillsListView

                    List<Team> teams = controller.generateAllTeamProposal(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded);
            generatedTeamsListView.getItems().clear();
            generatedTeamsListView.getItems().addAll(teams);
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid team size.");
        }
    }

*/

    @FXML
    private void handleApproveTeam() {
        Team selectedTeam = generatedTeamsListView.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            boolean approved = showConfirmationDialog("Approve Team", "Do you approve this team?");
            if (approved) {
                controller.saveTeamProposal(selectedTeam);
                messageLabel.setText("Team approved and saved.");
            } else {
                messageLabel.setText("Team approval canceled.");
            }
        } else {
            messageLabel.setText("Please select a team to approve.");
        }
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnGenerateAllTeamProposals(ActionEvent actionEvent) {
    }

    public void btnAddSelectedSkillPlusQuantity(ActionEvent actionEvent) {

    }
}

