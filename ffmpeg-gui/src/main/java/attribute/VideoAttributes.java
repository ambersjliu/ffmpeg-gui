package attribute;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegStream;

@Getter
public class VideoAttributes {
    private int width;
    private int height;
    private double fps;
    private long bitrate;
    private String codecName;

    public VideoAttributes(FFmpegStream ffmpegStream){
        this.width = ffmpegStream.width;
        this.height = ffmpegStream.height;
        this.fps = ffmpegStream.avg_frame_rate.doubleValue();
        this.bitrate = ffmpegStream.bit_rate;
        this.codecName = ffmpegStream.codec_name;
    }
}
