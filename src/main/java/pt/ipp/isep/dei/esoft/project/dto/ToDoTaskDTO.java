package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class ToDoTaskDTO {
    public String title;
    public String description;
    public Status status;
    public String greenSpaceName;
    public UrgencyType urgency;
    public Duration expectedDuration;

    public ToDoTaskDTO(String title, String description, Status status, String greenSpace, UrgencyType urgency, Duration expectedDuration) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.greenSpaceName = greenSpace;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
    }

}
