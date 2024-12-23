package entity;

import attribute.AudioAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Defines jobs for audio.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AudioJob extends AbstractJob {
    private AudioAttributes audioAttributes;

    public AudioJob(String inputFileName, String outputFileName, String outputFormat, double duration, double startTime,
                    AudioAttributes audioAttributes) {
        super(inputFileName, outputFileName, outputFormat, duration, startTime);
        this.audioAttributes = audioAttributes;
    }
}
