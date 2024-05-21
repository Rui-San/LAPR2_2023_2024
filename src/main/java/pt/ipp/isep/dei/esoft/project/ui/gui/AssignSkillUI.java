package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.esoft.project.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssignSkillUI implements Initializable {
    private final AssignSkillController controller;
    private String selectedCollaborator;
    private List<Collaborator> collaboratorList;
    private List<Skill> skillList;
    @FXML
    private ComboBox<String> cbCollaborator;

    //@FXML
    //private ListView<String> lvSkills;

    @FXML
    private ListView<String> lvCollaboratorSkills;

    @FXML
    private VBox vboxSkills;

    @FXML
    private Label lblSelectedSkillsError;


    public AssignSkillUI() {
        this.controller = new AssignSkillController();
        collaboratorList = controller.getCollaboratorList();
        skillList = controller.getSkillList();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCollaboratorComboBox();
        fillSkillVBox(skillList);
        //lvSkills.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //fillSkillList(skillList);
    }

    private void fillCollaboratorComboBox() {
        for (Collaborator collaborator : collaboratorList) {

            String displayText = collaborator.getName() + " - " + collaborator.getEmail();
            cbCollaborator.getItems().add(displayText);
        }
    }

    private void fillSkillVBox(List<Skill> skillList) {
        for (Skill skill : skillList) {
            CheckBox checkBox = new CheckBox(skill.getSkillName());
            vboxSkills.getChildren().add(checkBox);
        }
    }

    private Collaborator getSelectedCollaborator() {
        if (cbCollaborator.getValue() == null) {
            return null;
        }

        String[] comboBoxTextParts = cbCollaborator.getValue().split(": ");
        String email = comboBoxTextParts[1];


        for (Collaborator collaborator : collaboratorList) {
            if (collaborator.getEmail().getEmail().equals(email)) {
                return collaborator;
            }
        }
        return null;
    }

    /*
    private List<Skill> getSelectedSkills() {
        List<Skill> selectedSkills = new ArrayList<>();
        boolean hasDuplicates = false;

        for (int i = 0; i < vboxSkills.getChildren().size(); i++) {
            if (vboxSkills.getChildren().get(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) vboxSkills.getChildren().get(i);
                if (checkBox.isSelected()) {
                    for (Skill skill : skillList) {
                        if (checkBox.getText().equalsIgnoreCase(skill.getSkillName()) && getSelectedCollaborator().getSkillList().contains(skill)) {
                            hasDuplicates = true;
                            checkBox.setStyle("-fx-border-color: transparent transparent red transparent; -fx-border-width: 0 0 2px 0;");
                        } else if (checkBox.getText().equalsIgnoreCase(skill.getSkillName()) && !getSelectedCollaborator().getSkillList().contains(skill)) {
                            selectedSkills.add(skill);
                            checkBox.setStyle("-fx-border-color: transparent transparent green transparent; -fx-border-width: 0 0 2px 0;");
                        }
                    }
                } else {
                    checkBox.setStyle("");
                }
            }
        }

        if (!selectedSkills.isEmpty()) {
            lblSelectedSkillsError.setText("");
            if (hasDuplicates) {
                displayErrorLabel(lblSelectedSkillsError,"The skills marked in red are already assigned to collaborator " + cbCollaborator.getValue());
                //lblSelectedSkillsError.setText("The skills marked in red are already assigned to collaborator" + cbCollaborator.getValue());
            }
        } else {
            displayErrorLabel(lblSelectedSkillsError,"Select at least one skill");
        }

        if (!selectedSkills.isEmpty() && hasDuplicates) {
            lblSelectedSkillsError.setText("The skills marked in red are already assigned to collaborator" + cbCollaborator.getValue());
        } else {
            lblSelectedSkillsError.setText("");
        }

        return selectedSkills;

    }

     */
    private List<Skill> getSelectedSkills() {
        List<Skill> selectedSkills = new ArrayList<>();
        boolean hasDuplicates = false;
        Collaborator selectedCollaborator = getSelectedCollaborator();

        if (selectedCollaborator == null) {
            displayErrorLabel(lblSelectedSkillsError, "A collaborator must be selected");
            return selectedSkills;
        }

        for (int i = 0; i < vboxSkills.getChildren().size(); i++) {
            if (vboxSkills.getChildren().get(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) vboxSkills.getChildren().get(i);
                if (checkBox.isSelected()) {
                    Skill matchingSkill = findSkillByName(checkBox.getText());
                    if (matchingSkill != null) {
                        if (selectedCollaborator.getSkillList().contains(matchingSkill)) {
                            hasDuplicates = true;
                            checkBox.setStyle("-fx-border-color: transparent transparent red transparent; -fx-border-width: 0 0 2px 0;");
                        } else {
                            selectedSkills.add(matchingSkill);
                            checkBox.setStyle("-fx-border-color: transparent transparent green transparent; -fx-border-width: 0 0 2px 0;");
                        }
                    }
                } else {
                    checkBox.setStyle("");
                }
            }
        }

        if (selectedSkills.isEmpty() && !hasDuplicates) {
            displayErrorLabel(lblSelectedSkillsError, "Select at least one skill");
        } else {
            clearLabelError(lblSelectedSkillsError);
            if (hasDuplicates) {
                displayErrorLabel(lblSelectedSkillsError, "The skills marked in red are already assigned to the collaborator " + cbCollaborator.getValue());
            }
        }

        return selectedSkills;
    }

    private Skill findSkillByName(String skillName) {
        for (Skill skill : skillList) {
            if (skill.getSkillName().equalsIgnoreCase(skillName)) {
                return skill;
            }
        }
        return null;
    }


    private boolean validateSelection() {
        List<Skill> selectedSkills = getSelectedSkills();
        if (selectedSkills.isEmpty()) {
            return false;
        }else{
            clearLabelError(lblSelectedSkillsError);
            return true;

        }
    }

    private boolean validateSelectedCollaborator() {
        if (cbCollaborator.getValue() == null) {
            displayErrorLabel(lblSelectedSkillsError, "A collaborator must be selected");
            return false;
        }
        clearLabelError(lblSelectedSkillsError);
        return true;
    }


    @FXML
    private void btnAssignSelectedSkills() {

        try {
            Collaborator collaborator = getSelectedCollaborator();

            List<Skill> selectedSkills = getSelectedSkills();

            if (validateSelectedCollaborator() && validateSelection()) {
                StringBuilder sb = new StringBuilder();
                sb.append("You are about to assign the following skills to collaborator ").append(collaborator.getName()).append(":");
                for (Skill skill : selectedSkills) {
                    sb.append("\n").append(skill.getSkillName());
                }

                AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Assign Skills", "Confirm the operation", sb.toString()).show();

                controller.assignSkillsToCollaborator(collaborator,selectedSkills);

                AlertUI.createAlert(Alert.AlertType.INFORMATION,"Assign Skills","Confirmation of operation","All skills were sucessfully assigned to collaborator");
            }
        } catch (NullPointerException ne) {
            displayErrorLabel(lblSelectedSkillsError, "A collaborator must be selected");
        }

    }

    /*  private boolean validChoices(List<Skill> selectedSkills, List<Skill> collaboratorSkills) {
          List<String> repetitiveChoices = new ArrayList<>();
          for (Skill selectedSkill : selectedSkills) {
              if (collaboratorSkills.contains(selectedSkill)) {
                  repetitiveChoices.add(selectedSkill.getSkillName());
              }
          }
          if (!repetitiveChoices.isEmpty()) {
              StringBuilder errorMessage = new StringBuilder();
              for (String repetitiveSkill : repetitiveChoices) {
                  errorMessage.append(repetitiveSkill).append(";");
              }
              displayErrorLayoutVBox(vboxSkills, lblSelectedSkillsError, "Collaborator already have the selected skills: " + errorMessage);
              return false;
          } else {
              clearLabelErrorVBox(vboxSkills, lblSelectedSkillsError);
              return true;
          }
      }

  */
    private void displayErrorLabel(Label labelToShowError, String
            errorMessage) {
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLabelError(Label labelWithError) {
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }

    private void displayErrorCheckBox(CheckBox checkBoxWithError, Label labelToShowError, String
            errorMessage) {
        checkBoxWithError.setStyle("-fx-border-color: transparent transparent green transparent; -fx-border-width: 0 0 2px 0;");
        labelToShowError.setVisible(true);
        labelToShowError.setText(errorMessage);
    }

    private void clearLabelErrorVBox(VBox vBoxWithError, Label labelWithError) {
        vBoxWithError.setStyle("");
        labelWithError.setVisible(false);
        labelWithError.setText("");
    }


    @FXML
    private void updateLVskills() {

        Collaborator collaborator = getSelectedCollaborator();
        List<Skill> collaboratorSkills = collaborator.getSkillList();

        if (!collaboratorSkills.isEmpty()) {
            lvCollaboratorSkills.getItems().clear();
            for (Skill skill : collaboratorSkills) {
                lvCollaboratorSkills.getItems().add(skill.getSkillName());
            }
        } else {
            lvCollaboratorSkills.getItems().clear();
            lvCollaboratorSkills.getItems().add("No skills assigned yet");
        }
    }


}
