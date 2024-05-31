package pt.ipp.isep.dei.esoft.project.tools;

import java.io.Serializable;

public class TaskDurationFormatter implements Serializable {

    public static String toStringDaysHoursMinutes(int days, int hours, int minutes) {
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

    public static String toStringDaysHoursMinutes(String expectedDurationString) {
        int days = 0, hours = 0, minutes = 0;

        String[] parts = expectedDurationString.split(" ");
        for (String part : parts) {
            if (part.endsWith("d")) {
                days = Integer.parseInt(part.substring(0, part.length() - 1));
            } else if (part.endsWith("h")) {
                hours = Integer.parseInt(part.substring(0, part.length() - 1));
            } else if (part.endsWith("m")) {
                minutes = Integer.parseInt(part.substring(0, part.length() - 1));
            }
        }

        return toStringDaysHoursMinutes(days, hours, minutes);
    }

    public static String toStringHoursAndMinutes(int days, int hours, int minutes) {
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
