package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import pt.ipp.isep.dei.esoft.project.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

        String[] comboBoxTextParts = cbCollaborator.getValue().split(": ");
        String email = comboBoxTextParts[1];


        for (Collaborator collaborator : collaboratorList) {
            if (collaborator.getEmail().getEmail().equals(email)) {
                return collaborator;
            }
        }
        return null;
    }

    private List<Skill> getSelectedSkills() {
        List<Skill> selectedSkills = new ArrayList<>();
        for (int i = 0; i < vboxSkills.getChildren().size(); i++) {
            if (vboxSkills.getChildren().get(i) instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) vboxSkills.getChildren().get(i);
                if (checkBox.isSelected()) {
                    for (Skill skill : skillList) {
                        if (checkBox.getText().equalsIgnoreCase(skill.getSkillName())) {
                            selectedSkills.add(skill);
                        }
                    }
                }
            }
        }
        return selectedSkills;
    }

    @FXML
    private void btnAssignSelectedSkills(){
        List<Skill> selectedSkills = getSelectedSkills();
        for(Skill skillss : selectedSkills){
            System.out.println(skillss.getSkillName());
        }
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
