package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.controller.TeamProposalController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamProposalUI implements Initializable {
    @FXML
    private TextField txtMinTeamSize;
    @FXML
    private TextField txtMaxTeamSize;
    @FXML
    private ListView<Skill> skillsListView;
    @FXML
    private TextField txtQuantityOfSelectedSkill;
    @FXML
    private ListView<String> lvSkillSetList;
    @FXML
    private Label lblSkillSetQuantityError;
    @FXML
    private Label lblGenerateTeamError;
    @FXML
    private Label lblMaxError;
    @FXML
    private Label lblMinError;
    @FXML
    private Label lblResult;


    @FXML
    private ListView<String> lvTeamGenerated;


    private int currentTeamIndex = 0;
    private final TeamProposalController controller;
    private List<Skill> skillsNeeded;
    private List<Integer> quantityNeeded;
    private List<Team> generatedTeams;

    public TeamProposalUI() {
        controller = new TeamProposalController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        skillsNeeded = new ArrayList<>();
        quantityNeeded = new ArrayList<>();
        skillsListView.setItems(FXCollections.observableArrayList(controller.getSkillList()));


        skillsListView.setCellFactory(lv -> new ListCell<Skill>() {
            @Override
            protected void updateItem(Skill skill, boolean empty) {
                super.updateItem(skill, empty);
                if (empty || skill == null) {
                    setText(null);
                } else {
                    setText(skill.getSkillName());
                }
            }
        });
    }

    @FXML
    private void btnAddSelectedSkillPlusQuantity() {
        Skill selectedSkill = skillsListView.getSelectionModel().getSelectedItem();
        if (selectedSkill != null) {
            if (skillsNeeded.contains(selectedSkill)) {
                lblSkillSetQuantityError.setText("Skill already added.");
                lblSkillSetQuantityError.setVisible(true);
            } else {


                try {
                    int quantity = Integer.parseInt(txtQuantityOfSelectedSkill.getText());
                    if (quantity > 0) {
                        skillsNeeded.add(selectedSkill);
                        quantityNeeded.add(quantity);
                        lvSkillSetList.getItems().add(selectedSkill.getSkillName() + " - " + quantity);
                        txtQuantityOfSelectedSkill.clear();
                        lblSkillSetQuantityError.setVisible(false);
                    } else {
                        lblSkillSetQuantityError.setText("Quantity must be greater than 0.");
                        lblSkillSetQuantityError.setVisible(true);
                    }
                } catch (NumberFormatException e) {
                    lblSkillSetQuantityError.setText("Invalid quantity format.");
                    lblSkillSetQuantityError.setVisible(true);
                }
            }
        } else {
            lblSkillSetQuantityError.setText("No skill selected.");
            lblSkillSetQuantityError.setVisible(true);
        }
    }

    @FXML
    private void btnGenerateAllTeamProposals() {
        try {
            int minTeamSize = Integer.parseInt(txtMinTeamSize.getText());
            int maxTeamSize = Integer.parseInt(txtMaxTeamSize.getText());

            if (minTeamSize >= 1 && maxTeamSize >= minTeamSize) {
                generatedTeams = controller.generateAllTeamProposal(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded);
                if (generatedTeams.isEmpty()) {
                    lblGenerateTeamError.setText("No teams could be generated with the given inputs.");
                    lblGenerateTeamError.setVisible(true);
                    lblResult.setVisible(false);
                    lvTeamGenerated.getItems().clear();
                } else {
                    currentTeamIndex = 0;
                    showCurrentTeam();
                    lblGenerateTeamError.setVisible(false);
                    lblResult.setText("Total of teams generated: " + generatedTeams.size());
                    lblResult.setVisible(true);
                }
            } else {
                lblGenerateTeamError.setText("Invalid team sizes.");
                lblGenerateTeamError.setVisible(true);
                lblResult.setVisible(false);
            }
        } catch (NumberFormatException e) {
            lblGenerateTeamError.setText("Invalid team size format.");
            lblGenerateTeamError.setVisible(true);
            lblResult.setVisible(false);
        }
    }

    @FXML
    private void btnDisplayOtherTeam() {
        currentTeamIndex++;
        if (currentTeamIndex >= generatedTeams.size()) {
            currentTeamIndex = 0;
        }
        showCurrentTeam();
    }

    private void showCurrentTeam() {
        if (!generatedTeams.isEmpty()) {
            lvTeamGenerated.getItems().clear();
            Team currentTeam = generatedTeams.get(currentTeamIndex);

            for (Collaborator collaborator : currentTeam.getMembers()) {
                lvTeamGenerated.getItems().add("Name: " + collaborator.getName());
                lvTeamGenerated.getItems().add("Email: " + collaborator.getEmail().getEmail());
                lvTeamGenerated.getItems().add("Skills: ");
                for (Skill skill : collaborator.getSkillList()) {
                    lvTeamGenerated.getItems().add(skill.getSkillName());
                }
                lvTeamGenerated.getItems().add("");
            }
        }
    }

    @FXML
    private void btnAcceptTeam() {
        if (!lvTeamGenerated.getItems().isEmpty()) {
            String teamString = lvTeamGenerated.getItems().get(0);
            Team selectedTeam = generatedTeams.get(currentTeamIndex);

            Alert alertConfirmation = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Team proposal", "You are about to save the select team proposal: ", selectedTeam.toString());
            if (alertConfirmation.showAndWait().get() == ButtonType.OK) {
                controller.saveTeamProposal(selectedTeam);

                AlertUI.createAlert(Alert.AlertType.INFORMATION, "Team proposal", "Confirmation of operation", "Team proposal successfully saved");
                clearFields();
            }

        } else {
            lblGenerateTeamError.setText("No team displayed to accept.");
            lblGenerateTeamError.setVisible(true);
        }
    }

    @FXML
    private void btnRemoveSkillSet() {
        int selectedIndex = lvSkillSetList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            lvSkillSetList.getItems().remove(selectedIndex);
            skillsNeeded.remove(selectedIndex);
            quantityNeeded.remove(selectedIndex);
        }
    }

    private void clearFields() {
        txtMinTeamSize.clear();
        txtMaxTeamSize.clear();
        txtQuantityOfSelectedSkill.clear();
        lvSkillSetList.getItems().clear();
        lvTeamGenerated.getItems().clear();
        lblGenerateTeamError.setVisible(false);
        lblSkillSetQuantityError.setVisible(false);
        lblMaxError.setVisible(false);
        lblMinError.setVisible(false);
        lblResult.setVisible(false);
        skillsNeeded.clear();
        quantityNeeded.clear();

    }

    @FXML
    public void btnClear() {
        clearFields();
    }
}
