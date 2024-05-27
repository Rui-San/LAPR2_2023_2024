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

    public static boolean isDateBetween(Date dateToCheck, Date startDate, Date endDate) {
        // Check if dateToCheck is after or equal to startDate and before or equal to endDate
        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;

    }
}