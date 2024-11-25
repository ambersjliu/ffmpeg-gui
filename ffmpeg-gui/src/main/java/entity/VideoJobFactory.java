package entity;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VideoJobFactory {
    public VideoJob create(String inputFileName, String outputFileName, String outputFormat, double duration, double startTime,
                           VideoAttributes videoAttributes, AudioAttributes audioAttributes){
        return new VideoJob(inputFileName, outputFileName, outputFormat, duration, startTime, videoAttributes, audioAttributes);

    }
}
