package entity;

import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegStream;

@Getter
@AllArgsConstructor
public class AudioAttributes {
    private final int bitrate;
    private final int sampleRate;
    private final int channels;
    private final String codecName;

    public AudioAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        if (ffmpegStream == null){
            throw new BadFileException();
        }
        this.bitrate = Math.toIntExact(ffmpegStream.bit_rate);
        this.sampleRate = ffmpegStream.sample_rate;
        this.channels = ffmpegStream.channels;
        this.codecName = ffmpegStream.codec_name;
    }
}
