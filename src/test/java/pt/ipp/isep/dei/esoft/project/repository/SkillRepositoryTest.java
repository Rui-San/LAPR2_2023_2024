package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SkillRepositoryTest {

    @Test
    void testAddValidSkill() {
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Java language");
        Optional<Skill> addedSkill = skillRepository.add(skill);
        assertTrue(addedSkill.isPresent());
    }

    @Test
    void testAddDuplicateSkill() {
        SkillRepository skillRepository = new SkillRepository();

        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("java");

        skillRepository.add(skill1);

        Optional<Skill> addedSkill = skillRepository.add(skill2);
        assertTrue(addedSkill.isEmpty());
        assertTrue(skillRepository.getSkillList().size() == 1);
    }

    @Test
    void testGetSkillList() {
        SkillRepository skillRepository = new SkillRepository();

        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Python");

        skillRepository.add(skill1);
        skillRepository.add(skill2);

        List<Skill> skills = skillRepository.getSkillList();
        assertEquals(2, skills.size());

        for (Skill skill : skills) {
            assertTrue(skill.getSkillName().equals(skill1.getSkillName()) || skill.getSkillName().equals(skill2.getSkillName()));

        }
    }

    @Test
    void testSkillListImmutability() {
        SkillRepository skillRepository = new SkillRepository();
        List<Skill> skills = skillRepository.getSkillList();
        assertThrows(UnsupportedOperationException.class, () -> skills.add(new Skill("Tree Prunning")));
    }

    @Test
    void testRegisterSkill() {
        SkillRepository skillRepository = new SkillRepository();

        String skillName = "Tree Prunning";
        Optional<Skill> addedSkill = skillRepository.registerSkill(skillName);

        assertTrue(addedSkill.isPresent());
        assertEquals(skillName, addedSkill.get().getSkillName());

        List<Skill> skills = skillRepository.getSkillList();
        assertTrue(skills.contains(addedSkill.get()));
    }

}
