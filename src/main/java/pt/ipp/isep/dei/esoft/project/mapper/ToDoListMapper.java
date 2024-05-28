package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskWithStatusDTO;

import java.util.ArrayList;
import java.util.List;

public class ToDoListMapper {

    public ToDoListMapper() {

    }

    public static List<ToDoTaskDTO> toDTOlist(List<Task> toDoList) {
        List<ToDoTaskDTO> toDoTaskDTOS = new ArrayList<>();
        for (Task toDoTask : toDoList) {
            toDoTaskDTOS.add(toDTO(toDoTask));
        }
        return toDoTaskDTOS;
    }

    public static ToDoTaskDTO toDTO(Task toDoTask) {

        return new ToDoTaskDTO(
                toDoTask.getTitle(),
                toDoTask.getDescription(),
                toDoTask.getTaskType(),
                toDoTask.getGreenSpace().getName(),
                toDoTask.getUrgency(),
                toDoTask.getExpectedDuration().getDays(),
                toDoTask.getExpectedDuration().getHours(),
                toDoTask.getExpectedDuration().getMinutes()
        );
    }


    public static List<ToDoTaskWithStatusDTO> toDTOWithStatusList(List<Task> toDoList) {
        List<ToDoTaskWithStatusDTO> toDoTaskWithStatusDTOS = new ArrayList<>();
        for (Task toDoTask : toDoList) {
            toDoTaskWithStatusDTOS.add(toDTOWithStatus(toDoTask));
        }
        return toDoTaskWithStatusDTOS;
    }


    public static ToDoTaskWithStatusDTO toDTOWithStatus(Task toDoTask) {
        return new ToDoTaskWithStatusDTO(
                toDoTask.getTitle(),
                toDoTask.getDescription(),
                toDoTask.getTaskType(),
                toDoTask.getGreenSpace().getName(),
                toDoTask.getUrgency(),
                toDoTask.getExpectedDuration(),
                toDoTask.getStatus()
        );
    }
    public static Task toTask(ToDoTaskDTO toDoTaskDTO, GreenSpace greenSpace) {
        return new Task(
                toDoTaskDTO.title,
                toDoTaskDTO.description,
                toDoTaskDTO.taskType,
                greenSpace,
                toDoTaskDTO.urgency,
                toDoTaskDTO.days,
                toDoTaskDTO.hours,
                toDoTaskDTO.minutes
        );
    }


}
