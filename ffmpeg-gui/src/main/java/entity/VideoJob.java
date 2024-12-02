package entity;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Job for video.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VideoJob extends AbstractJob {
    private VideoAttributes videoAttributes;
    private AudioAttributes audioAttributes;

    public VideoJob(String inputFileName, String outputFileName, String outputFormat, double duration, double startTime,
                    VideoAttributes videoAttributes, AudioAttributes audioAttributes) {
        super(inputFileName, outputFileName, outputFormat, duration, startTime);
        this.videoAttributes = videoAttributes;
        this.audioAttributes = audioAttributes;

    }

}
