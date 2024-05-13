package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Register Skill UI class
 */
public class RegisterSkillUI implements Runnable {
    private final RegisterSkillController controller;
    private String skillName;

    /**
     * Constructor for the RegisterSkillUI class
     */
    public RegisterSkillUI() {

        controller = new RegisterSkillController();
    }

    /**
     * Returns the controller
     * @return the controller
     */
    private RegisterSkillController getController() {
        return controller;
    }

    /**
     * Runs the Register Skill UI
     */
    public void run() {
        System.out.println("\n\n=== REGISTER NEW SKILL ===");

        requestData();

        submitData();
    }

    /**
     * Submits the data
     */
    private void submitData() {

        showAllDataForConfirmation(skillName, "You're about to register the following Skill:");
        if (Utils.confirm("Do you want to proceed? (y/n)")) {


            Optional<Skill> skill = getController().registerSkill(skillName);

            if (skill.isPresent()) {
                System.out.println("\nSkill successfully registered!");
            } else {
                System.out.println("\nThis Skill is already registered!");
            }
        }
    }

    /**
     * Request data from the user
     */
    private void requestData() {

        skillName = requestSkillName();
    }

    /**
     * Requests the skill name
     * @return the skill name
     */
    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.print("\nEnter skill name: ");
                response = input.nextLine();

                ValidationAttributeResults validateSkillNameResults = validateSkill(response);

                switch (validateSkillNameResults) {
                    case EMPTYNULL:
                        throw new IllegalArgumentException("Skill name must not be empty");
                    case CONTAINS_SPECIAL_CHARACTERS:
                        throw new IllegalArgumentException("Skill name must not contain special characters or numbers");
                    case VALID:
                        validInput = true;
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }

    /**
     * Validates the skill
     * @param skillName the skill name
     * @return the validation attribute results
     */
    private ValidationAttributeResults validateSkill(String skillName) {
        if (skillName == null || skillName.trim().isEmpty()) {
            return ValidationAttributeResults.EMPTYNULL;
        }

        Pattern namePattern = Pattern.compile("[a-zA-Z\\s-\\p{L}]+");

        if (namePattern.matcher(skillName).matches()) {
            return ValidationAttributeResults.VALID;
        } else {
            return ValidationAttributeResults.CONTAINS_SPECIAL_CHARACTERS;
        }
    }

    /**
     * Shows all data for confirmation
     * @param typedSkill the typed skill
     * @param header the header displays a custom message
     */
    public void showAllDataForConfirmation(String typedSkill, String header) {
        System.out.println();
        System.out.println(header);
        System.out.println(typedSkill);
    }

    /*
    public static void main(String[] args) {
        RegisterSkillUI registerSkillUI = new RegisterSkillUI();
        registerSkillUI.run();
    }
     */
}
