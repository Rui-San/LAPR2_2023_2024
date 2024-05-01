package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import java.util.ArrayList;
import java.sql.SQLOutput;
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

        CollaboratorRepository collaboratorRepository = new CollaboratorRepository();
        List<Collaborator> collaborators = collaboratorRepository.getCollaboratorList();

        System.out.println("Select the collaborator:");
        for (int i = 0; i < collaborators.size(); i++) {
            System.out.println(i + 1 + ". " + collaborators.get(i).getName());
        }
        int collaboratorIndex = scanner.nextInt() - 1;
        selectedCollaborator = collaborators.get(collaboratorIndex);

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
        requestData();
        submitData();
    }

    public static void main(String[] args) {
        AssignSkillUI assignSkillUI = new AssignSkillUI();
        assignSkillUI.run();
    }
}
