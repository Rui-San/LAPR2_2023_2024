package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository {
    private final List<Skill> skillList;

    public SkillRepository() {
        skillList = new ArrayList<>();
    }

    private Optional<Skill> add(Skill skill) {

        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skillList.add(newSkill.get());
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
    }

    /**
     * @param skill
     * @return the logical state of the validation. True if the list of skills doesn't contain that skill.
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
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skillList);
    }

    public Optional<Skill> registerSkill(String skillName) {
        Skill newSkill = new Skill(skillName);
        Optional<Skill> addedSkill = add(newSkill);
        return addedSkill;
    }
}
