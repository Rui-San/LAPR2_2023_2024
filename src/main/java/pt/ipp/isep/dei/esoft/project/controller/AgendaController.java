package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Task;

import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;
import pt.ipp.isep.dei.esoft.project.mapper.AgendaMapper;
import pt.ipp.isep.dei.esoft.project.mapper.CollaboratorMapper;
import pt.ipp.isep.dei.esoft.project.mapper.TeamMapper;
import pt.ipp.isep.dei.esoft.project.mapper.VehicleMapper;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.session.ApplicationSession;

import java.util.ArrayList;
import java.util.List;

public class AgendaController {

    /**
     * Gets the list of AgendaTaskDTOs for the manager.
     * @return List of AgendaTaskDTOs
     */
    public List<AgendaTaskDTO> getAgendaTaskDTOManagerList() {
        String managerEmail = ApplicationSession.getInstance().getCurrentSession().getUserId().getEmail();
        List<Task> agendaTaskList = Repositories.getInstance().getAgendaRepository().getManagerSpecificAgenda(managerEmail);
        List<AgendaTaskDTO> managerSpecificAgendaDTO = new ArrayList<>();

        for (Task agendaTask : agendaTaskList) {
            TeamDTO teamDTO = null;
            List<VehicleDTO> vehicleDTOList = null;
            if(agendaTask.getTeamAssigned() != null){
                teamDTO = TeamMapper.toDTO(CollaboratorMapper.toDTOlist(agendaTask.getTeamAssigned().getMembers()));
            }
            if(agendaTask.getVehiclesAssigned() != null){
                vehicleDTOList = VehicleMapper.toDTOList(agendaTask.getVehiclesAssigned());
            }
            managerSpecificAgendaDTO.add(AgendaMapper.toDTO(agendaTask, teamDTO, vehicleDTOList));
        }
        return managerSpecificAgendaDTO;
    }

}
