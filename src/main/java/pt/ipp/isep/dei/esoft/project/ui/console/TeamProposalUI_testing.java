package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.TeamProposalController_testing;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team_testing;

import java.util.*;

public class TeamProposalUI_testing implements Runnable {
    private final TeamProposalController_testing controller;

    private int minTeamSize;
    private int maxTeamSize;
    private List<Skill> skillsNeeded;
    private List<Integer> quantityNeeded;
    private Team_testing teamAccepted;

    public TeamProposalUI_testing() {
        controller = new TeamProposalController_testing();
    }

    public TeamProposalController_testing getTeamProposalController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Generate a Team automatically ------------------------");

        requestData();
        List<Team_testing> generatedTeams = generateAllTeamProposals();
        teamAccepted = displayAndSelectTeamForApproval(generatedTeams);

        if (teamAccepted != null) {
            submitSelectedTeam(teamAccepted);
            System.out.println("Team selected successfully saved.");
        } else {
            System.out.println("No team selected. Process canceled.");
        }

    }

    private void submitSelectedTeam(Team_testing teamAccepted) {
        getTeamProposalController().saveTeamProposal(teamAccepted);

    }

    private Team_testing displayAndSelectTeamForApproval(List<Team_testing> generatedTeams) {
        if (generatedTeams.isEmpty()) {
            throw new IllegalArgumentException("No teams have been generated with given inputs");
        } else {
            boolean teamAccepted = false;

            while (!teamAccepted) {
                for (Team_testing generatedTeam : generatedTeams) {
                    System.out.println("Team Generated:");
                    System.out.println(generatedTeam.toString());
                    teamAccepted = askManagerResponse();
                    if(teamAccepted){
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

    private List<Team_testing> generateAllTeamProposals() {

        skillsNeeded = displayAndSelectSkillsNeeded();
        quantityNeeded = requestQuantityNeeded(skillsNeeded);

        List<Team_testing> teamsGenerated = getTeamProposalController().generateAllTeamProposal(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded);

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
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the quantity needed for each selected Skill:");

        for (Skill skill : skillsNeeded) {
            System.out.print("Quantity needed for skill " + skill.getSkillName() + ": ");
            try {
                int quantity = input.nextInt();
                quantityNeeded.add(quantity);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please select an Integer.");
                input.nextLine();
                System.out.print("Quantity needed for skill " + skill.getSkillName() + ": ");
                int quantity = input.nextInt();
                quantityNeeded.add(quantity);
            }
        }
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
    }

    private void requestData() {
        minTeamSize = requestMinTeamSize();
        maxTeamSize = requestMaxTeamSize();
    }

    private int requestMaxTeamSize() {
        Scanner input = new Scanner(System.in);
        System.out.println("Minimum size of team: ");
        return input.nextInt();
    }

    private int requestMinTeamSize() {
        Scanner input = new Scanner(System.in);
        System.out.println("Maximum size of team: ");
        return input.nextInt();
    }

}
