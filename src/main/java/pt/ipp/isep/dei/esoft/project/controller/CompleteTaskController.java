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

    private AgendaRepository agendaRepository;
    private TeamRepository teamRepository;
    private VehicleRepository vehicleRepository;

    public CompleteTaskController() {
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

    public Optional<Task> completeTaskAgenda(AgendaTaskDTO agendaTaskDTO) {

        System.out.println("Iniciando cancelamento de tarefa...");

        Task completedTask = agendaRepository.updateTaskToDone(agendaTaskDTO.title, agendaTaskDTO.greenSpaceName, agendaTaskDTO.workStartDate, agendaTaskDTO.status);

        if (completedTask != null) {
            System.out.println("Tarefa encontrada e status atualizado para DONE.");

            if(completedTask.getTeamAssigned() != null){
                System.out.println("tamanho da equipa: " +completedTask.getTeamAssigned().getMembers().size());
                System.out.println("Removendo work period da equipe.");
                teamRepository.removeWorkPeriodFromTeam(completedTask, completedTask.getTaskWorkPeriod());
            }
            if(!completedTask.getVehiclesAssigned().isEmpty()){
                System.out.println("Numero de veiculos dentro da task: " + completedTask.getVehiclesAssigned().size());
                System.out.println("Removendo work period dos veículos.");
                vehicleRepository.removeWorkPeriodFromVehicle(completedTask, completedTask.getTaskWorkPeriod());
            }

            return Optional.of(completedTask);
        }else{
            System.out.println("Tarefa não encontrada ou não pode ser completada.");
            return Optional.empty();
        }
    }
}
