package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;

public class AssignSkillController {
    /**
     * Collaborator Repository
     */
    private CollaboratorRepository collaboratorRepository;
    /**
     * Skill Repository
     */
    private SkillRepository skillRepository;

    /**
     * Constructor of AssignSkillController
     */
    public AssignSkillController() {
        getCollaboratorRepository();
    }

    /**
     * Returns the Collaborator Repository
     * @return the Collaborator Repository
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Returns the Skill Repository
     * @return the Skill Repository
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }


    /**
     * Assign a list of skills to a specific collaborator.
     * @param collaborator the collaborator of the organization
     * @param skills the list of skills to assign
     */
    public void assignSkillsToCollaborator(Collaborator collaborator, List<Skill> skills) {
        getCollaboratorRepository().assignSkillsToCollaborator(collaborator, skills);
    }

    /**
     * Returns the list of collaborators from the repository
     * @return the list of collaborators from the repository
     */
    public List<Collaborator> getCollaboratorList() {
        return getCollaboratorRepository().getCollaboratorList();
    }

    /**
     * Returns the list of skills from the repository
     * @return the list of skills from the repository
     */
    public List<Skill> getSkillList() {
        return getSkillRepository().getSkillList();
    }

}
