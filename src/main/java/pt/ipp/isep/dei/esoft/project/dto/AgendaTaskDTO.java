package pt.ipp.isep.dei.esoft.project.dto;


import pt.ipp.isep.dei.esoft.project.domain.TaskDuration;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
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
    public int expectedDuration;
    public String workStartDate;
    public int workStartHour;
    public int workStartMinutes;
    public Status status;
    public int teamsAssigned;
    public int vehiclesAssigned;

    public AgendaTaskDTO(String title, String description, TaskType taskType, String greenSpace, UrgencyType urgency, TaskDuration expectedDuration, String executionDate, int workStartHour, int workStartMinutes, Status status, int teamsAssigned, int vehiclesAssigned){
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.greenSpaceName = greenSpace;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration.getTotalDurationMinutes();
        this.workStartDate = executionDate;
        this.workStartHour = workStartHour;
        this.workStartMinutes = workStartMinutes;
        this.status = status;
        this.teamsAssigned = teamsAssigned;
        this.vehiclesAssigned = vehiclesAssigned;
    }
}
