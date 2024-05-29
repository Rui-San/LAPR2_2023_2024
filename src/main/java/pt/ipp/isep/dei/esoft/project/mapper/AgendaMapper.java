package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.TeamDTO;
import pt.ipp.isep.dei.esoft.project.dto.VehicleDTO;

import java.util.ArrayList;
import java.util.List;

public class AgendaMapper {

    public AgendaMapper() {

    }

    public static List<AgendaTaskDTO> toDTOlist(List<Task> agenda) {
        List<AgendaTaskDTO> agendaTaskDTOList = new ArrayList<>();
        for (Task task : agenda) {
            TeamDTO teamDTO = TeamMapper.toDTO(CollaboratorMapper.toDTOlist(task.getTeamAssigned().getMembers()));
            List<VehicleDTO> vehicleDTOList = VehicleMapper.toDTOList(task.getVehiclesAssigned());
            agendaTaskDTOList.add(toDTO(task, teamDTO, vehicleDTOList));
        }
        return agendaTaskDTOList;
    }

    public static AgendaTaskDTO toDTO(Task agendaTask, TeamDTO teamDTO, List<VehicleDTO> vehicleDTOList) {
        String isTeamAssigned = "No";
        if (agendaTask.getTeamAssigned() != null) {
            isTeamAssigned = "Yes";
        }


        return new AgendaTaskDTO(
                agendaTask.getTitle(),
                agendaTask.getDescription(),
                agendaTask.getTaskType(),
                agendaTask.getGreenSpace().getName(),
                agendaTask.getUrgency(),
                agendaTask.getExpectedDuration(),
                agendaTask.getTaskWorkPeriod().getWorkStartDate().toString().trim(),
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
