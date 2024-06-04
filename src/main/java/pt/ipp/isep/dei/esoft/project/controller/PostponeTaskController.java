package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class PostponeTaskController {

    /**
     * The Agenda Repository.
     */
    private AgendaRepository agendaRepository;
    /**
     * The Vehicle Repository.
     */
    private VehicleRepository vehicleRepository;
    /**
     * The Team Repository.
     */
    private TeamRepository teamRepository;

    /**
     * Instantiates a new Postpone Task Controller.
     */
    public PostponeTaskController() {
        getAgendaRepository();
        getVehicleRepository();
        getTeamRepository();
    }

    /**
     * Gets the agenda repository.
     * @return the agenda repository
     */
    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    /**
     * Gets the vehicles repository.
     * @return the vehicles repository
     */
    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Gets the team repository.
     * @return the team repository
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Postpones a task in the agenda.
     * @param selectedTask the selected task
     * @param selectedDate the date to be postponed to
     * @param workStartingHours the new work starting hours
     * @param workStartingMinutes the new work starting minutes
     * @return true if the task is postponed, false otherwise
     */
    public boolean postponeTask(AgendaTaskDTO selectedTask, String selectedDate,int workStartingHours,int workStartingMinutes){

        Task task = getAgendaRepository().getTask(selectedTask);
        WorkPeriod newWorkPeriod = new WorkPeriod(new Date(selectedDate),workStartingHours,workStartingMinutes,task.getExpectedDuration());
        WorkPeriod oldWorkPeriod = task.getTaskWorkPeriod();
        boolean isTeamAvailableInNewPeriod = true;
        boolean areVehiclesAvailableInNewPeriod = true;
        boolean isTaskPostponed = false;

        if(task.getTeamAssigned() != null){
            isTeamAvailableInNewPeriod = task.getTeamAssigned().isAvailable(newWorkPeriod, oldWorkPeriod);
        }
        if(task.getVehiclesAssigned() != null){
            for (Vehicle vehicle : task.getVehiclesAssigned()) {
                if(areVehiclesAvailableInNewPeriod){ areVehiclesAvailableInNewPeriod = vehicle.isAvailable(newWorkPeriod, oldWorkPeriod); }
            }
        }
        if(isTeamAvailableInNewPeriod && areVehiclesAvailableInNewPeriod) {
            isTaskPostponed = getAgendaRepository().postponeTaskAgenda(task, newWorkPeriod);
            if (isTaskPostponed) {
                if (task.getTeamAssigned() != null) {
                    teamRepository.postponeWorkPeriods(task, oldWorkPeriod, newWorkPeriod);
                }
                if (task.getVehiclesAssigned() != null) {
                    vehicleRepository.postponeWorkPeriods(task, oldWorkPeriod, newWorkPeriod);
                }
            }
        }
        return isTaskPostponed;
    }

}
