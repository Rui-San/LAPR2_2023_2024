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
    private TaskDuration expectedDuration;
    private Team teamAssigned;
    private List<Vehicle> vehiclesAssigned;
    private WorkPeriod taskWorkPeriod;


    public Task(String title, String description, TaskType taskType, GreenSpace greenSpace, UrgencyType urgency, int days, int hours, int minutes) {
        setTitle(title);
        setDescription(description);
        setTaskType(taskType);
        this.status = null;
        setGreenSpace(greenSpace);
        setUrgency(urgency);
        this.expectedDuration = new TaskDuration(days, hours, minutes);
        this.taskWorkPeriod = null;
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

    public void assignTeam(Team team) {
        if (team.isAvailable(this.taskWorkPeriod)) {
            this.teamAssigned = team;
            team.addWorkPeriod(this.taskWorkPeriod);
        } else {
            throw new IllegalArgumentException("The team is not available for the given task period.");
        }
    }

    public void assignVehicles(List<Vehicle> vehicles) {
        boolean allAvailable = true;
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isAvailable(this.taskWorkPeriod)) {
                allAvailable = false;
            }
        }
        if (allAvailable){
            this.vehiclesAssigned.addAll(vehicles);
            vehicles.forEach(vehicle -> vehicle.addWorkPeriod(this.taskWorkPeriod));
        }else {
            throw new IllegalArgumentException("One or more vehicles are not available for the given task period.");
        }
    }

    private boolean validateStatus(Status status) {
        return status == Status.DONE || status == Status.CANCELED || status == Status.PENDING || status == Status.PLANNED || status == Status.POSTPONED || status == Status.PROCESSED;
    }

    public WorkPeriod getTaskWorkPeriod() {
        return taskWorkPeriod;
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

    /*
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

     */

    public TaskDuration getExpectedDuration() {
        return expectedDuration;
    }

    public void setTaskWorkPeriod(String executionDate, int workStartHour, int workStartMinutes, TaskDuration expectedDuration) {
        Date execDate = new Date(executionDate);
        if (execDate.isPastDate()) {
            throw new IllegalArgumentException("Execution date must be a future date.");
        }

        if (workStartHour < 0 || workStartMinutes < 0) {
            throw new IllegalArgumentException("Inputs cannot be negative integer");
        }

        if (workStartHour == 0 && workStartMinutes == 0) {
            throw new IllegalArgumentException("At least one field must be filled in");
        }

        this.taskWorkPeriod = new WorkPeriod(execDate, workStartHour, workStartMinutes, expectedDuration);

    }

    public void setExpectedDuration(int days, int hours, int minutes) {

        this.expectedDuration = new TaskDuration(days, hours,minutes);
    }
/*
    private void validateDuration(int expectedDuration) {

        if(expectedDuration < 0){

        long totalDays = expectedDuration.toDays();
        long remainingHours = (expectedDuration.toHours() % 24);
        long remainingMinutes = (expectedDuration.toMinutes() % 60);
        if (totalDays < 0 || remainingHours < 0 || remainingMinutes < 0) {
            throw new IllegalArgumentException("Days, hours, and minutes must be non-negative numbers.");
        }
        if (remainingHours >= 24 || remainingMinutes >= 60) {
            throw new IllegalArgumentException("Duration exceeds maximum limits.");
        }
        long recalculatedHours = totalDays * 24 + remainingHours;
        long recalculatedMinutes = recalculatedHours * 60 + remainingMinutes;
        if (recalculatedMinutes != expectedDuration.toMinutes()) {
            throw new IllegalArgumentException("Duration values are inconsistent.");
        }
        if (totalDays == 0 && recalculatedHours == 0 && remainingMinutes == 0) {
            throw new IllegalArgumentException("At least one of the following must be higher than zero: days, hours, minutes");
        }
    }

 */

    /*
        public Date getEndExecutionDate() {
            int totalMinutes = (int) expectedDuration.toMinutes();
            int totalDays = totalMinutes / (12 * 60);
            int remainingMinutes = totalMinutes % (12 * 60);
            Date endDate = executionDate.addDays(totalDays);
            endDate = endDate.addMinutes(remainingMinutes);
            return endDate;
        }
     */
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

/*
    public boolean isTeamAvailable(Team team, Date start, int startHour, int startMin, Duration expectedDuration) {
        return team.isAvailable(start, startHour, startMin, expectedDuration);
    }


 */

}