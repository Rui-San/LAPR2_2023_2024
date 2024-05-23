package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class ToDoTaskWithStatusDTO extends ToDoTaskDTO {
    public Status status;

    public ToDoTaskWithStatusDTO(String title, String description, TaskType taskType, String greenSpaceName, UrgencyType urgency, Duration expectedDuration, Status status) {
        super(title, description, taskType, greenSpaceName, urgency, expectedDuration);
        this.status = status;
    }
}
