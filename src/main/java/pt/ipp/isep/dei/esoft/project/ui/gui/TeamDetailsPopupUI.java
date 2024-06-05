package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;

import java.util.List;

public class TeamDetailsPopupUI {

    @FXML
    private Label lblSkills, lblCollaborators;


    public void initData(TeamDTO teamDTO) {
        StringBuilder collaboratorNames = new StringBuilder();
        StringBuilder collaboratorSkills = new StringBuilder();

        List<CollaboratorDTO> collaboratorsOfTeam = teamDTO.collaborators;


        for (CollaboratorDTO collaboratorDTO : collaboratorsOfTeam) {
            collaboratorNames.append(collaboratorDTO.collaboratorName).append(" - ").append(collaboratorDTO.email).append("\n");

            for (String skillOfCollaborator : collaboratorDTO.skillNames) {
                collaboratorSkills.append(skillOfCollaborator).append(", ");
            }
            collaboratorSkills.append("\n");
        }

        lblCollaborators.setText(collaboratorNames.toString());
        lblSkills.setText(collaboratorSkills.toString());


    }

    @FXML
    private void btnExit() {
        closeUI();
    }

    private void closeUI() {

        Stage stage = (Stage) lblCollaborators.getScene().getWindow();
        stage.close();
    }
}
