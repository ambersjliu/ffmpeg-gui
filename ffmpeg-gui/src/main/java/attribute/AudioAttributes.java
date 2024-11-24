package attribute;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegStream;

@Getter
public class AudioAttributes {
    private long bitrate;
    private long sampleRate;
    private int channels;
    private String codecName;

    public AudioAttributes(FFmpegStream ffmpegStream) {
        this.bitrate = ffmpegStream.bit_rate;
        this.sampleRate = ffmpegStream.sample_rate;
        this.channels = ffmpegStream.channels;
        this.codecName = ffmpegStream.codec_name;
    }
}
