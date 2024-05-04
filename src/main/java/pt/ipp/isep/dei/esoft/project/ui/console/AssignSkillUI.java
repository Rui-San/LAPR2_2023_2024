package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignSkillUI implements Runnable {
    private final AssignSkillController controller;
    private Collaborator selectedCollaborator;
    private List<Skill> selectedSkills;

    public AssignSkillUI() {
        controller = new AssignSkillController();
    }

    private void requestData() {
        Scanner scanner = new Scanner(System.in);


        SkillRepository skillRepository = new SkillRepository();
        List<Skill> skills = skillRepository.getSkillList();

        System.out.println("Select the skills:");
        for (int i = 0; i < skills.size(); i++) {
            System.out.println(i + 1 + ". " + skills.get(i).getSkillName());
        }
        selectedSkills = new ArrayList<>();
        while (true){
            int skillIndex = scanner.nextInt() -1;
            if (skillIndex < 0 || skillIndex >= skills.size()) {
                break;
            }
            selectedSkills.add(skills.get(skillIndex));
        }
    }

    private void submitData() {
        boolean isAssigned = controller.assignSkillsToCollaborator(selectedCollaborator, selectedSkills);

        if (isAssigned) {
            System.out.println("\nSkills assigned!");
        } else {
            System.out.println("\nAssignment failed!");
        }
    }

    @Override
    public void run() {
        System.out.println("\nAssign skill to collaborator\n");
        selectedCollaborator = displayAndSelectCollaborator();

        requestData();
        submitData();
    }

    private Collaborator displayAndSelectCollaborator() {

        List<Collaborator> collaboratorList = controller.getCollaborators();

        int numberOfCollaborator = collaboratorList.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > numberOfCollaborator) {
            displayCollaboratorOptions(collaboratorList);
            System.out.print("Select collaborator: ");
            answer = input.nextInt();
        }

        Collaborator collaborator = collaboratorList.get(answer-1);
        return collaborator;
    }

    private void displayCollaboratorOptions(List<Collaborator> collaboratorList) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Collaborator collaborator : collaboratorList) {
            System.out.println("  " + i + " - " + collaborator.getName());
            i++;
        }
    }
}
