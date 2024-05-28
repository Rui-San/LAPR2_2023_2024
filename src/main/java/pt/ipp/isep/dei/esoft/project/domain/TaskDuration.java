package pt.ipp.isep.dei.esoft.project.domain;

public class TaskDuration {
    private int days;
    private int hours;
    private int minutes;

    public TaskDuration(int days, int hours, int minutes) {
        if(days == 0 && hours == 0 && minutes == 0){
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




/*
    @Override
    public String toString() {

        StringBuilder formattedDuration = new StringBuilder();

        if (days > 0) {
            formattedDuration.append(days).append(" day");
            if (days > 1) {
                formattedDuration.append("s");
            }
            formattedDuration.append(" ");
        }

        if (hours > 0 || (days > 0 && (minutes > 0 || hours > 0))) {
            formattedDuration.append(hours).append("h");
        }

        if ((hours > 0 || days > 0) && minutes > 0) {
            formattedDuration.append(minutes).append("min");
        } else if (minutes > 0) {
            formattedDuration.append(minutes).append("min");
        }

        return formattedDuration.toString();
    }

 */
}
