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

public class CancelEntryAgendaController {

    private AgendaRepository agendaRepository;
    private TeamRepository teamRepository;
    private VehicleRepository vehicleRepository;

    public CancelEntryAgendaController() {
        getAgendaRepository();
        getTeamRepository();
        getVehicleRepository();
    }

    private VehicleRepository getVehicleRepository() {
        if(vehicleRepository == null){
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

    private AgendaRepository getAgendaRepository() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaRepository();
        }
        return agendaRepository;
    }

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

    public Optional<Task> cancelTaskAgenda(AgendaTaskDTO agendaTaskDTO) {

        Optional<Task> canceledTask = agendaRepository.updateTaskToCanceled(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status);
        if (canceledTask.isPresent()) {
            if(canceledTask.get().getTeamAssigned() != null){
                teamRepository.removeWorkPeriod(canceledTask.get());
            }
            if(!canceledTask.get().getVehiclesAssigned().isEmpty()){
                vehicleRepository.removeWorkPeriod(canceledTask.get());
            }
            return canceledTask;
        }else{
            return Optional.empty();
        }
    }
}
