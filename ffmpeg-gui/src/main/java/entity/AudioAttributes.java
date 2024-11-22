package entity;

import exceptions.BadFileException;
import lombok.Getter;
import net.bramp.ffmpeg.probe.FFmpegStream;

@Getter
public class AudioAttributes {
    private final long bitrate;
    private final long sampleRate;
    private final int channels;
    private final String codecName;

    public AudioAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        if (ffmpegStream == null){
            throw new BadFileException();
        }
        this.bitrate = ffmpegStream.bit_rate;
        this.sampleRate = ffmpegStream.sample_rate;
        this.channels = ffmpegStream.channels;
        this.codecName = ffmpegStream.codec_name;
    }
}
