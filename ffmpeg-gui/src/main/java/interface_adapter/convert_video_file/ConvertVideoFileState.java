package interface_adapter.convert_video_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import attribute.VideoAttributes;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        this.outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf('.')) + ".mp4";
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

    public String getInputFilePath() {
        return this.inputFilePath;
    }

    public String getOutputFilePath() {
        return this.outputFilePath;
    }

    public String getOutputFormatName() {
        return this.outputFormatName;
    }

    public int getStartTimeHours() {
        return this.startTimeHours;
    }

    public int getStartTimeMinutes() {
        return this.startTimeMinutes;
    }

    public double getStartTimeSeconds() {
        return this.startTimeSeconds;
    }

    public int getEndTimeHours() {
        return this.endTimeHours;
    }

    public int getEndTimeMinutes() {
        return this.endTimeMinutes;
    }

    public double getEndTimeSeconds() {
        return this.endTimeSeconds;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public long getVideoBitRate() {
        return this.videoBitRate;
    }

    public double getFrameRate() {
        return this.frameRate;
    }

    public String getVideoCodecName() {
        return this.videoCodecName;
    }

    public String getAudioCodecName() {
        return this.audioCodecName;
    }

    public int getNumAudioChannels() {
        return this.numAudioChannels;
    }

    public long getAudioBitRate() {
        return this.audioBitRate;
    }

    public long getAudioSampleRate() {
        return this.audioSampleRate;
    }

    public String getConversionSuccessMessage() {
        return this.conversionSuccessMessage;
    }

    public String getConversionErrorMessage() {
        return this.conversionErrorMessage;
    }
}
