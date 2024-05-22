package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Task {

    private String title;
    private String description;
    private TaskType taskType;
    private Status status;
    private GreenSpace greenSpace;
    private UrgencyType urgency;
    private Duration expectedDuration;
    private Date executionDate;
    private Team teamAssigned;
    private List<Vehicle> vehiclesAssigned;

    public Task(String title, String description, TaskType taskType, GreenSpace greenSpace, UrgencyType urgency, Duration expectedDuration) {
        setTitle(title);
        setDescription(description);
        setTaskType(taskType);
        this.status = null;
        setGreenSpace(greenSpace);
        setUrgency(urgency);
        setExpectedDuration(expectedDuration);
        this.executionDate = null;
        this.teamAssigned = null;
        this.vehiclesAssigned = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty or null.");
        }
        if (!title.matches("[a-zA-Z\\s-\\p{L}]+")) {
            throw new IllegalArgumentException("Title cannot contain Special characters.");
        }
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be empty or null.");
        }
        if (!description.matches("[a-zA-Z\\s-\\p{L}]+")) {
            throw new IllegalArgumentException("Description cannot contain Special characters.");
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (validateStatus(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status is not valid.");
        }
    }

    private void setTaskType(TaskType taskType) {
        if (validateTaskType(taskType)) {
            this.taskType = taskType;
        } else {
            throw new IllegalArgumentException("Task type is not valid.");
        }
    }

    private boolean validateTaskType(TaskType taskType) {
        return taskType == TaskType.REGULAR || taskType == TaskType.OCCASIONAL;
    }

    private boolean validateStatus(Status status) {
        return status == Status.DONE || status == Status.CANCELED || status == Status.PENDING || status == Status.PLANNED || status == Status.POSTPONED || status == Status.PROCESSED;
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

    public void setExecutionDate(String executionDate) {
        Date date = new Date(executionDate);

        if (date.isPastDate()) {
            throw new IllegalArgumentException("Execution date must be a future date.");
        } else {
            this.executionDate = date;
        }
    }

    public Duration getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(Duration expectedDuration) {
        validateDuration(expectedDuration);

        this.expectedDuration = expectedDuration;
    }

    private void validateDuration(Duration expectedDuration) {
        long totalHours = expectedDuration.toHours();
        long totalDays = expectedDuration.toDays();
        long remainingHours = totalHours % 24;

        if (totalDays < 0 || remainingHours < 0) {
            throw new IllegalArgumentException("Days and hours must be non-negative numbers.");
        }
        if (totalDays == 0 && remainingHours == 0) {
            throw new IllegalArgumentException("At least one of the following must be higher than zero: days, hours");
        }
        if (remainingHours >= 24) {
            throw new IllegalArgumentException("Hours must be less than 24.");
        }

    }

    public UrgencyType getUrgency() {
        return urgency;
    }

    public void setUrgency(UrgencyType urgency) {
        if (validateUrgency(urgency)) {
            this.urgency = urgency;
        } else {
            throw new IllegalArgumentException("Urgency option is not valid.");
        }
    }

    private boolean validateUrgency(UrgencyType urgency) {
        return urgency == UrgencyType.HIGH || urgency == UrgencyType.MEDIUM || urgency == UrgencyType.LOW;
    }

    public void assignVehicles(List<Vehicle> selectedVehicles) {
        this.vehiclesAssigned.addAll(selectedVehicles);
    }


}
