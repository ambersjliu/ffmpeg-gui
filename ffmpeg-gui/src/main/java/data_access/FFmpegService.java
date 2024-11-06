package data_access;

import lombok.Getter;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.IOException;

@Getter
public class FFmpegService {
    private final FFmpeg ffmpeg;
    private final FFprobe ffprobe;

    public FFmpegService(String ffmpegPath, String ffprobePath) throws IOException {
        // TODO: HOW DO WE HANDLE THE IOEXCEPTION GRACEFULLY?
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
    }


}
