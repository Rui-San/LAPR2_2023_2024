package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.CollaboratorDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class AgendaController {

    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {

            List<CollaboratorDTO> collaboratorDTOList = new ArrayList<>();
            if (agendaTask.getTeamAssigned() != null && agendaTask.getTeamAssigned().getMembers() != null) {
                collaboratorDTOList = CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers());
            }

            List<VehicleDTO> vehicleDTOList = new ArrayList<>();
            if (agendaTask.getVehiclesAssigned() != null) {
                vehicleDTOList = VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned());
            }

            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask, TeamMapper.toDTO(collaboratorDTOList), vehicleDTOList));
        }
        return managerSpecificAgendaDTO;
    }

    public List<AgendaTaskDTO> getCollbaboratorSpecificAgenda() {
        String collaboratorEmail = Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getCollbaboratorSpecificAgenda(collaboratorEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask,
                    TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers())),
                    VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned())));
        }
        return managerSpecificAgendaDTO;
    }

}
