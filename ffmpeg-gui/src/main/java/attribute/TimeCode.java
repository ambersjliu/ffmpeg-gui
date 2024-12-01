package attribute;

import constant.TimeConstants;
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
        this.hours = (int) (timeInSeconds / TimeConstants.SECONDS_PER_HOUR);
        this.minutes = (int) ((timeInSeconds % TimeConstants.SECONDS_PER_HOUR) / TimeConstants.SECONDS_PER_MINUTE);
        this.seconds = timeInSeconds % TimeConstants.SECONDS_PER_MINUTE;
    }

    /**
     * Convert hh:mm:ss.ss to total seconds.
     * @return seconds
     */

    public double toSeconds() {
        return hours * TimeConstants.SECONDS_PER_HOUR + minutes * TimeConstants.SECONDS_PER_MINUTE + seconds;
    }
}
