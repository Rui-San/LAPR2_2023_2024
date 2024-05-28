package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Duration;

public class teste {
    public static void main(String[] args) {
        try {
            Date workStartDate = new Date("01/06/2024");
            int workStartHour = 10;
            int workStartMin = 1;
            Duration expectedDuration = Duration.ofMinutes(1440);

            WorkPeriod wp = new WorkPeriod(workStartDate, workStartHour, workStartMin, expectedDuration);

            System.out.println(wp.getWorkEndDate());
            System.out.println(wp.getWorkEndHour());
            System.out.println(wp.getWorkEndMin());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
