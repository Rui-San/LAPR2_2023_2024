package pt.ipp.isep.dei.esoft.project.ui.console;


import javafx.scene.control.TableView;
import pt.ipp.isep.dei.esoft.project.controller.ListCollaboratorTasksController;
import pt.ipp.isep.dei.esoft.project.domain.Date;


import java.net.URL;
import java.util.ResourceBundle;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;

public class ListCollaboratorTasksUI{

    private final ListCollaboratorTasksController controller;

    private Date initialDate;
    private Date finalDate;

    /**
     * Creates an instance of ListCollaboratorTasksUI.
     */

    public ListCollaboratorTasksUI() {
        controller = new ListCollaboratorTasksController(initialDate, finalDate);
    }

    /**
     * Gets the controller.
     * @return controller
     */
    private ListCollaboratorTasksController getController(){
        return controller;
    }


}