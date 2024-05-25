package pt.ipp.isep.dei.esoft.project.tools;

import java.time.Duration;

public class DurationFormatter {

    public static String formatDuration(Duration duration) {
        long days = duration.toDays();
        duration = duration.minusDays(days);

        long hours = duration.toHours();
        duration = duration.minusHours(hours);

        long minutes = duration.toMinutes();

        StringBuilder formattedDuration = new StringBuilder();

        if (days > 0) {
            formattedDuration.append(days).append(" day");
            if (days > 1) {
                formattedDuration.append("s");
            }
            formattedDuration.append(", ");
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
