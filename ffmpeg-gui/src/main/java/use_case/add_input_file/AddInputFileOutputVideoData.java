package use_case.add_input_file;

import entity.AudioAttributes;
import entity.VideoAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegFormat;

@AllArgsConstructor
@Getter
public class AddInputFileOutputVideoData{
    private VideoAttributes videoAttributes;
    private AudioAttributes audioAttributes;
    private FFmpegFormat format;
    private String inputFilePath;
}
