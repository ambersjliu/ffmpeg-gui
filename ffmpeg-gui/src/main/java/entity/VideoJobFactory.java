package entity;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import lombok.NoArgsConstructor;

/**
 * Factory for video job.
 */

@NoArgsConstructor
public class VideoJobFactory {
    /**
     * Create video job.
     *
     * @param inputFileName   file name of input file
     * @param outputFileName  file name of output file
     * @param outputFormat    format of output file
     * @param duration        duration of output file
     * @param startTime       start time of output file
     * @param videoAttributes video attribures of output file
     * @param audioAttributes audio attributes of output file
     * @param cropping        If it should be a cropped video or not
     * @return video job
     */
    public VideoJob create(String inputFileName, String outputFileName, String outputFormat,
                           double duration, double startTime,
                           VideoAttributes videoAttributes, AudioAttributes audioAttributes, boolean cropping) {
        if (cropping) {
            return new CropVideoJob(inputFileName, outputFileName, outputFormat,
                    duration, startTime, videoAttributes, audioAttributes);
        }
        else {
            return new VideoJob(
                    inputFileName, outputFileName, outputFormat, duration, startTime, videoAttributes, audioAttributes);
        }
    }
}
