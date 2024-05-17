package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private String title;
    private String description;
    private Status status;
    private GreenSpace greenSpace;
    private UrgencyType urgency;
    private Duration expectedDuration;
    private Date executionDate;
    private Team teamAssigned;
    private List<Vehicle> vehiclesAssigned;

    public Task(String title, String description, Status status, GreenSpace greenSpace, UrgencyType urgency, Duration expectedDuration) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.greenSpace = greenSpace;
        this.urgency = urgency;
        this.expectedDuration = expectedDuration;
        this.executionDate = null;
        this.teamAssigned = null;
        this.vehiclesAssigned = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public Team getTeamAssigned() {
        return teamAssigned;
    }

    public void setTeamAssigned(Team teamAssigned) {
        this.teamAssigned = teamAssigned;
    }

    public List<Vehicle> getVehiclesAssigned() {
        return vehiclesAssigned;
    }

    public void setVehiclesAssigned(List<Vehicle> vehiclesAssigned) {
        this.vehiclesAssigned = vehiclesAssigned;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public Duration getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(Duration expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public UrgencyType getUrgency() {
        return urgency;
    }

    public void setUrgency(UrgencyType urgency) {
        this.urgency = urgency;
    }


}
