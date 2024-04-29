package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Optional;

public class RegisterSkillController {
    private final SkillRepository skillRepository;

    public RegisterSkillController() {
        skillRepository = new SkillRepository();
    }

    public boolean registerSkill(String skillName) {
        Skill newSkill = new Skill(skillName);
        Optional<Skill> addedSkill = skillRepository.add(newSkill);
        return addedSkill.isPresent();
    }
}
