package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class TeamProposal {
    private int minTeamSize;
    private int maxTeamSize;
    private Map<Skill, Integer> selectedSkills = new HashMap<>();
    private SkillRepository skillRepository;
    private TeamRepository teamRepository;

    public TeamProposal(SkillRepository skillRepository, TeamRepository teamRepository) {
        this.skillRepository = skillRepository;
        this.teamRepository = teamRepository;
    }

    public void requestTeamSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Minimum team size:");
        minTeamSize = scanner.nextInt();
        System.out.println("Maximum team size:");
        maxTeamSize = scanner.nextInt();
    }

    public void selectSkills() {
        List<Skill> skills = skillRepository.getSkillList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (Skill skill : skills) {
                System.out.println("Add skill " + skill.getSkillName() + " to team? (yes/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    System.out.println("How many of this skill?");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline left-over
                    selectedSkills.put(skill, quantity);
                }
            }
            System.out.println("Do you want to select more skills? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break;
            }
        }
    }

    public Map<Skill, Integer> generateTeamProposal() {
        return new HashMap<>(selectedSkills);
    }

    public boolean showTeamProposal(Map<Skill, Integer> team) {
        System.out.println("Proposed team: " + team);
        Scanner scanner = new Scanner(System.in);
        System.out.println("accept this team? (yes/no)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    public void createTeam() {
        boolean isAccepted = false;
        while (!isAccepted) {
            Map<Skill, Integer> teamProposal = generateTeamProposal();
            isAccepted = showTeamProposal(teamProposal);
            if (isAccepted) {
                boolean isAdded = teamRepository.addTeam(teamProposal);
                if (isAdded) {
                    System.out.println("Team successfully created and added to the repository.");
                } else {
                    System.out.println("Failed to create and add the team to the repository.");
                }
            }
        }
    }
}
