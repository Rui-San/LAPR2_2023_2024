package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.tools.Status;
import pt.ipp.isep.dei.esoft.project.tools.TaskType;
import pt.ipp.isep.dei.esoft.project.tools.UrgencyType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {

    /**
     * Title of the task
     */
    private String title;
    /**
     * Description of the task
     */
    private String description;
    /**
     * Type of the task
     */
    private TaskType taskType;
    /**
     * Status of the task
     */
    private Status status;
    /**
     * GreenSpace to which the task is associated
     */
    private GreenSpace greenSpace;
    /**
     * Urgency of the task
     */
    private UrgencyType urgency;
    /**
     * Expected duration of the task
     */
    private TaskDuration expectedDuration;
    /**
     * Team assigned to the task
     */
    private Team teamAssigned;
    /**
     * List of vehicles assigned to the task
     */
    private List<Vehicle> vehiclesAssigned;
    /**
     * The work period of the task
     */
    private WorkPeriod taskWorkPeriod;

    /**
     * Instantiates a new Task with only a few attributes.
     * @param title the title
     * @param description the description
     * @param taskType the task type
     * @param greenSpace the green space
     * @param urgency the urgency
     * @param days the days
     * @param hours the hours
     * @param minutes the minutes
     */
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

    /**
     * Gets the task title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title
     * @param title the title
     */
    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    /**
     * Validates the title
     * @param title the title
     */
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty or null.");
        }
        if (!title.matches("[a-zA-Z\\s-\\p{L}]+")) {
            throw new IllegalArgumentException("Title cannot contain Special characters.");
        }
    }

    /**
     * Gets the task description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the task type
     * @return the task type
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Sets the task description
     * @param description the description
     */
    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    /**
     * Validates the description
     * @param description the description
     */
    private void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description can't be empty or null.");
        }
    }

    /**
     * Gets the task status
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the task status
     * @param status the status
     */
    public void setStatus(Status status) {
        if (validateStatus(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Status is not valid.");
        }
    }

    /**
     * Sets the task type
     * @param taskType the task type
     */
    public void setTaskType(TaskType taskType) {
        if (validateTaskType(taskType)) {
            this.taskType = taskType;
        } else {
            throw new IllegalArgumentException("Task type is not valid.");
        }
    }

    /**
     * Validates the task type
     * @param taskType the task type
     * @return true if the task type is valid, false otherwise
     */
    private boolean validateTaskType(TaskType taskType) {
        return taskType == TaskType.REGULAR || taskType == TaskType.OCCASIONAL;
    }

    /**
     * Assigns a team to the task
     * @param team the team
     */
    public void assignTeam(Team team) {
        if(this.getTeamAssigned() != null){
            throw new IllegalArgumentException("This task already have a team assigned to.");
        }
        if (team.isAvailable(this.taskWorkPeriod)) {
            this.teamAssigned = team;
            team.addWorkPeriod(this.taskWorkPeriod);
        } else {
            throw new IllegalArgumentException("The team is not available for the given task period.");
        }
    }

    /**
     * Assigns vehicles to the task
     * @param vehicles the vehicles
     */
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

    /**
     * Validates the status
     * @param status the status
     * @return true if the status is valid, false otherwise
     */
    private boolean validateStatus(Status status) {
        return status == Status.DONE || status == Status.CANCELED || status == Status.PENDING || status == Status.PLANNED || status == Status.POSTPONED || status == Status.PROCESSED;
    }

    /**
     * Gets the task work period
     * @return the work period
     */
    public WorkPeriod getTaskWorkPeriod() {
        return taskWorkPeriod;
    }

    /**
     * Gets the green space
     * @return the green space
     */
    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    /**
     * Sets the green space
     * @param greenSpace the green space
     */
    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    /**
     * Gets the team assigned to the task
     * @return the team assigned
     */
    public Team getTeamAssigned() {
        return teamAssigned;
    }

    /**
     * Sets the team assigned to the task
     * @param teamAssigned the team assigned
     */
    public void setTeamAssigned(Team teamAssigned) {
        this.teamAssigned = teamAssigned;
    }

    /**
     * Gets the vehicles assigned to the task
     * @return the vehicles assigned
     */
    public List<Vehicle> getVehiclesAssigned() {
        return vehiclesAssigned;
    }

    /**
     * Sets the vehicles assigned to the task
     * @param vehiclesAssigned the vehicles to be assigned
     */
    public void setVehiclesAssigned(List<Vehicle> vehiclesAssigned) {
        this.vehiclesAssigned = vehiclesAssigned;
    }

    /**
     * Gets the expected duration of the task
     * @return the expected duration
     */
    public TaskDuration getExpectedDuration() {
        return expectedDuration;
    }

    /**
     * Sets the task work period
     * @param executionDate the execution date
     * @param workStartHour the work start hour
     * @param workStartMinutes the work start minutes
     * @param expectedDuration the expected duration
     */
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

    /**
     * Sets the task work period
     * @param workPeriod the work period
     */
    public void setTaskWorkPeriod(WorkPeriod workPeriod){
        this.taskWorkPeriod = workPeriod;
    }

    /**
     * Gets the urgency of the task
     * @return the urgency
     */
    public UrgencyType getUrgency() {
        return urgency;
    }

    /**
     * Sets the urgency of the task
     * @param urgency the urgency
     */
    public void setUrgency(UrgencyType urgency) {
        if (validateUrgency(urgency)) {
            this.urgency = urgency;
        } else {
            throw new IllegalArgumentException("Urgency option is not valid.");
        }
    }

    /**
     * Validates the urgency
     * @param urgency the urgency
     * @return true if the urgency is valid, false otherwise
     */
    private boolean validateUrgency(UrgencyType urgency) {
        return urgency == UrgencyType.HIGH || urgency == UrgencyType.MEDIUM || urgency == UrgencyType.LOW;
    }

    /**
     * Returns a clone of the task
     * @return the clone
     */
    public Task clone(){
        Task newTask = new Task(this.title, this.description, this.taskType, this.greenSpace, this.urgency, this.expectedDuration.getDays(), this.expectedDuration.getHours(), this.expectedDuration.getMinutes());
        newTask.setStatus(this.status);
        newTask.setTaskWorkPeriod(this.taskWorkPeriod);
        newTask.setTeamAssigned(this.teamAssigned);
        newTask.setVehiclesAssigned(this.vehiclesAssigned);
        return newTask;
    }

}