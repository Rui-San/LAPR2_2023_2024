package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgendaRepository implements Serializable {
    List<Task> agenda;

    private static final Status AGENDA_DEFAULT_STATUS = Status.PLANNED;

    public AgendaRepository() {
        this.agenda = new ArrayList<>();
    }

    public List<Task> getAgenda() {
        return agenda;
    }

    public List<Task> getManagerSpecificAgenda(String managerEmail) {
        List<Task> managerSpecificAgenda = new ArrayList<>();

        for (Task task : agenda) {
            if (task.getGreenSpace().getManager().trim().equalsIgnoreCase(managerEmail.trim())) {
                managerSpecificAgenda.add(task);
            }
        }

        return managerSpecificAgenda;
    }

    public Task getTask(AgendaTaskDTO taskDTO) {
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(taskDTO.title.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(taskDTO.greenSpaceName.trim()) && task.getStatus().toString().trim().equalsIgnoreCase(taskDTO.status.toString())) {
                return task;
            }
        }
        return null;
    }

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


    public Optional<Task> registerTaskAgenda(Task task, String executionDate, int workStartingHours, int workStartingMinutes) {

        Optional<Task> addedTask = add(task, executionDate, workStartingHours, workStartingMinutes);

        return addedTask;
    }


    public Task updateTaskToCanceled(String title, String greenSpace, String executionDate, Status status) {
        for (Task task : agenda) {
            if (task.getTitle().trim().equalsIgnoreCase(title.trim()) && task.getStatus() == status && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(executionDate.trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(greenSpace.trim())) {
                if (task.getStatus() == Status.PLANNED || task.getStatus() == Status.POSTPONED) {

                    task.setStatus(Status.CANCELED);
                    Task copy = getCopy(task);

                    System.out.println(copy.getTeamAssigned().getMembers().size());
                    System.out.println(copy.getTeamAssigned().getMembers().get(0).getName());

                   // task.removeAssignedTeam();
                   // task.removeAssignedVehicles();

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

    public boolean postponeTaskAgenda(Task selectedTask, WorkPeriod newWorkPeriod) {
        if(selectedTask != null){
            WorkPeriod oldWorkPeriod = selectedTask.getTaskWorkPeriod();
            for (Task task : agenda) {
                if (task.getTitle().trim().equalsIgnoreCase(selectedTask.getTitle().trim()) && task.getStatus() == selectedTask.getStatus() && task.getTaskWorkPeriod().getWorkStartDate().toString().trim().equalsIgnoreCase(selectedTask.getTaskWorkPeriod().getWorkStartDate().toString().trim()) && task.getGreenSpace().getName().trim().equalsIgnoreCase(selectedTask.getGreenSpace().getName().trim())) {
                    task.setTaskWorkPeriod(newWorkPeriod);

                    System.out.println("Workperiod of the task postponed !");
                    return true;
                }
            }
        }
        System.out.println("Task does not exist");
        return false;
    }

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

