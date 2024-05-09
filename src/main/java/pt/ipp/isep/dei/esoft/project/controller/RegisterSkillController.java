package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Optional;

/**
 * Controller class responsible for handling operations related to registering skills.
 */
public class RegisterSkillController {
    /**
     * Skill Repository
     */
    private SkillRepository skillRepository;

    /**
     * Constructor of a RegisterSkillController object.
     */
    public RegisterSkillController(){
        skillRepository = getSkillRepository();
    }

    /**
     * Constructs a RegisterSkillController object with the specified skill repository.
     * Allows receiving the repositories as parameters for testing purposes
     *
     * @param skillRepository the skill repository
     */
    public RegisterSkillController(SkillRepository skillRepository) {
        skillRepository = new SkillRepository();
    }

    /**
     * Returns the skill repository
     * @return the skill repository
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Registers a new skill.
     *
     * @param skillName the name of the skill to register
     * @return an Optional containing the newly registered skill if successful, empty otherwise
     */
    public Optional<Skill> registerSkill(String skillName) {

        Optional<Skill> newSkill = Optional.empty();
        newSkill = skillRepository.registerSkill(skillName);
        return newSkill;
    }
}
