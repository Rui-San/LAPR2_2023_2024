package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.util.regex.Pattern;

public class Skill implements Cloneable{
    private String skillName;

    public Skill(String skillName) {
        setSkillName(skillName);
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        ValidationAttributeResults skillValidationResults = validateSkill(skillName);
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

    private ValidationAttributeResults validateSkill(String skillName) {
        if (skillName == null || skillName.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-\\p{L}]+");

        if (namePattern.matcher(skillName).matches()) {
            return ValidationAttributeResults.VALID;
        } else {
            return ValidationAttributeResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }
}


