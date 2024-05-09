package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;

public class AssignSkillController {
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    public AssignSkillController() {
        getCollaboratorRepository();
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }



    public void assignSkillsToCollaborator(Collaborator collaborator, List<Skill> skills) {
        getCollaboratorRepository().assignSkillsToCollaborator(collaborator, skills);
    }

    public List<Collaborator> getCollaboratorList() {
        return getCollaboratorRepository().getCollaboratorList();
    }

    public List<Skill> getSkillList() {
        return getSkillRepository().getSkillList();
    }

}
