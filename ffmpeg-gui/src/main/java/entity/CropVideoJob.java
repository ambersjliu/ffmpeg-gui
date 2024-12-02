package entity;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import lombok.Getter;

/**
 * Job for Cropped video.
 */
@Getter
public class CropVideoJob extends VideoJob {

    public CropVideoJob(String inputFileName, String outputFileName, String outputFormat,
                        double duration, double startTime,
                        VideoAttributes videoAttributes, AudioAttributes audioAttributes) {
        super(inputFileName, outputFileName, outputFormat, duration, startTime, videoAttributes, audioAttributes);
    }
}
