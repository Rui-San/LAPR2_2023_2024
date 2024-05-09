package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.TeamProposalController_testing;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;

public class TeamProposalUI_testing implements Runnable {
    private final TeamProposalController_testing controller;

    private int minTeamSize;
    private int maxTeamSize;
    private List<Skill> skillsNeeded;
    private List<Integer> quantityNeeded;
    private Team teamAccepted;

    public TeamProposalUI_testing() {
        controller = new TeamProposalController_testing();
    }

    public TeamProposalController_testing getTeamProposalController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Generate a Team automatically ------------------------");

        requestData();
        List<Team> generatedTeams = generateAllTeamProposals();
        try {
            teamAccepted = displayAndSelectTeamForApproval(generatedTeams);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (teamAccepted != null) {
            saveTeamProposal(teamAccepted);
            System.out.println("Team selected successfully saved.");
        } else {
            System.out.println("No team selected. Process canceled.");
        }

    }

    private void saveTeamProposal(Team teamAccepted) {
        getTeamProposalController().saveTeamProposal(teamAccepted);

    }

    private Team displayAndSelectTeamForApproval(List<Team> generatedTeams) {
        if (generatedTeams.isEmpty()) {
            throw new IllegalArgumentException("Theres no possible teams with given inputs");
        } else {
            boolean teamAccepted = false;

            while (!teamAccepted) {
                for (Team generatedTeam : generatedTeams) {
                    System.out.println("Team Generated:");
                    System.out.println(generatedTeam.toString());
                    teamAccepted = askManagerResponse();
                    if (teamAccepted) {
                        return generatedTeam;
                    }
                }

                boolean validResponse = false;
                System.out.println("Do you want to view the options again or proceed without selecting any team? (view/proceed)");
                while (!validResponse) {
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("proceed")) {
                        return null;
                    } else if (response.equals("view")) {
                        validResponse = true;
                    } else {
                        System.out.println("Invalid option. Please select 'view' or 'proceed'.");
                    }
                }
            }
            return null;
        }
    }

    private List<Team> generateAllTeamProposals() {

        skillsNeeded = displayAndSelectSkillsNeeded();
        quantityNeeded = requestQuantityNeeded(skillsNeeded);

        List<Team> teamsGenerated = getTeamProposalController().generateAllTeamProposal(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded);

        return teamsGenerated;
    }


    private boolean askManagerResponse() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you accept this team proposal? (yes/no)");
        String response = input.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

    private List<Integer> requestQuantityNeeded(List<Skill> skillsNeeded) {
        List<Integer> quantityNeeded = new ArrayList<>();

        System.out.println("Enter the quantity needed for each selected Skill:");
        System.out.println();

        for (Skill skill : skillsNeeded) {
            int quantity;
            do {
                quantity = Utils.readIntegerFromConsole("Quantity needed for skill " + skill.getSkillName() + ": ");
                if (quantity <= 0) {
                    System.out.println("The quantity must be higher than zero.");
                }
            } while (quantity <= 0);
            quantityNeeded.add(quantity);
        }
        System.out.println();
        return quantityNeeded;
    }

    private List<Skill> displayAndSelectSkillsNeeded() {
        List<Skill> skillList = controller.getSkillList();
        List<Skill> skillsNeeded = new ArrayList<>();

        int listSize = skillList.size();

        Scanner input = new Scanner(System.in);
        boolean chooseSkill = true;
        displaySkillOptions(skillList);
        System.out.println("Which skills are needed for the team?");

        while (chooseSkill) {
            System.out.print("Skill: ");

            try {
                int answer = input.nextInt();

                if (answer == 0) {
                    chooseSkill = false;
                } else if (answer > 0 && answer <= listSize) {
                    Skill selectedSkill = skillList.get(answer - 1);
                    skillsNeeded.add(selectedSkill);
                } else {
                    System.out.println("Invalid option! Please select a valid number.");
                }
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid input! Please select a valid number.");
            }
        }
        return skillsNeeded;
    }

    private void displaySkillOptions(List<Skill> skillList) {
        int i = 1;
        for (Skill skill : skillList) {
            System.out.println("  " + i + " - " + skill.getSkillName());
            i++;
        }
        System.out.println("  0 - Finish selecting skills");
    }

    private void requestData() {
        minTeamSize = requestMinTeamSize();
        maxTeamSize = requestMaxTeamSize();
    }

    private int requestMaxTeamSize() {
        int input = -1;
        while (input < minTeamSize) {
            input = Utils.readIntegerFromConsole("Maximum size of team: ");
            if (input < minTeamSize) {
                System.out.println("Maximum team size must be equal or higher than Minimum team size (" + minTeamSize + ").");
            }
        }
        return input;
    }

    private int requestMinTeamSize() {
        int input = -1;
        while (input < 2) {
            input = Utils.readIntegerFromConsole("Minimum size of team: ");
            if (input < 2) {
                System.out.println("A team must have at least 2 members.");
            }
        }
        return input;
    }

}
