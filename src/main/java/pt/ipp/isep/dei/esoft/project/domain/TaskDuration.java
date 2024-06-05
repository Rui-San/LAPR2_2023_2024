package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class TaskDuration implements Serializable {

    /**
     * reference to the number of days in the task duration
     */
    private int days;
    /**
     * reference to the number of hours in the task duration
     */
    private int hours;
    /**
     * reference to the number of minutes in the task duration
     */
    private int minutes;
    /**
     * reference to the total duration in minutes
     */
    private int totalDurationMinutes;

    /**
     * TaskDuration constructor
     * @param days  number of days
     * @param hours number of hours
     * @param minutes number of minutes
     */
    public TaskDuration(int days, int hours, int minutes) {
        setTaskDuration(days, hours, minutes);
    }

    /**
     * Get the number of days in the task duration
     * @return
     */
    public int getDays() {
        return days;
    }

    /**
     * Set the number of days in the task duration
     * @param days number of days
     */
    public void setDays(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Days must be a positive integer");
        }
        this.days = days;
    }

    /**
     * Get the number of hours in the task duration
     * @return number of hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Set the number of hours in the task duration
     * @param hours number of hours
     */
    public void setHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours must be a positive integer");
        }
        this.hours = hours;
    }

    /**
     * Get the number of minutes in the task duration
     * @return number of minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Set the number of minutes in the task duration
     * @param minutes number of minutes
     */
    public void setMinutes(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes must be a positive integer");
        }
        this.minutes = minutes;
    }

    /**
     * Set the task duration
     * @param days number of days
     * @param hours number of hours
     * @param minutes number of minutes
     */
    public void setTaskDuration(int days, int hours, int minutes) {
        validateTaskDuration(days, hours, minutes);
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.totalDurationMinutes = days * 8 * 60 + hours * 60 + minutes;

    }

    /**
     * Validate the task duration
     * @param days number of days
     * @param hours number of hours
     * @param minutes number of minutes
     */
    private void validateTaskDuration(int days, int hours, int minutes) {
        if (days == 0 && hours == 0 && minutes == 0) {
            throw new IllegalArgumentException("At least one field must be filled");
        }

        if (days < 0) {
            throw new IllegalArgumentException("Days must be a positive integer");
        }

        if (hours < 0) {
            throw new IllegalArgumentException("Hours must be a positive integer");
        }

        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes must be a positive integer");
        }
    }

    /**
     * Get the total duration in minutes
     * @return total duration in minutes
     */
    public int getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    /**
     *  converts the task duration to a string in format "X days, Y h, Z min"
     * @return string representation of the task duration
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (days > 0) {
            stringBuilder.append(days).append(days == 1 ? " day" : " days");
        }

        if (hours > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(hours).append(" h");
        }

        if (minutes > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(minutes).append(" min");
        }

        return stringBuilder.toString();
    }

}
