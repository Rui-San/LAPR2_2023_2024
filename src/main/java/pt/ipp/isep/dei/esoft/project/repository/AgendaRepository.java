package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository {
    List<Task> agenda;

    private static final Status AGENDA_DEFAULT_STATUS = Status.PLANNED;

    public AgendaRepository() {
        this.agenda = new ArrayList<>();
    }

    public List<Task> getAgenda() {
        return agenda;
    }

    public Optional<Task> add(Task task, String executionDate) {

        Optional<Task> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskAgenda(task)) {
            task.setStatus(AGENDA_DEFAULT_STATUS);
            task.setExecutionDate(executionDate);
            newTask = Optional.of(task);
            operationSuccess = agenda.add(task);
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;
    }

    private boolean validateTaskAgenda(Task task) {
        String taskTitle = task.getTitle().trim();
        String taskGreenSpace = task.getGreenSpace().toString().trim();

        for (Task aTask : agenda) {
            if (!aTask.getTitle().trim().equalsIgnoreCase(taskTitle)) {
                return true;
            } else {
                if (aTask.getStatus() == AGENDA_DEFAULT_STATUS) {
                    if (aTask.getGreenSpace().toString().trim().equalsIgnoreCase(taskGreenSpace)) {
                        return false;
                    }
                }
            }


        }


        /*



        for (Task registeredTask : agenda) {
            if (registeredTask.getTitle().trim().equalsIgnoreCase(taskTitle) &&
                    registeredTask.getStatus() == AGENDA_DEFAULT_STATUS &&
                    registeredTask.getGreenSpace().toString().trim().equalsIgnoreCase(taskGreenSpace)) {
                return false;
            }
        }

         */
        return true;
    }



      /*
            if ((registeredTask.getTitle().trim().equalsIgnoreCase(taskTitle) && registeredTask.getStatus() == AGENDA_DEFAULT_STATUS)) {
                if(registeredTask.getGreenSpace().toString().trim().equalsIgnoreCase(taskGreenSpace)){
                    isValid = false;
                    return isValid;
                }else{
                    return isValid;
                }
            }
        }
        */


    public Optional<Task> registerTaskAgenda(Task task, String executionDate) {

        Optional<Task> addedTask = add(task, executionDate);

        return addedTask;
    }


    public Optional<Task> updateTaskToCanceled(String title, String greenSpace, String executionDate, Status status) {

        Optional<Task> updatedTask = Optional.empty();

        System.out.println("Title parametro: " + title);
        System.out.println("Green Space parametro: " + greenSpace);
        System.out.println("Ex date parametro: " + executionDate);
        System.out.println("Status do parametro: " + status);

        for (Task task : agenda) {
            System.out.println("Task do for: " + task.getTitle());
            System.out.println("Task do for: " + task.getGreenSpace().getName());
            System.out.println("Task do for: " + task.getExecutionDate());
            System.out.println("Task do for: " + task.getStatus());
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getExecutionDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpace.trim())) {

                if (task.getStatus() == Status.PLANNED || task.getStatus() == Status.POSTPONED) {

                    task.setStatus(Status.CANCELED);

                    System.out.println("Novo Status" + task.getStatus());
                    updatedTask = Optional.of(task);
                    return updatedTask;
                }

            }
        }
        return Optional.empty();
    }

    public Optional<Task> postponeTaskAgenda(String title, String greenSpaceName, String executionDate, Status status, String newDate) {


        return Optional.empty();
    }

/*
    public Optional<Task> assignTeamToTask(String title, String greenSpaceName, String executionDate, Status status, Team teamToAssign) {
        Optional<Task> assignedTask = Optional.empty();
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getExecutionDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpaceName.trim())) {
                if (teamToAssign.isAvailable(task.getExecutionDate(), task.getEndExecutionDate())) {
                    task.setTeamAssigned(teamToAssign);
                    teamToAssign.addWorkPeriod(task.getExecutionDate(), task.getEndExecutionDate());
                    assignedTask = Optional.of(task);
                }
            }
        }
        return assignedTask;
    }

 */
}

