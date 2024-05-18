package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class ToDoTaskDTO {
    public String title;
    public String description;
    public Status status;
    public GreenSpace greenSpace;
    public UrgencyType urgency;
    public Duration expectedDuration;

    public ToDoTaskDTO(String title, String description, Status status, GreenSpace greenSpace, UrgencyType urgency, Duration expectedDuration) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.greenSpace = greenSpace;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
    }

}
