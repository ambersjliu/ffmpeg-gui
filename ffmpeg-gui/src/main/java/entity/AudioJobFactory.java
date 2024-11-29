package entity;

import attribute.AudioAttributes;
import lombok.AllArgsConstructor;

/**
 * Factory that creates AudioJob.
 */

@AllArgsConstructor
public class AudioJobFactory {
    /**
     * Creates AudioJob.
     * @param inputFileName name of input file
     * @param outputFileName name of output file
     * @param outputFormat format of output file
     * @param duration duration of the output file
     * @param startTime start time of the output file
     * @param audioAttributes attributes of the audio
     * @return a new AudioJob
     */
    public AudioJob create(String inputFileName, String outputFileName,
                           String outputFormat, double duration, double startTime,
                           AudioAttributes audioAttributes) {
        return new AudioJob(inputFileName, outputFileName, outputFormat, duration, startTime, audioAttributes);

    }
}
