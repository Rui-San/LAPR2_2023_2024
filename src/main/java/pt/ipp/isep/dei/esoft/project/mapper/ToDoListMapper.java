package pt.ipp.isep.dei.esoft.project.mapper;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.dto.ToDoTaskDTO;

import java.util.ArrayList;
import java.util.List;

public class ToDoListMapper {

    public ToDoListMapper() {

    }

    public static List<ToDoTaskDTO> toDTO(List<Task> toDoList) {
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
                toDoTask.getStatus(),
                toDoTask.getGreenSpace(),
                toDoTask.getUrgency(),
                toDoTask.getExpectedDuration()
        );
    }
}
