package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoAttributes {
    private int width;
    private int height;
    private double fps;
    private long bitrate;
    private String codecName;

}
