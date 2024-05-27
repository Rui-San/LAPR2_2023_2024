package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.tools.Sorting;


import java.util.ArrayList;
import java.util.List;

public class ListCollaboratorTasksController {

    private Date initialDate;
    private Date finalDate;
    private String status;
    private String collaboratorName;

    public ListCollaboratorTasksController(Date initialDate, Date finalDate){
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    /**
     * @return sorted tasks assigned to the collaborator between two selected dates
     */

    public List<Task> getTasks() {

        //TODO - make the showing of the tasks only related to the user

        List<Task> agenda = new AgendaRepository().getAgenda();
        List<Object> tasks = new ArrayList<>(agenda);

        Sorting sorting = new Sorting(tasks);
        sorting.setColumnToSort(6);
        sorting.sort();

        agenda.clear();
        for (Object a : tasks) {
            Task b = (Task) a;

            //checks if the task is between the selected dates
            if (isDateBetween(b.getExecutionDate(), initialDate, finalDate)) {
                agenda.add((Task) a);
            }
            agenda.add((Task) a);
        }

        return agenda;

    }

    /**
     * Checks if dateToCheck is after or equal to startDate and before or equal to endDate
     */

    public static boolean isDateBetween(Date dateToCheck, Date startDate, Date endDate) {
        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;

    }

    /**
     * Updates the initial date.
     * @param initialDate the new initial date
     */
    public void updateInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * Updates the final date.
     * @param finalDate the new final date
     */
    public void updateFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}