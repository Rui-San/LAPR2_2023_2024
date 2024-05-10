package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.util.regex.Pattern;

/**
 * Represents a Skill object containing the skill name.
 */
public class Skill implements Cloneable{
    /**
     * The name of the skill.
     */
    private String skillName;

    /**
     * Constructs a Skill object with the specified skill name.
     *
     * @param skillName the name of the skill
     */
    public Skill(String skillName) {
        setSkillName(skillName);
    }

    /**
     * Returns the name of the skill.
     *
     * @return the name of the skill
     */
    public String getSkillName() {
        return skillName;
    }

    /**
     * Sets the name of the skill after validation.
     *
     *
     * @param skillName the name to set for the skill
     * @throws IllegalArgumentException if the skill name is empty or contains special characters or numbers
     */
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

    /**
     * Creates a deep copy of the Skill.
     *
     * @return the cloned Skill
     */
    @Override
    public Skill clone() {
        return new Skill(this.skillName);
    }

    /**
     * Validates the skill name to ensure it contains only letters, spaces, or hyphens.
     * If the skill name is not valid, returns the specific error (empty, null, or contains special characters)
     *
     * @param skillName the skill name to validate
     * @return the validation result
     */
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


