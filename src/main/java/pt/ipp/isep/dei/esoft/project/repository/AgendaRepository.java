package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository implements Serializable {
    /**
     * The agenda of tasks.
     */
    List<Task> agenda;

    /**
     * The default status for tasks in the agenda.
     */
    private static final Status AGENDA_DEFAULT_STATUS = Status.PLANNED;

    /**
     * Instantiates a new Agenda repository.
     */
    public AgendaRepository() {
        this.agenda = new ArrayList<>();
    }

    /**
     * Gets agenda.
     * @return the agenda
     */
    public List<Task> getAgenda() {
        return agenda;
    }

    /**
     * Gets manager specific agenda.
     * @param managerEmail the manager email
     * @return the manager specific agenda
     */
    public List<Task> getManagerSpecificAgenda(String managerEmail) {
        List<Task> managerSpecificAgenda = new ArrayList<>();

        for (Task task : agenda) {
            if (task.getGreenSpace().getManager().trim().equalsIgnoreCase(managerEmail.trim())) {
                managerSpecificAgenda.add(task);
            }
        }

        return managerSpecificAgenda;
    }

    /**
     * Gets task for a DTO
     * @param taskDTO the task DTO
     * @return the task
     */
    public Task getTask(AgendaTaskDTO taskDTO) {
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(taskDTO.title.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(taskDTO.greenSpaceName.trim()) && task.getStatus().toString().trim().equalsIgnoreCase(taskDTO.status.toString())) {
                return task;
            }
        }
        return null;
    }

    /**
     * Gets collaborator specific agenda.
     * @param collaboratorEmail the collaborator email
     * @return the collaborator specific agenda
     */
    public List<Task> getCollbaboratorSpecificAgenda(String collaboratorEmail) {
        List<Task> collaboratorSpecificAgenda = new ArrayList<>();

        for (Task task : agenda) {
            if (task.getTeamAssigned() != null) {
                List<Collaborator> teamOnTask = task.getTeamAssigned().getMembers();

                for(Collaborator collaborator : teamOnTask){
                    if(collaborator.getEmail().getEmail().trim().equalsIgnoreCase(collaboratorEmail)){
                        collaboratorSpecificAgenda.add(task);
                    }
                }

            }
        }

        return collaboratorSpecificAgenda;
    }

    /**
     * Add task to the agenda.
     * @param task the task
     * @param executionDate the execution date
     * @param workStartingHours the work starting hours
     * @param workStartingMinutes the work starting minutes
     * @return the optional task
     */
    public Optional<Task> add(Task task, String executionDate, int workStartingHours, int workStartingMinutes) {

        Optional<Task> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskAgenda(task)) {
            task.setStatus(AGENDA_DEFAULT_STATUS);
            task.setTaskWorkPeriod(executionDate, workStartingHours, workStartingMinutes, task.getExpectedDuration());
            System.out.println("-----------" + task.getExpectedDuration());
            System.out.println("Workperiod Task da agenda:" + task.getTaskWorkPeriod().getWorkStartDate() + task.getTaskWorkPeriod().getWorkStartHour() + task.getTaskWorkPeriod().getWorkStartMin() + task.getTaskWorkPeriod().getWorkEndDate() + task.getTaskWorkPeriod().getWorkEndHour() + task.getTaskWorkPeriod().getWorkEndMin());

            newTask = Optional.of(task);
            operationSuccess = agenda.add(task);
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;
    }

    /**
     * Validate task in the agenda.
     * @param task the task
     * @return true of task is valid, false otherwise
     */
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

        return true;
    }

    /**
     * Register task agenda.
     * @param task the task
     * @param executionDate the execution date
     * @param workStartingHours the work starting hours
     * @param workStartingMinutes the work starting minutes
     * @return the optional task
     */
    public Optional<Task> registerTaskAgenda(Task task, String executionDate, int workStartingHours, int workStartingMinutes) {

        Optional<Task> addedTask = add(task, executionDate, workStartingHours, workStartingMinutes);

        return addedTask;
    }

    /**
     * Update task to canceled.
     * @param title the title
     * @param greenSpace the green space
     * @param executionDate the execution date
     * @param status the status
     * @return the task
     */
    public Task updateTaskToCanceled(String title, String greenSpace, String executionDate, Status status) {
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpace.trim())) {
                if (task.getStatus() == Status.PLANNED || task.getStatus() == Status.POSTPONED) {

                    task.setStatus(Status.CANCELED);
                    Task copy = getCopy(task);

                   // task.removeAssignedTeam();
                   // task.removeAssignedVehicles();

                    return copy;
                }

            }
        }
        return null;
    }

    /**
     * Update task to done.
     * @param title the title
     * @param greenSpace the green space
     * @param executionDate the execution date
     * @param status the status
     * @return the task
     */
    public Task updateTaskToDone(String title, String greenSpace, String executionDate, Status status) {
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpace.trim())) {
                if (task.getStatus() == Status.PLANNED || task.getStatus() == Status.POSTPONED) {

                    task.setStatus(Status.DONE);
                    Task copy = getCopy(task);

                    System.out.println(copy.getTeamAssigned().getMembers().size());
                    System.out.println(copy.getTeamAssigned().getMembers().get(0).getName());

                    //task.removeAssignedTeam();
                    //task.removeAssignedVehicles();

                    System.out.println("----------//-------");
                    if(task.getVehiclesAssigned().isEmpty()){
                        System.out.println("no vehicles on task now");
                    }else{

                        System.out.println("task still have veicles: " + task.getVehiclesAssigned().size());
                    }

                    if(task.getTeamAssigned() == null){
                        System.out.println("no team on task now");
                    }else{
                        System.out.println("task still have team with size" + task.getTeamAssigned().getMembers().size());

                    }
                    System.out.println("----------//-------");


                    return copy;
                }

            }
        }
        return null;
    }

    /**
     * getCopy of a task
     * @param task the task
     * @return the task copy
     */
    private Task getCopy (Task task) {
        Task copy = new Task(task.getTitle().trim(),
                task.getDescription().trim(),
                task.getTaskType(),
                task.getGreenSpace(),
                task.getUrgency(),
                task.getExpectedDuration().getDays(),
                task.getExpectedDuration().getHours(),
                task.getExpectedDuration().getMinutes()
                );
        copy.setTeamAssigned(task.getTeamAssigned());
        copy.setVehiclesAssigned(task.getVehiclesAssigned());
        copy.setTaskWorkPeriod(task.getTaskWorkPeriod());
        return copy;
    }

    /**
     * Postpone task agenda.
     * @param selectedTask the selected task
     * @param newWorkPeriod the new work period
     * @return true if task is postponed, false otherwise
     */
    public boolean postponeTaskAgenda(Task selectedTask, WorkPeriod newWorkPeriod) {
        if(selectedTask != null){
            WorkPeriod oldWorkPeriod = selectedTask.getTaskWorkPeriod();
            for (Task task : agenda) {
                if (task.getTitle().trim().equalsIgnoreCase(selectedTask.getTitle().trim()) && task.getStatus() == selectedTask.getStatus() && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(selectedTask.getTaskWorkPeriod().getWorkStartDate().toString().trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(selectedTask.getGreenSpace().getName().trim())) {
                    Task newTask = task.clone();
                    Repositories.getInstance().getAgendaRepository().getAgenda().add(newTask);
                    task.setStatus(Status.POSTPONED);
                    newTask.setTaskWorkPeriod(newWorkPeriod);

                    System.out.println("Task postponed !");
                    return true;
                }
            }
        }
        System.out.println("Task does not exist");
        return false;
    }

    /**
     * Assign team to task agenda.
     * @param title the title
     * @param greenSpaceName the green space name
     * @param executionDate the execution date
     * @param status the status
     * @param team the team
     * @return the optional task
     */
    public Optional<Task> assignTeamToTaskAgenda(String title, String greenSpaceName, String executionDate, Status status, Team team) {
        Optional<Task> assignedTask = Optional.empty();
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpaceName.trim())) {
                System.out.println("-----------");
                System.out.println("Workperiod Task da agenda:" + task.getTaskWorkPeriod().getWorkStartDate() + "  " + task.getTaskWorkPeriod().getWorkStartHour() + "  " + task.getTaskWorkPeriod().getWorkStartMin() + "  " + task.getTaskWorkPeriod().getWorkEndDate() + "  " + task.getTaskWorkPeriod().getWorkEndHour() + "  " + task.getTaskWorkPeriod().getWorkEndMin());

                task.assignTeam(team);
                assignedTask = Optional.of(task);
                return assignedTask;

            }
        }

        return Optional.empty();
    }

    /**
     * Assign vehicles to task agenda.
     * @param title the title
     * @param greenSpaceName the green space name
     * @param executionDate the execution date
     * @param status the status
     * @param vehicles the vehicles
     * @return the optional task
     */
    public Optional<Task> assignVehiclesToTaskAgenda(String title, String greenSpaceName, String executionDate, Status status, List<Vehicle> vehicles) {
        Optional<Task> assignedTask;
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpaceName.trim())) {
                System.out.println("-----------");
                System.out.println("Workperiod Task da agenda:" + task.getTaskWorkPeriod().getWorkStartDate() + task.getTaskWorkPeriod().getWorkStartHour() + task.getTaskWorkPeriod().getWorkStartMin() + task.getTaskWorkPeriod().getWorkEndDate() + task.getTaskWorkPeriod().getWorkEndHour() + task.getTaskWorkPeriod().getWorkEndMin());

                task.assignVehicles(vehicles);
                assignedTask = Optional.of(task);
                return assignedTask;

            }
        }

        return Optional.empty();
    }

}

