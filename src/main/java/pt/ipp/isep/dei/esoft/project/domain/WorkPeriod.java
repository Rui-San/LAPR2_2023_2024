package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Duration;

public class WorkPeriod {
    private Date workStartDate;
    private int workStartHour;
    private int workStartMin;
    private Date workEndDate;
    private int workEndHour;
    private int workEndMin;

    public WorkPeriod(Date workStartDate, int workStartHour, int workStartMin, Duration expectedDuration) {
        this.workStartDate = workStartDate;
        this.workStartHour = workStartHour;
        this.workStartMin = workStartMin;

        // Calculating work end date and time based on expected duration
        calculateEndDate(expectedDuration);
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

    private void calculateEndDate(Duration expectedDuration) {
        // Assuming work hours are from 8 AM to 12 PM and from 1 PM to 5 PM
        int workHoursPerDay = 8; // Total working hours per day
        int workHoursMorning = 4; // Morning working hours
        int workHoursAfternoon = 4; // Afternoon working hours

        long totalDurationMinutes = expectedDuration.toMinutes();
        long remainingMinutes = totalDurationMinutes;

        // Calculate end date
        Date endDate = workStartDate.clone();

        // Calculate end time
        int endHour = workStartHour;
        int endMin = workStartMin;

        while (remainingMinutes > 0) {
            if (endHour < 12) {
                // Check if the remaining minutes fit in the morning working hours
                long morningRemainingMinutes = (12 - endHour) * 60 - endMin;
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
                long afternoonRemainingMinutes = (17 - endHour) * 60 - endMin;
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

    public boolean overlapsWith(WorkPeriod other) {
        // Check if this period overlaps with another period
        boolean startBeforeOtherEnds = this.workEndDate.isBefore(other.workEndDate) ||
                (this.workEndDate.equals(other.workEndDate) &&
                        (this.workEndHour < other.workEndHour ||
                                (this.workEndHour == other.workEndHour && this.workEndMin <= other.workEndMin)));
        boolean endAfterOtherStarts = this.workStartDate.isAfter(other.workStartDate) ||
                (this.workStartDate.equals(other.workStartDate) &&
                        (this.workStartHour > other.workStartHour ||
                                (this.workStartHour == other.workStartHour && this.workStartMin >= other.workStartMin)));

        return startBeforeOtherEnds && endAfterOtherStarts;
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
