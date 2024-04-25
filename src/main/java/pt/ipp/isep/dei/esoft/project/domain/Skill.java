package pt.ipp.isep.dei.esoft.project.domain;

import java.util.regex.Pattern;

public class Skill {
    private String skillName;

    private enum SkillValidationResults {
        EMPTYNULL, VALID, CONTAINS_SPECIAL_CHARACTERS,
    }

    public Skill(String skillName) {
        setSkillName(skillName);
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        SkillValidationResults skillValidationResults = validateSkill(skillName);
        switch (skillValidationResults) {
            case EMPTYNULL:
                throw new IllegalArgumentException("Skill name must not be empty");
            case CONTAINS_SPECIAL_CHARACTERS:
                throw new IllegalArgumentException("Skill name must not contain special characters or numbers");
            case VALID:
                this.skillName = skillName;
        }
    }

    @Override
    public Skill clone() {
        return new Skill(this.skillName);
    }

    private SkillValidationResults validateSkill(String skillName) {
        if (skillName == null || skillName.trim().isEmpty()) {
            return SkillValidationResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-]+");

        if (namePattern.matcher(skillName).matches()) {
            return SkillValidationResults.VALID;
        } else {
            return SkillValidationResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }
}


