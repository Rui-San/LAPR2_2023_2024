package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;

import java.util.ArrayList;
import java.util.List;

public class AgendaMapper {

    public AgendaMapper(){

    }

    public static List<AgendaTaskDTO> toDTOlist(List<Task> agenda, TeamDTO teamDTO, List<VehicleDTO> vehicleDTOList) {
        List<AgendaTaskDTO> agendaDTOS = new ArrayList<>();
        for (Task agendaTask : agenda) {
            agendaDTOS.add(toDTO(agendaTask, teamDTO, vehicleDTOList));
        }
        return agendaDTOS;
    }

    public static AgendaTaskDTO toDTO(Task agendaTask, TeamDTO teamDTO, List<VehicleDTO> vehicleDTOList) {
        String isTeamAssigned = "No";
        if(agendaTask.getTeamAssigned() != null){
            isTeamAssigned = "Yes";
        }

        return new AgendaTaskDTO(
                agendaTask.getTitle(),
                agendaTask.getDescription(),
                agendaTask.getTaskType(),
                agendaTask.getGreenSpace().getName(),
                agendaTask.getUrgency(),
                agendaTask.getExpectedDuration(),
                agendaTask.getTaskWorkPeriod().getWorkStartDate().toString(),
                agendaTask.getTaskWorkPeriod().getWorkStartHour(),
                agendaTask.getTaskWorkPeriod().getWorkStartMin(),
                agendaTask.getStatus(),
                isTeamAssigned,
                agendaTask.getVehiclesAssigned().size(),
                teamDTO,
                vehicleDTOList
        );
    }
}
