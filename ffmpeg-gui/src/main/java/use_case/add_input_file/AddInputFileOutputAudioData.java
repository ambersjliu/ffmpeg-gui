package use_case.add_input_file;


import entity.AudioAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bramp.ffmpeg.probe.FFmpegFormat;

@Getter
@Setter
@AllArgsConstructor
public class AddInputFileOutputAudioData{
    private AudioAttributes audioAttributes;
    private FFmpegFormat format;
    private String inputFilePath;

}
