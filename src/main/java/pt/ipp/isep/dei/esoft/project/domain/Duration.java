package pt.ipp.isep.dei.esoft.project.domain;

public class Duration {
    private int days;
    private int hours;
    private int minutes;

    public Duration(int days, int hours, int minutes) {
        if(days == 0 && hours == 0 && minutes == 0){
            throw new IllegalArgumentException("At least one field must be filled");
        }
        setDays(days);
        setHours(hours);
        setMinutes(minutes);
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        validateDays(days);
        this.days = days;
    }
    private void validateDays(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Days must be a positive integer");
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        validateHours(hours);
        this.hours = hours;
    }


    private void validateHours(int hours) {
        if (hours < 0 || hours > 23) {
            throw new IllegalArgumentException("Hours must be between 0 and 23");
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        validateMinutes(minutes);
        this.minutes = minutes;
    }

    private void validateMinutes(int minutes) {
        if (minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
    }

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
}
