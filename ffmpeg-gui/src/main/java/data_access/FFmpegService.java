package data_access;

import exceptions.InvalidExecutableException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

import java.io.IOException;

@NoArgsConstructor
@Getter
public class FFmpegService {
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;


    public void initialize(String ffmpegPath, String ffprobePath) throws IOException {
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
        System.out.println(FFmpeg.DEFAULT_PATH);
        if (!ffmpeg.isFFmpeg() || !ffprobe.isFFprobe()){
            throw new IOException();
        }
    }

    // TODO: Encoding method


}
