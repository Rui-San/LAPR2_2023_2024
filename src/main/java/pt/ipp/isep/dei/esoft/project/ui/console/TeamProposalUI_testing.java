package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.TeamProposalController_testing;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team_testing;

import java.util.*;

public class TeamProposalUI_testing implements Runnable {
    private final TeamProposalController_testing controller;

    private int minTeamSize;
    private int maxTeamSize;
    private List<Skill> skillsNeeded;
    private List<Integer> quantityNeeded;

    public TeamProposalUI_testing() {
        controller = new TeamProposalController_testing();
    }

    public TeamProposalController_testing getTeamProposalController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Generate a Team automatically ------------------------");

        requestData();
        generateTeamProposal();

    }

    private void generateTeamProposal() {
        boolean teamAccepted = false;

        while (!teamAccepted) {

            skillsNeeded = displayAndSelectSkillsNeeded();
            quantityNeeded = requestQuantityNeeded(skillsNeeded);

            Optional<Team_testing> teamProposal = getTeamProposalController().generateTeamProposal(minTeamSize, maxTeamSize, skillsNeeded, quantityNeeded);

            if (teamProposal != null) {
                System.out.println("Team proposal generated successfully:");
                System.out.println(teamProposal);
                teamAccepted = askManagerResponse();
                if (teamAccepted) {
                    controller.acceptTeamProposal(teamProposal);  //Adds the team to the teamList
                }
            }else {
                System.out.println("Could not generate a team proposal with given information");
                return;
            }

        }
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
