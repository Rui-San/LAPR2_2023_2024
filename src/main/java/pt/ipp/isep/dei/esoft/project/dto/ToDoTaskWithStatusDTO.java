package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.TaskDuration;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class ToDoTaskWithStatusDTO extends ToDoTaskDTO {
    public Status status;

    public ToDoTaskWithStatusDTO(String title, String description, TaskType taskType, String greenSpaceName, UrgencyType urgency, TaskDuration expectedDuration, Status status) {
        super(title, description, taskType, greenSpaceName, urgency, expectedDuration.getDays(), expectedDuration.getHours(), expectedDuration.getMinutes());
        this.status = status;
    }

    public String expectedDurationToString(){
        return super.expectedDurationToString();
    }
}
