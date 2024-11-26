package entity;

import attribute.AudioAttributes;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AudioJobFactory {
    public AudioJob create(String inputFileName, String outputFileName, String outputFormat, double duration, double startTime,
                           AudioAttributes audioAttributes) {
        return new AudioJob(inputFileName, outputFileName, outputFormat, duration, startTime, audioAttributes);

    }
}
