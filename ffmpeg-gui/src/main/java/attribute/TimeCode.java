package attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimeCode {
    private final int hours;
    private final int minutes;
    private final double seconds;

    public TimeCode(double timeInSeconds) {
        this.hours = (int) (timeInSeconds / 3600);
        this.minutes = (int) ((timeInSeconds % 3600) / 60);
        seconds = timeInSeconds % 60;
    }

    public double toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }
}
