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
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CancelEntryAgendaController {

    /**
     * the agenda repository
     */
    private AgendaRepository agendaRepository;
    /**
     * the team repository
     */
    private TeamRepository teamRepository;
    /**
     * the vehicle repository
     */
    private VehicleRepository vehicleRepository;

    /**
     * Instantiates a new Cancel entry agenda controller.
     */
    public CancelEntryAgendaController() {
        getAgendaRepository();
        getTeamRepository();
        getVehicleRepository();
    }

    /**
     * Gets vehicle repository.
     * @return the vehicle repository
     */
    private VehicleRepository getVehicleRepository() {
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();
            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    /**
     * Gets team repository.
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
     * Gets agenda repository.
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
     * Gets the list of tasksDTO of a manager
     * @return the list of tasksDTO of a manager
     */
    /*
    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask,
                    TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers())),
                    VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned())));
        }
        return managerSpecificAgendaDTO;
    }
    */

    /**
     * Cancels a task from the agenda
     * @param agendaTaskDTO the task to cancel
     * @return the canceled task
     */
    public Optional<Task> cancelTaskAgenda(AgendaTaskDTO agendaTaskDTO) {

        Task canceledTask = agendaRepository.updateTaskToCanceled(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status);

        if (canceledTask != null) {
            if(canceledTask.getTeamAssigned() != null){
                teamRepository.removeWorkPeriodFromTeam(canceledTask, canceledTask.getTaskWorkPeriod());
            }
            if(!canceledTask.getVehiclesAssigned().isEmpty()){
                vehicleRepository.removeWorkPeriodFromVehicle(canceledTask, canceledTask.getTaskWorkPeriod());
            }

            return Optional.of(canceledTask);
        }else{
            return Optional.empty();
        }
    }
}
