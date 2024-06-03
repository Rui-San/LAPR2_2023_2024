package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class WorkPeriod implements Serializable {
    private Date workStartDate;
    private int workStartHour;
    private int workStartMin;
    private Date workEndDate;
    private int workEndHour;
    private int workEndMin;

    public WorkPeriod(Date workStartDate, int workStartHour, int workStartMin, TaskDuration expectedDuration) {
        this.workStartDate = workStartDate;
        this.workStartHour = workStartHour;
        this.workStartMin = workStartMin;

        // Calculating work end date and time based on expected duration
        calculateEndDate(expectedDuration);
    }

    public WorkPeriod(Date initialDate, Date finalDate){
        this.workStartDate = initialDate;
        this.workStartHour = 8;
        this.workStartMin = 0;
        this.workEndDate = finalDate;
        this.workEndHour = 17;
        this.workEndMin = 0;
    }

    public Date getWorkStartDate() {
        return workStartDate;
    }

    public int getWorkStartHour() {
        return workStartHour;
    }

    public int getWorkStartMin() {
        return workStartMin;
    }

    private void calculateEndDate(TaskDuration expectedDuration) {
        // Assuming work hours are from 8 AM to 12 PM and from 1 PM to 5 PM
        int workHoursPerDay = 8; // Total working hours per day
        int workHoursMorning = 4; // Morning working hours
        int workHoursAfternoon = 4; // Afternoon working hours

        int remainingMinutes = expectedDuration.getTotalDurationMinutes();


        // Calculate end date
        Date endDate = workStartDate.clone();

        // Calculate end time
        int endHour = workStartHour;
        int endMin = workStartMin;

        while (remainingMinutes > 0) {
            if (endHour < 12) {
                // Check if the remaining minutes fit in the morning working hours
                int morningRemainingMinutes = (12 - endHour) * 60 - endMin;
                if (remainingMinutes <= morningRemainingMinutes) {
                    endHour += remainingMinutes / 60;
                    endMin += remainingMinutes % 60;
                    remainingMinutes = 0;
                } else {
                    // Subtract morning working hours
                    remainingMinutes -= morningRemainingMinutes;
                    // Move to afternoon working hours
                    endHour = 13;
                    endMin = 0;
                }
            } else {
                // Check if the remaining minutes fit in the afternoon working hours
                int afternoonRemainingMinutes = (17 - endHour) * 60 - endMin;
                if (remainingMinutes <= afternoonRemainingMinutes) {
                    endHour += remainingMinutes / 60;
                    endMin += remainingMinutes % 60;
                    remainingMinutes = 0;
                } else {
                    // Move to next day
                    endDate = endDate.plusDays(1);
                    // Reset hour and minutes to start of the day
                    endHour = 8;
                    endMin = 0;
                    // Subtract afternoon working hours
                    remainingMinutes -= afternoonRemainingMinutes;
                }
            }
        }

        this.workEndDate = endDate;
        this.workEndHour = endHour;
        this.workEndMin = endMin;
    }

    public boolean matches(WorkPeriod otherWorkPeriod) {
        return (this.workStartDate.toString().trim().equals(otherWorkPeriod.workStartDate.toString().trim())
                && this.workStartHour == otherWorkPeriod.workStartHour
                && this.workStartMin == otherWorkPeriod.workStartMin
                && this.workEndDate.toString().trim().equals(otherWorkPeriod.workEndDate.toString().trim())
                && this.workEndHour == otherWorkPeriod.workEndHour
                && this.workEndMin == otherWorkPeriod.workEndMin);

    }

    public boolean isOverlap(WorkPeriod other) {
        LocalDateTime thisStart = LocalDateTime.of(workStartDate.getYear(), workStartDate.getMonth(), workStartDate.getDay(), workStartHour, workStartMin);
        LocalDateTime thisEnd = LocalDateTime.of(workEndDate.getYear(), workEndDate.getMonth(), workEndDate.getDay(), workEndHour, workEndMin);

        LocalDateTime otherStart = LocalDateTime.of(other.workStartDate.getYear(), other.workStartDate.getMonth(), other.workStartDate.getDay(), other.workStartHour, other.workStartMin);
        LocalDateTime otherEnd = LocalDateTime.of(other.workEndDate.getYear(), other.workEndDate.getMonth(), other.workEndDate.getDay(), other.workEndHour, other.workEndMin);

        return thisStart.isBefore(otherEnd) && otherStart.isBefore(thisEnd);
    }


/*
    public boolean isOverlap(WorkPeriod other) {
        // Verificar se os períodos de trabalho se sobrepõem
        return (this.workStartDate.isBefore(other.workEndDate) || this.workStartDate.equals(other.workEndDate)) &&
                (this.workEndDate.isAfter(other.workStartDate) || this.workEndDate.equals(other.workStartDate));
    }

 */
    /*
    public boolean isOverlap(WorkPeriod other) {
        // Check if the work periods are on different days
        if (this.workEndDate.isBefore(other.workStartDate) || other.workEndDate.isBefore(this.workStartDate)) {
            return false; // No overlap if they are on different days
        }

        // Check if the work periods overlap within the same day
        if (this.workEndDate.equals(other.workStartDate) || other.workEndDate.equals(this.workStartDate)) {
            // Check if the ending and starting times are exactly the same, excluding the minutes
            if (this.workEndHour == other.workStartHour && this.workEndMin == other.workStartMin) {
                return false;
            }
        }

        // Check if one work period starts during the other work period
        if ((this.workStartDate.isBefore(other.workStartDate) || this.workStartDate.equals(other.workStartDate)) &&
                (this.workEndDate.isAfter(other.workStartDate) || this.workEndDate.equals(other.workStartDate))) {
            return true; // There is overlap
        }

        // Check if one work period ends during the other work period
        if ((this.workStartDate.isBefore(other.workEndDate) || this.workStartDate.equals(other.workEndDate)) &&
                (this.workEndDate.isAfter(other.workEndDate) || this.workEndDate.equals(other.workEndDate))) {
            return true; // There is overlap
        }

        // If none of the above conditions are met, there is no overlap
        return false;
    }

     */


    public boolean isOverlapDates(WorkPeriod other) {
        // Check if this work period ends before the other work period starts
        if (this.workEndDate.isBefore(other.workStartDate)) {
            return false; // No overlap
        }

        // Check if this work period starts after the other work period ends
        if (this.workStartDate.isAfter(other.workEndDate)) {
            return false; // No overlap
        }

        // Check if this work period starts exactly when the other work period ends
        if (this.workStartDate.equals(other.workEndDate) && this.workStartHour == other.workEndHour && this.workStartMin == other.workEndMin) {
            return false; // No overlap
        }

        // Check if this work period ends exactly when the other work period starts
        if (this.workEndDate.equals(other.workStartDate) && this.workEndHour == other.workStartHour && this.workEndMin == other.workStartMin) {
            return false; // No overlap
        }

        // If none of the above conditions are met, there is overlap
        return true;
    }


    public boolean overlapsWith(WorkPeriod other) {
        boolean startsBeforeOtherEnds = this.workStartDate.isBefore(other.workEndDate) ||
                (this.workStartDate.equals(other.workEndDate) &&
                        (this.workStartHour < other.workEndHour ||
                                (this.workStartHour == other.workEndHour && this.workStartMin < other.workEndMin)));

        boolean endsAfterOtherStarts = this.workEndDate.isAfter(other.workStartDate) ||
                (this.workEndDate.equals(other.workStartDate) &&
                        (this.workEndHour > other.workStartHour ||
                                (this.workEndHour == other.workStartHour && this.workEndMin > other.workStartMin)));

        return startsBeforeOtherEnds && endsAfterOtherStarts;
    }


    public Date getWorkEndDate() {
        return workEndDate;
    }

    public int getWorkEndHour() {
        return workEndHour;
    }

    public int getWorkEndMin() {
        return workEndMin;
    }

}
