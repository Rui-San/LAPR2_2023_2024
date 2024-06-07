package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;
import pt.ipp.isep.dei.esoft.project.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompleteTaskController {

    /**
     * Agenda Repository
     */
    private AgendaRepository agendaRepository;
    /**
     * Team Repository
     */
    private TeamRepository teamRepository;
    /**
     * Vehicle Repository
     */
    private VehicleRepository vehicleRepository;

    /**
     * Instantiates a new Complete Task Controller.
     */
    public CompleteTaskController() {
        getAgendaRepository();
        getTeamRepository();
        getVehicleRepository();
    }

    /**
     * Gets the Vehicle Repository.
     * @return Vehicle Repository
     */
    private VehicleRepository getVehicleRepository() {
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Gets the Team Repository.
     * @return Team Repository
     */
    private TeamRepository getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
        return teamRepository;
    }

    /**
     * Gets the Agenda Repository.
     * @return Agenda Repository
     */
    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

    /**
     * Gets the list of AgendaTaskDTO for the Manager.
     * @return List of AgendaTaskDTO
     */
    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask,
                    TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers())),
                    VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned())));
        }
        return managerSpecificAgendaDTO;
    }

    /**
     * Completes a Task in the Agenda.
     * @param agendaTaskDTO the task to set as done
     * @return completed Task
     */
    public Optional<Task> completeTaskAgenda(AgendaTaskDTO agendaTaskDTO) {

        Task completedTask = agendaRepository.updateTaskToDone(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status);

        if (completedTask != null) {

            if(completedTask.getTeamAssigned() != null){
                teamRepository.removeWorkPeriodFromTeam(completedTask, completedTask.getTaskWorkPeriod());
            }
            if(!completedTask.getVehiclesAssigned().isEmpty()){
                vehicleRepository.removeWorkPeriodFromVehicle(completedTask, completedTask.getTaskWorkPeriod());
            }

            return Optional.of(completedTask);
        }else{
            return Optional.empty();
        }
    }
}
