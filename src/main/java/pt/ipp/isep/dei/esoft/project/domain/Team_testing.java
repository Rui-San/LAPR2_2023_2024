package pt.ipp.isep.dei.esoft.project.domain;



import java.util.ArrayList;
import java.util.List;

public class Team_testing {
    private List<Collaborator> members;
    private List<Skill> skillsNeeded;
    private List<Integer> quantityNeeded;

    public Team_testing(List<Collaborator> Collaborator, List<Skill> skillsNeeded, List<Integer> quantityNeeded) {
        this.members = new ArrayList<>(Collaborator);
        this.skillsNeeded = new ArrayList<>(skillsNeeded);
        this.quantityNeeded = new ArrayList<>(quantityNeeded);
    }

    public List<Collaborator> getMembers() {
        return new ArrayList<>(members);
    }

    public List<Skill> getSkillsNeeded() {
        return new ArrayList<>(skillsNeeded);
    }

    public List<Integer> getQuantityNeeded() {
        return new ArrayList<>(quantityNeeded);
    }

}
