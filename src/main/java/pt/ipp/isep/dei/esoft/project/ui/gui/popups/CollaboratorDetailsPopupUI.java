package pt.ipp.isep.dei.esoft.project.ui.gui.popups;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.tools.TaskDurationFormatter;
import pt.ipp.isep.dei.esoft.project.tools.TaskStartDateFormatter;

public class CollaboratorDetailsPopupUI {

    @FXML
    public TextField name, email, birthdate, admissionDate, mobileNumber, job, idtype, idnumber, address;
    @FXML
    public TextArea assignedSkills, teamMembers;

    public void initData(Collaborator selectedCollaborator) {

        name.setText(selectedCollaborator.getName());
        email.setText(selectedCollaborator.getEmail().getEmail());
        birthdate.setText(selectedCollaborator.getBirthdate().toString());
        admissionDate.setText(selectedCollaborator.getAdmissionDate().toString());
        mobileNumber.setText(selectedCollaborator.getMobileNumber());
        job.setText(selectedCollaborator.getJob().getJobName());
        idtype.setText(selectedCollaborator.getIdDocType().name());
        idnumber.setText(selectedCollaborator.getIdDocNumber());
        address.setText(selectedCollaborator.getAddress().toString());
        if(!selectedCollaborator.getSkillList().isEmpty()){
            for(Skill skill : selectedCollaborator.getSkillList()){
                assignedSkills.setText(skill.getSkillName() + "\n" + assignedSkills.getText());
            }
        }else {
            assignedSkills.setText("No skills assigned");
        }
        Team team = Repositories.getInstance().getTeamRepository().getTeamByTeamMemberEmails(selectedCollaborator.getEmail().toString());
        if (team != null) {
            for (Collaborator collaborator : team.getMembers()) {
                if (!collaborator.getEmail().getEmail().equals(selectedCollaborator.getEmail().getEmail())) {
                    teamMembers.setText(collaborator.getName() + " - " + collaborator.getEmail().getEmail() + "\n" + teamMembers.getText());
                }
            }
        } else {
            teamMembers.setText("No team assigned");
        }


    }

    @FXML
    private void btnExit() {
        closeUI();
    }

    private void closeUI() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
