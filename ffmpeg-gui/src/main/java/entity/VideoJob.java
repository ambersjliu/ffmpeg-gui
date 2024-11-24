package entity;

import attributes.AudioAttributes;
import attributes.VideoAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class VideoJob extends AbstractJob{
    private VideoAttributes videoAttributes;
    // For now we'll pretend every video has either 0 or 1 audio stream.
    // But in the future this should be a list. Refactor if we have time.
    private AudioAttributes audioAttributes;
    public VideoJob(String inputFileName, String outputFileName, double duration, double startTime,
                    VideoAttributes videoAttributes, AudioAttributes audioAttributes) {
        super(inputFileName, outputFileName, duration, startTime);
        this.videoAttributes = videoAttributes;
        this.audioAttributes = audioAttributes;
    }
}
