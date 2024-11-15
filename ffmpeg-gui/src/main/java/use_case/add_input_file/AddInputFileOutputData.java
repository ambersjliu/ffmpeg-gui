package use_case.add_input_file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegFormat;

@AllArgsConstructor
@Getter
public class AddInputFileOutputData {
    // Might be temporary but atm idk why we need a new entity class
    // To represent file info
    private final FFmpegFormat ffmpegFormat;
    private final boolean useCaseFailed;
}
