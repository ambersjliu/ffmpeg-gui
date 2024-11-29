package attribute;

import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegStream;

/**
 * Defines attributes in audio.
 */

@Getter
@AllArgsConstructor
public class AudioAttributes {
    private long bitrate;
    private long sampleRate;
    private int channels;
    private String codecName;

    public AudioAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        if (ffmpegStream == null) {
            throw new BadFileException();
        }
        this.bitrate = ffmpegStream.bit_rate;
        this.sampleRate = ffmpegStream.sample_rate;
        this.channels = ffmpegStream.channels;
        this.codecName = ffmpegStream.codec_name;
    }
}
