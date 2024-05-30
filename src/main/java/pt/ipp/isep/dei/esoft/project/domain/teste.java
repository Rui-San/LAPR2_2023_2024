package pt.ipp.isep.dei.esoft.project.domain;

import java.time.Duration;

public class teste {
    public static void main(String[] args) {

        try {
            Date workStartDate = new Date("29/05/2024");
            int workStartHour = 8;
            int workStartMin = 0;
            TaskDuration taskDuration = new TaskDuration(0, 1, 0);
            WorkPeriod wp1 = new WorkPeriod(workStartDate, workStartHour, workStartMin, taskDuration);

            TaskDuration taskDuration2 = new TaskDuration(0, 1, 0);
            Date workStartDate2 = new Date("29/05/2024");
            WorkPeriod wp2 = new WorkPeriod(workStartDate2,8,30,taskDuration2);

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


/*
        TaskDuration taskDuration1 = new TaskDuration(2, 5, 2);
        TaskDuration taskDuration2 = new TaskDuration(0, 5, 0);
        TaskDuration taskDuration3 = new TaskDuration(0, 55, 2);
        TaskDuration taskDuration4 = new TaskDuration(0, 0, 125);
        TaskDuration taskDuration5 = new TaskDuration(25, 0, 0);
        TaskDuration taskDuration6 = new TaskDuration(10, 5, 10);
        TaskDuration taskDuration7 = new TaskDuration(0, 5, 30);
        TaskDuration taskDuration8 = new TaskDuration(3, 52, 0);
        TaskDuration taskDuration9 = new TaskDuration(4, 0, 10);
        System.out.println(taskDuration1.toStringHoursAndMinutes());
        System.out.println(taskDuration2.toStringHoursAndMinutes());
        System.out.println(taskDuration3.toStringHoursAndMinutes());
        System.out.println(taskDuration4.toStringHoursAndMinutes());
        System.out.println(taskDuration5.toStringHoursAndMinutes());
        System.out.println(taskDuration6.toStringHoursAndMinutes());
        System.out.println(taskDuration7.toStringHoursAndMinutes());
        System.out.println(taskDuration8.toStringHoursAndMinutes());
        System.out.println(taskDuration9.toStringHoursAndMinutes());


 */

    }

