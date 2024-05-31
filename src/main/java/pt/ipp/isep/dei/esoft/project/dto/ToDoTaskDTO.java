package pt.ipp.isep.dei.esoft.project.dto;

import pt.ipp.isep.dei.esoft.project.domain.TaskDuration;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;

public class ToDoTaskDTO {
    public String title;
    public String description;
    public TaskType taskType;
    public String greenSpaceName;
    public UrgencyType urgency;
    public int days;
    public int hours;
    public int minutes;

    public ToDoTaskDTO(String title, String description, TaskType taskType, String greenSpace, UrgencyType urgency, int days, int hours, int minutes) {
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.greenSpaceName = greenSpace;
        this.urgency = urgency;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;

    }

    public String expectedDurationToString() {
        String expectDurationString = days + "d " + hours + "h " + minutes + "m";
        return expectDurationString;
    }
}
