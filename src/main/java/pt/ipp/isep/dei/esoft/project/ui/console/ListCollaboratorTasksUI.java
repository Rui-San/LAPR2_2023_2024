package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.controller.ListCollaboratorTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Date;

public class ListCollaboratorTasksUI {

    private final ListCollaboratorTasksController controller;

    private Date initialDate;
    private Date finalDate;


    public ListCollaboratorTasksUI() {
        controller = new ListCollaboratorTasksController(initialDate, finalDate);
    }

    private ListCollaboratorTasksController getController(){
        return controller;
    }
}