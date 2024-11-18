package data_access;

import exceptions.InvalidExecutableException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.IOException;

@NoArgsConstructor
@Getter
@Setter
public class FFmpegService {
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;


    public void initialize(String ffmpegPath, String ffprobePath) throws IOException {
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
    }

    public void validateBinaries() throws IOException, InvalidExecutableException {
        if (!ffmpeg.isFFmpeg() || !ffprobe.isFFprobe()) {
            throw new InvalidExecutableException();
        }
    }

    // TODO: Create Entities to represent the information about a file as well as user input
    // TODO: Write method using ffprobe to return information about an input file
    // TODO: Encoding method

}
