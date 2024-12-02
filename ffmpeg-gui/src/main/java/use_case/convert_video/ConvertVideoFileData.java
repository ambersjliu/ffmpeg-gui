package use_case.convert_video;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data of video file in convert video use case.
 */

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

    private final boolean cropping;
}
