package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.tools.ValidationAttributeResults;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RegisterSkillUI implements Runnable{
    private final RegisterSkillController controller;
    private String skillName;

    public RegisterSkillUI() {

        controller = new RegisterSkillController();
    }

    private RegisterSkillController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n---------- Register new Skill ------------------");

        requestData();

        submitData();
    }

    private void submitData() {
/*
        boolean isRegistered = controller.registerSkill(skillName);

        if (isRegistered) {
            System.out.println("\nSkill registered!");
        } else {
            System.out.println("\nregistration failed!");
        }
*/
        Optional<Skill> skill = getController().registerSkill(skillName);

        if (skill.isPresent()) {
            System.out.println("\nSkill successfully registered!");
        } else {
            System.out.println("\nSkill not registered!");
        }


    }

    private void requestData() {
/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter skill name:");
        String skillname = scanner.nextLine();
        */
        skillName = requestSkillName();
    }

    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        String response = "";

        while (!validInput) {
            try {
                System.out.println("Enter skill name: ");
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

    /*
    public static void main(String[] args) {
        RegisterSkillUI registerSkillUI = new RegisterSkillUI();
        registerSkillUI.run();
    }
     */
}
