package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkillSetTest {

    @Test
    public void ensureCreateSkillSetWorksTest() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);
    }

    @Test
    public void ensureGetSkillReturnsCorrectValue() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);

        assertEquals(skillSet.getSkill(), skillNeeded);
    }

    @Test
    public void ensureSetSkillWorks() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);

        Skill newSkillNeeded = new Skill("License Drive");
        skillSet.setSkill(newSkillNeeded);

        assertEquals(skillSet.getSkill(), newSkillNeeded);
    }

    @Test
    public void ensureGetInQuantityReturnsCorrectValue() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);

        assertEquals(skillSet.getInQuantity(), quantityNeeded);
    }

    @Test
    public void ensureSetInQuantityWorks() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);

        int newQuantityNeeded = 4;
        skillSet.setInQuantity(newQuantityNeeded);

        assertEquals(skillSet.getInQuantity(), newQuantityNeeded);
    }

    @Test
    public void ensureGetAllSkillsReturnsCorrectValue() {
        List<SkillSet> skillSetNeeded = new ArrayList<>();
        SkillSet skillSet1 = new SkillSet(new Skill("Equipment Maintenance"), 1);
        SkillSet skillSet2 = new SkillSet(new Skill("Heavy Vehicle Driving License"), 1);
        SkillSet skillSet3 = new SkillSet(new Skill("Light Vehicle Driving License"), 1);
        skillSetNeeded.add(skillSet1);
        skillSetNeeded.add(skillSet2);
        skillSetNeeded.add(skillSet3);

        List<Skill> expectedSkills = new ArrayList<>();
        for (SkillSet skillSet : skillSetNeeded) {
            Skill skill = skillSet.getSkill();
            expectedSkills.add(skill);
        }
        assertEquals(SkillSet.getAllSkills(skillSetNeeded), expectedSkills);
    }

    @Test
    public void ensureCloneWorks() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);

        SkillSet skillSetClone = skillSet.clone();

        assertEquals(skillSet.toString(), skillSetClone.toString());
    }

    @Test
    public void ensureToStringShowsExpectedResult() {
        Skill skillNeeded = new Skill("Tree Prunning");
        int quantityNeeded = 2;
        SkillSet skillSet = new SkillSet(skillNeeded, quantityNeeded);

        String expectedResult = "Skill : Tree Prunning\n" + "Quantity : 2";
        assertEquals(expectedResult, skillSet.toString());
    }
}
