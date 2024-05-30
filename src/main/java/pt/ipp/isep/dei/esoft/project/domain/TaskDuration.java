package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;

public class TaskDuration implements Serializable {
    private int days;
    private int hours;
    private int minutes;

    public TaskDuration(int days, int hours, int minutes) {
        if (days == 0 && hours == 0 && minutes == 0) {
            throw new IllegalArgumentException("At least one field must be filled");
        }
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.totalDurationMinutes = days * 8 * 60 + hours * 60 + minutes;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    public void setTotalDurationMinutes(int totalDurationMinutes) {
        this.totalDurationMinutes = totalDurationMinutes;
    }

    private int totalDurationMinutes;

    public static TaskDuration toTaskDuration(int minutes){
        int days = minutes / (8 * 60);
        int hours = (minutes % (8 * 60)) / 60;
        int mins = minutes % 60;
        return new TaskDuration(days, hours, mins);
    }

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


    public String toStringHoursAndMinutes() {
        int totalHours = days * 24 + hours;
        int totalMinutes = totalHours * 60 + minutes;

        int hoursPart = totalMinutes / 60;
        int minutesPart = totalMinutes % 60;

        StringBuilder stringBuilder = new StringBuilder();

        if (hoursPart > 0) {
            stringBuilder.append(hoursPart).append(" h");
        }

        if (minutesPart > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(minutesPart).append(" min");
        }

        return stringBuilder.toString();
    }
}
