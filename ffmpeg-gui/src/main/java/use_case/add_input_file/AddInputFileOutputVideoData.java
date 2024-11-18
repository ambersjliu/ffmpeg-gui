package use_case.add_input_file;

import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegStream;

@AllArgsConstructor
public class AddInputFileOutputVideoData{
    private FFmpegStream videoStream;
    private FFmpegStream audioStream;
    private FFmpegFormat format;
    private String inputFilePath;
}
