package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

public class CollaboratorDTO {
    public String collaboratorName;
    public String email;
    public List<String> skillNames;

    public CollaboratorDTO(Collaborator collaborator) {
        this.collaboratorName = collaborator.getName().trim();
        this.email = collaborator.getEmail().getEmail().trim();


        for(Skill skill : collaborator.getSkillList()){
            skillNames.add(skill.getSkillName());
        }
    }
}
