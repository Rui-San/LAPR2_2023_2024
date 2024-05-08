package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class HrmUI implements Runnable {

    public HrmUI() {

    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register new Skill", new RegisterSkillUI()));
        options.add(new MenuItem("Register new Job", new RegisterJobUI()));
        options.add(new MenuItem("Register new Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign skills to Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Generate team proposal", new TeamProposalUI_testing()));

        int option = 0;

        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Human Resources Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }

        } while (option != -1);
    }
}
