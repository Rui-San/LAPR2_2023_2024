package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class ToDoTaskDTO {
    public String title;
    public String description;
    public TaskType taskType;
    public String greenSpaceName;
    public UrgencyType urgency;
    public Duration expectedDuration;

    public ToDoTaskDTO(String title, String description, TaskType taskType, String greenSpace, UrgencyType urgency, Duration expectedDuration) {
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.greenSpaceName = greenSpace;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
    }
}
