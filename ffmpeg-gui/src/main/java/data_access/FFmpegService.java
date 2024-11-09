package data_access;

import exceptions.InvalidExecutableException;
import lombok.Getter;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.IOException;

@Getter
public class FFmpegService {
    private final FFmpeg ffmpeg;
    private final FFprobe ffprobe;

    public FFmpegService(String ffmpegPath, String ffprobePath) throws IOException, InvalidExecutableException {
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
        if (!ffmpeg.isFFmpeg() || !ffprobe.isFFprobe()){
            throw new InvalidExecutableException();
        }
    }

    // TODO: Create Entities to represent the information about a file as well as user input
    // TODO: Write method using ffprobe to return information about an input file
    // TODO: Encoding method

}
