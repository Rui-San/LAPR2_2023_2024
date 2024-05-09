package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {

    @Test
    public void ensureCreateSkillWorksTest() {
        Skill skill = new Skill("Drive License");
    }

    @Test
    public void ensureGetSkillNameReturnsCorrectValue() {
        Skill skill = new Skill("Drive License");
        String expectedSkill = "Drive License";
        assertEquals(skill.getSkillName(), expectedSkill);
    }

    @Test
    public void ensureSkillCantBeNullOrEmpty() {
        String[] incorrectSkillNames = {"", " ", null};

        for (String invalidName : incorrectSkillNames) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Skill(invalidName);
            });
            assertTrue(exception.getMessage().contains("Skill name must not be empty"));
        }
    }

    @Test
    public void ensureSkillCantContainSpecialCharacters() {
        String[] incorrectSkillNames = {"Licen´´se", "ski123ll", ".skill"};

        for (String invalidName : incorrectSkillNames) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                new Skill(invalidName);
            });
            assertTrue(exception.getMessage().contains("Skill name must not contain special characters or numbers"));
        }
    }

    @Test
    public void ensureCloneWorks() {
        Skill skill = new Skill("Drive License");
        Skill skillClone = skill.clone();
        assertEquals(skill.getSkillName(), skillClone.getSkillName());
    }
}
