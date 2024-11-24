package use_case.convert_video;

import entity.AudioAttributes;
import entity.VideoAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@Getter
@AllArgsConstructor
public class ConvertVideoFileData {
    private final String inputFileName;
    private final String outputFileName;
    private final String outputFormat;

    private final double startTime;
    private final double duration;

    private final VideoAttributes videoAttributes;
    private final AudioAttributes audioAttributes;
}
