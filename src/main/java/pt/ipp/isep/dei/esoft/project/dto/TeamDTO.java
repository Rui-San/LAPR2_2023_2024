package pt.ipp.isep.dei.esoft.project.dto;


import java.util.ArrayList;
import java.util.List;

public class TeamDTO {
    public List<CollaboratorDTO> collaborators;


    public TeamDTO(List<CollaboratorDTO> teamMembers) {
        this.collaborators = teamMembers;
    }


    public String namesToString() {
        StringBuilder sb = new StringBuilder();
        for (CollaboratorDTO collaboratorDTO : collaborators) {
            sb.append(collaboratorDTO.collaboratorName).append(", ");
        }

        return sb.substring(0, sb.length() - 2);
    }

    public String skillsToString() {
        List<String> nonRepetitiveSkills = new ArrayList<>();

        for (CollaboratorDTO collaboratorDTO : collaborators) {
            for (String stringName : collaboratorDTO.skillNames) {
                if (!nonRepetitiveSkills.contains(stringName.trim())) {
                    nonRepetitiveSkills.add(stringName);
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        for (String skillString : nonRepetitiveSkills) {
            sb.append(skillString).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

}
