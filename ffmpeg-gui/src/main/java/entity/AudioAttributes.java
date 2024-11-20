package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AudioAttributes {
    private long bitrate;
    private long sampleRate;
    private int channels;
    private String codecName;
}
