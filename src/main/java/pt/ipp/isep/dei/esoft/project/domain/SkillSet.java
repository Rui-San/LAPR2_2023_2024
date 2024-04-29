package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class SkillSet {
    private Skill skill;
    private int inQuantity;

    public SkillSet(Skill skill, int inQuantity) {
        this.skill = skill;
        this.inQuantity = inQuantity;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getInQuantity() {
        return inQuantity;
    }

    public void setInQuantity(int inQuantity) {
        this.inQuantity = inQuantity;
    }

    public static List<Skill> getAllSkills(List<SkillSet> skillSetList) {
        List<Skill> skillsList = new ArrayList<>();
        for (SkillSet skillSet : skillSetList) {
            skillsList.add(skillSet.getSkill());
        }
        return skillsList;
    }
}
