package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.tools.SerializationFiles;
import pt.ipp.isep.dei.esoft.project.tools.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Skill Repository class
 */
public class SkillRepository implements Serializable {
    /**
     * List to store all the skills.
     */
    private final List<Skill> skillList;

    /**
     * Constructs a SkillRepository object.
     */
    public SkillRepository() {
        skillList = new ArrayList<>();
    }

    /**
     * Adds a new skill to the repository after validation.
     *
     * @param skill the skill to add
     * @return an Optional containing the newly added skill if successful, empty otherwise
     */
    public Optional<Skill> add(Skill skill) {

        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skillList.add(newSkill.get());
            SerializationUtils.saveToFile(skillList, SerializationFiles.SKILL_DATABASE);
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
    }

    /**
     * Validates if a skill can be added based on its uniqueness.
     * Validates the specific skill is already on the list.
     *
     * @param skill the skill to validate
     * @return true if the skill can be added, false otherwise
     */
    private boolean validateSkill(Skill skill) {
        boolean isValid = true;
        String skillName = skill.getSkillName().trim().toLowerCase();

        for (Skill registeredSkill : skillList) {
            if (registeredSkill.getSkillName().trim().toLowerCase().equals(skillName)) {
                isValid = false;
                return isValid;
            }
        }
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Skill> getSkillList() {
        return List.copyOf(skillList);
    }

    /**
     * Registers a new skill and adds to the list of skills if validated
     *
     * @param skillName the name of the skill to register
     * @return an Optional containing the newly registered skill if successful, empty otherwise
     */
    public Optional<Skill> registerSkill(String skillName) {
        Skill newSkill = new Skill(skillName);
        Optional<Skill> addedSkill = add(newSkill);
        return addedSkill;
    }
}
