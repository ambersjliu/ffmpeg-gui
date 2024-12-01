package attribute;

import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.bramp.ffmpeg.probe.FFmpegStream;

/**
 * Contains attributes of a video file.
 */

@Getter
@AllArgsConstructor
public class VideoAttributes {
    private int width;
    private int height;
    @Setter
    private double fps;
    private long bitrate;
    private String codecName;

    public VideoAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        if (ffmpegStream == null) {
            throw new BadFileException();
        }
        this.width = ffmpegStream.width;
        this.height = ffmpegStream.height;
        this.fps = ffmpegStream.avg_frame_rate.doubleValue();
        this.bitrate = ffmpegStream.bit_rate;
        this.codecName = ffmpegStream.codec_name;
    }

}
