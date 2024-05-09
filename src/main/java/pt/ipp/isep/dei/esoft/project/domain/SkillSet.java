package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a combination of a skill with a quantity needed of that skill.
 */
public class SkillSet implements Cloneable {

    /**
     * The object skill.
     */
    private Skill skill;

    /**
     * The quantity of skill needed.
     */
    private int inQuantity;

    /**
     * Constructs a new SkillSet with the specified skill and quantity.
     *
     * @param skill      the skill of the SkillSet
     * @param inQuantity the quantity of the SkillSet
     */
    public SkillSet(Skill skill, int inQuantity) {
        this.skill = skill;
        this.inQuantity = inQuantity;
    }

    /**
     * Returns the skill of the SkillSet.
     *
     * @return the skill
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the skill of the SkillSet.
     *
     * @param skill the skill to set
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * Gets the quantity needed of that skill.
     *
     * @return the quantity
     */
    public int getInQuantity() {
        return inQuantity;
    }

    /**
     * Sets the quantity needed of that Skill.
     *
     * @param inQuantity the quantity to set
     */
    public void setInQuantity(int inQuantity) {
        this.inQuantity = inQuantity;
    }

    /**
     * Returns a string representation of the object SkillSet
     *
     * @return a string representation of the object SkillSet with the skill name and the quantity needed of that skill
     */
    @Override
    public String toString() {
        return "Skill : " + skill.getSkillName() +
                "\nQuantity : " + inQuantity;
    }

    /**
     * Retrieves a list of all skills from a list of SkillSets.
     *
     * @param skillSetList the list of SkillSets
     * @return a list of skills
     */
    public static List<Skill> getAllSkills(List<SkillSet> skillSetList) {
        List<Skill> skillsList = new ArrayList<>();
        for (SkillSet skillSet : skillSetList) {
            skillsList.add(skillSet.getSkill());
        }
        return skillsList;
    }

    /**
     * Creates a deep copy of the SkillSet.
     *
     * @return the cloned SkillSet
     */
    @Override
    public SkillSet clone(){
        Skill clonedSkill = this.getSkill().clone();
        SkillSet clone = new SkillSet(clonedSkill, this.getInQuantity());
        return clone;
    }
}
