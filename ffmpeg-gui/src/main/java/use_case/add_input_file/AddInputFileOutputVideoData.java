package use_case.add_input_file;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegFormat;

/**
 * Video Data of the input file.
 */

@AllArgsConstructor
@Getter
public class AddInputFileOutputVideoData {
    private VideoAttributes videoAttributes;
    private AudioAttributes audioAttributes;
    private FFmpegFormat format;
    private String inputFilePath;
}
