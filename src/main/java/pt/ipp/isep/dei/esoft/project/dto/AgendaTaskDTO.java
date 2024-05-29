package pt.ipp.isep.dei.esoft.project.dto;


import pt.ipp.isep.dei.esoft.project.domain.TaskDuration;
import pt.ipp.isep.dei.esoft.project.domain.WorkPeriod;
import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;
import java.util.List;

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
    public String isTeamAssigned;
    public int vehiclesAssigned;
    public TeamDTO teamDTO;

    public List<VehicleDTO> vehicleDTOList;

    public AgendaTaskDTO(String title, String description, TaskType taskType, String greenSpace, UrgencyType urgency, TaskDuration expectedDuration, String executionDate, int workStartHour, int workStartMinutes, Status status, String isTeamAssigned, int vehiclesAssigned, TeamDTO teamDTO, List<VehicleDTO> vehicleDTOList) {
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
        this.isTeamAssigned = isTeamAssigned;
        this.vehiclesAssigned = vehiclesAssigned;
        this.teamDTO = teamDTO;
        this.vehicleDTOList = vehicleDTOList;
    }
}
