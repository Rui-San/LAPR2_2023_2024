package pt.ipp.isep.dei.esoft.project.ui.console;

import jdk.jshell.execution.Util;
import pt.ipp.isep.dei.esoft.project._templateFiles.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Assign Skill UI class
 */
public class AssignSkillUI implements Runnable {
    private final AssignSkillController controller;
    private Collaborator selectedCollaborator;
    private List<Skill> selectedSkills;

    /**
     * Constructor for the AssignSkillUI class
     */
    public AssignSkillUI() {
        controller = new AssignSkillController();
    }

    /**
     * Returns the controller
     * @return the controller
     */
    public AssignSkillController getController() {
        return this.controller;
    }

    /**
     * Runs the Assign Skill UI
     */
    @Override
    public void run() {
        System.out.println("\n\n=== ASSIGN SKILLS TO COLLABORATOR ===");

        selectedCollaborator = displayAndSelectCollaborator();

        displayAndSelectSkills();

        submitData();
    }

    /**
     * Submits the data
     */
    private void submitData() {
        showAllDataForConfirmation(selectedSkills, "You're about to assign the following skills to the collaborator:");
        if (Utils.confirm("Do you want to proceed? (y/n)")) {

            getController().assignSkillsToCollaborator(selectedCollaborator, selectedSkills);

            if (selectedCollaborator.getSkillList().containsAll(selectedSkills)) {
                System.out.println();
                System.out.println("All selected skills were successfully added to the collaborator.");
            } else {
                System.out.println("An error occurred while adding the skills to the collaborator.");
            }
        }
    }

    /**
     * Displays the list of collaborators and selects a collaborator
     * @return the collaborator
     */
    private Collaborator displayAndSelectCollaborator() {

        List<Collaborator> collaboratorList = getController().getCollaboratorList();

        int counter = 1;
        System.out.println("\nAvailable collaborators:");
        for (Collaborator collaborator : collaboratorList) {
            System.out.println(counter + " - " + collaborator.getName() + " (" + collaborator.getEmail() + ")");
            counter += 1;
        }

        int option;
        do {
            option = Utils.readIntegerFromConsole("Select a collaborator to assign a skill to: ") - 1;

            if (option < 0 || option >= collaboratorList.size()) {
                System.out.println("Please select an option from the list.");
            }
        } while (option < 0 || option >= collaboratorList.size());
        return collaboratorList.get(option);
    }

    /**
     * Displays the list of skills and selects skills
     */
    private void displayAndSelectSkills() {

        List<Skill> skillList = getController().getSkillList();
        selectedSkills = new ArrayList<>();

        int counter = 1;
        System.out.println("\nAvailable skills:");
        for (Skill skill : skillList) {
            System.out.println(counter + " - " + skill.getSkillName());
            counter += 1;
        }

        int option;
        do {
            do {
                do {
                    option = Utils.readIntegerFromConsole("Select a skill to add to the collaborator: ") - 1;

                    if (option < 0 || option >= skillList.size()) {
                        System.out.println("Please select an option from the list.");
                    }
                } while (option < 0 || option >= skillList.size());

                if (selectedSkills.contains(skillList.get(option)) || selectedCollaborator.getSkillList().contains(skillList.get(option))) {
                    System.out.println("This skill has already been chosen, please select a different one.");
                }

            } while (selectedSkills.contains(skillList.get(option)) || selectedCollaborator.getSkillList().contains(skillList.get(option)));
            selectedSkills.add(skillList.get(option));
        } while (Utils.confirm("Do you want to add another skill? (y/n)"));

    }

    /**
     * Shows all data for confirmation
     * @param skills the skills to add
     * @param header the header displays a custom message
     */
    private void showAllDataForConfirmation(List<Skill> skills, String header) {
        System.out.println();
        System.out.println(header);

        for (Skill skill : skills) {
            System.out.println(skill.getSkillName());
        }

    }

}
