package use_case.convert_audio;

import attribute.AudioAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConvertAudioFileData {
    private final String inputFileName;
    private final String outputFileName;
    private final String outputFormat;

    private final double startTime;
    private final double duration;

    private final AudioAttributes audioAttributes;
}
