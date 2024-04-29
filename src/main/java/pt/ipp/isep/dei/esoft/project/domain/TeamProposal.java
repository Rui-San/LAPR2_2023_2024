package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeamProposal {
    private int minTeamSize;
    private int maxTeamSize;
    private List<Skill> selectedSkills = new ArrayList<>();
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;

    public TeamProposal(SkillRepository skillRepository, TeamRepository teamRepository) {
        this.skillRepository = skillRepository;
        this.teamRepository = teamRepository;
    }

    public void requestTeamSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter minimum team size:");
        minTeamSize = scanner.nextInt();
        System.out.println("Enter maximum team size:");
        maxTeamSize = scanner.nextInt();
    }

    public void selectSkills() {
        List<Skill> skills = skillRepository.getSkillList();
        Scanner scanner = new Scanner(System.in);
        for (Skill skill : skills) {
            System.out.println("add skill " + skill.getSkillName() + " to team? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                selectedSkills.add(skill);
            }
        }
    }

    public List<Skill> generateTeamProposal() {

        return new ArrayList<>(selectedSkills);
    }

    public boolean showTeamProposal(List<Skill> team) {
        System.out.println("Proposed team: " + team);
        Scanner scanner = new Scanner(System.in);
        System.out.println("accept this team? (yes/no)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    public void createTeam(List<Skill> team) {
        boolean isAdded = teamRepository.addTeam(team);
        if (isAdded) {
            System.out.println("Team successfully created and added to the repository.");
        } else {
            System.out.println("Failed to create and add the team to the repository.");
        }
    }
}
