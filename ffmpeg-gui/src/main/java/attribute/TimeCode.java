package attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Store duration in hh:mm:ss.ss .
 */

@Getter
@AllArgsConstructor
public class TimeCode {
    private final int hours;
    private final int minutes;
    private final double seconds;

    public TimeCode(double timeInSeconds) {
        this.hours = (int) (timeInSeconds / 3600);
        this.minutes = (int) ((timeInSeconds % 3600) / 60);
        this.seconds = timeInSeconds % 60;
    }

    /**
     * Convert hh:mm:ss.ss to total seconds.
     * @return seconds
     */

    public double toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }
}
