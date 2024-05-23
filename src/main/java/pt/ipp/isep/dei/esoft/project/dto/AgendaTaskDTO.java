package pt.ipp.isep.dei.esoft.project.dto;


import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class AgendaTaskDTO {

    public String title;
    public String description;
    public TaskType taskType;
    public String greenSpaceName;
    public UrgencyType urgency;
    public Duration expectedDuration;
    public String executionDate;
    public Status status;

    public AgendaTaskDTO(String title, String description, TaskType taskType, String greenSpace, UrgencyType urgency, Duration expectedDuration, String executionDate, Status status) {
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.greenSpaceName = greenSpace;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
        this.executionDate = executionDate;
        this.status = status;
    }
}
