package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.AgendaTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;

import java.util.ArrayList;
import java.util.List;

public class AgendaMapper {

    public AgendaMapper(){

    }

    public static List<AgendaTaskDTO> toDTOlist(List<Task> agenda) {
        List<AgendaTaskDTO> agendaDTOS = new ArrayList<>();
        for (Task agendaTask : agenda) {
            agendaDTOS.add(toDTO(agendaTask));
        }
        return agendaDTOS;
    }

    public static AgendaTaskDTO toDTO(Task agendaTask) {

        return new AgendaTaskDTO(
                agendaTask.getTitle(),
                agendaTask.getDescription(),
                agendaTask.getTaskType(),
                agendaTask.getGreenSpace().getName(),
                agendaTask.getUrgency(),
                agendaTask.getExpectedDuration(),
                agendaTask.getExecutionDate().toString(),
                agendaTask.getStatus()
        );
    }
}
