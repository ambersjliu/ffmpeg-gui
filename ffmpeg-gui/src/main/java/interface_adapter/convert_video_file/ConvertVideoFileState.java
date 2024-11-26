package interface_adapter.convert_video_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import attribute.VideoAttributes;
import constant.VideoFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConvertVideoFileState {
    private String inputFilePath;
    private String outputFilePath;
    private String outputFormatName;

    private int startTimeHours;
    private int startTimeMinutes;
    private double startTimeSeconds;

    private int endTimeHours;
    private int endTimeMinutes;
    private double endTimeSeconds;

    private int width;
    private int height;
    private long videoBitRate;
    private double frameRate;
    private String videoCodecName;

    private String audioCodecName;
    private int numAudioChannels;
    private long audioBitRate;
    private long audioSampleRate;

    private String conversionSuccessMessage;
    private String conversionErrorMessage;

    public ConvertVideoFileState(String inputFilePath, String outputFormatName,
                                 TimeCode startTime, TimeCode endTime,
                                 VideoAttributes videoAttributes, AudioAttributes audioAttributes) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf('.')) + "." + VideoFormat.DEFAULT_FORMAT;
        this.outputFormatName = outputFormatName;

        this.startTimeHours = startTime.getHours();
        this.startTimeMinutes = startTime.getMinutes();
        this.startTimeSeconds = startTime.getSeconds();

        this.endTimeHours = endTime.getHours();
        this.endTimeMinutes = endTime.getMinutes();
        this.endTimeSeconds = endTime.getSeconds();

        this.width = videoAttributes.getWidth();
        this.height = videoAttributes.getHeight();
        this.videoBitRate = videoAttributes.getBitrate();
        this.frameRate = videoAttributes.getFps();
        this.videoCodecName = videoAttributes.getCodecName();

        this.audioCodecName = audioAttributes.getCodecName();
        this.audioSampleRate = audioAttributes.getSampleRate();
        this.numAudioChannels = audioAttributes.getChannels();
        this.audioBitRate = audioAttributes.getBitrate();
    }

}
