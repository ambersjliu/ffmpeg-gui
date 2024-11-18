package use_case.add_input_file;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegStream;
@Getter
@Setter
@AllArgsConstructor
public class AddInputFileOutputAudioData{
    private FFmpegStream audioStream;
    private FFmpegFormat format;
    private String inputFilePath;

}
