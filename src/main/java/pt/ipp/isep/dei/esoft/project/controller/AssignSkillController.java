package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.util.List;

public class AssignSkillController {
    private final CollaboratorRepository collaboratorRepository;

    public AssignSkillController() {

        this.collaboratorRepository = new CollaboratorRepository();
    }
    public boolean assignSkillsToCollaborator(Collaborator collaborator, List<Skill> skills) {
        return collaboratorRepository.updateCollaboratorSkills(collaborator, skills);
    }

    public List<Collaborator> getCollaborators() {
        return collaboratorRepository.getCollaboratorList();
    }
}
