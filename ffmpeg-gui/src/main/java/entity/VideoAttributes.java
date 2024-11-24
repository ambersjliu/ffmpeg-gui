package entity;

import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.bramp.ffmpeg.probe.FFmpegStream;

@Getter
@AllArgsConstructor
public class VideoAttributes {
    private final int width;
    private final int height;
    private final double fps;
    private final long bitrate;
    private final String codecName;

    public VideoAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        if (ffmpegStream == null){
            throw new BadFileException();
        }
        this.width = ffmpegStream.width;
        this.height = ffmpegStream.height;
        this.fps = ffmpegStream.avg_frame_rate.doubleValue();
        this.bitrate = ffmpegStream.bit_rate;
        this.codecName = ffmpegStream.codec_name;
    }
}
