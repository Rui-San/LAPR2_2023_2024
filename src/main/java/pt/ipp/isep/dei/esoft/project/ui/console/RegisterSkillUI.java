package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.RegisterSkillController;

import java.util.Scanner;

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
        System.out.println("\n\n---------- Create Skill ------------------");

        requestData();

        submitData();
    }

    private void submitData() {

        boolean isRegistered = controller.registerSkill(skillName);

        if (isRegistered) {
            System.out.println("\nSkill registered!");
        } else {
            System.out.println("\nregistration failed!");
        }
    }

    private void requestData() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter skill name:");
        skillName = scanner.nextLine();
    }
    public static void main(String[] args) {
        RegisterSkillUI registerSkillUI = new RegisterSkillUI();
        registerSkillUI.run();
    }
}
