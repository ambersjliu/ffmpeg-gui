package entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AudioJob extends AbstractJob{
    private AudioAttributes audioAttributes;
    public AudioJob(String inputFileName, String outputFileName, double duration, double startTime,
                    AudioAttributes audioAttributes) {
        super(inputFileName, outputFileName, duration, startTime);
        this.audioAttributes = audioAttributes;
    }
}
