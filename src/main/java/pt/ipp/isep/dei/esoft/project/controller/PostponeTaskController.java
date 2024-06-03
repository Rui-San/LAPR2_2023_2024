package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Vehicle;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.tools.Status;

import java.util.List;

public class PostponeTaskController {

    private AgendaRepository agendaRepository;
    private VehicleRepository vehicleRepository;
    private TeamRepository teamRepository;
    private ToDoRepository toDoRepository;

    public PostponeTaskController() {
        getAgendaRepository();
        getVehicleRepository();
        getTeamRepository();
    }

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    private VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

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
