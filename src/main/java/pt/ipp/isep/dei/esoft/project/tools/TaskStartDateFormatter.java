package pt.ipp.isep.dei.esoft.project.tools;

import java.io.Serializable;

public class TaskStartDateFormatter implements Serializable {

    public static String getFormattedStartDateTime(String workStartDate, int workStartHour, int workStartMin) {
        return String.format("%s, %02d:%02d", workStartDate, workStartHour, workStartMin);
    }

}
