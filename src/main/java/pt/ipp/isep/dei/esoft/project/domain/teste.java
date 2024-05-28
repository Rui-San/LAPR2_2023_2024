package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Duration;

public class teste {
    public static void main(String[] args) {
        try {
            Date workStartDate = new Date("29/05/2024");
            int workStartHour = 8;
            int workStartMin = 0;
            TaskDuration taskDuration = new TaskDuration(6, 10, 0);
            WorkPeriod wp1 = new WorkPeriod(workStartDate, workStartHour, workStartMin, taskDuration);
            TaskDuration taskDuration2 = new TaskDuration(0, 5, 0);
            Date workStartDate2 = new Date("30/05/2024");
            WorkPeriod wp2 = new WorkPeriod(workStartDate2,8,0,taskDuration2);

            boolean check = wp1.isOverlap(wp2);

            if(check){
                System.out.println("dá overlap");
            }else{
                System.out.println("não dá overlap");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
