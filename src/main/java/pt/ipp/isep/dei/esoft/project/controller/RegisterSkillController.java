package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.Optional;

public class RegisterSkillController {
    private SkillRepository skillRepository;


    public RegisterSkillController(){
        skillRepository = getSkillRepository();
    }



    public RegisterSkillController(SkillRepository skillRepository) {
        skillRepository = new SkillRepository();
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /*
    public boolean registerSkill(String skillName) {
        Skill newSkill = new Skill(skillName);
        Optional<Skill> addedSkill = skillRepository.add(newSkill);
        return addedSkill.isPresent();
    }
    */

    public Optional<Skill> registerSkill(String skillName) {

        Optional<Skill> newSkill = Optional.empty();
        newSkill = skillRepository.registerSkill(skillName);
        return newSkill;
    }
}
