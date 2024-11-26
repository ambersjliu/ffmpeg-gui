package interface_adapter.convert_audio_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import constant.AudioCodec;
import constant.AudioFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConvertAudioFIleState {
    private String inputFilePath;
    private String outputFilePath;
    private String outputFormatName;

    private int startTimeHours;
    private int startTimeMinutes;
    private double startTimeSeconds;

    private int endTimeHours;
    private int endTimeMinutes;
    private double endTimeSeconds;

    private String audioCodecName;
    private int numAudioChannels;
    private long audioBitRate;
    private long audioSampleRate;

    private String conversionSuccessMessage;
    private String conversionErrorMessage;

    public ConvertAudioFIleState(String inputFilePath, String outputFormatName,
                                 TimeCode startTime, TimeCode endTime,
                                 AudioAttributes audioAttributes) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = inputFilePath.substring(0, inputFilePath.lastIndexOf('.')) + "." + AudioFormat.DEFAULT_AUDIO_FORMAT;
        this.outputFormatName = outputFormatName;

        this.startTimeHours = startTime.getHours();
        this.startTimeMinutes = startTime.getMinutes();
        this.startTimeSeconds = startTime.getSeconds();

        this.endTimeHours = endTime.getHours();
        this.endTimeMinutes = endTime.getMinutes();
        this.endTimeSeconds = endTime.getSeconds();

        this.audioCodecName = AudioCodec.DEFAULT_AUDIO_CODEC;
        this.audioSampleRate = audioAttributes.getSampleRate();
        this.numAudioChannels = audioAttributes.getChannels();
        this.audioBitRate = audioAttributes.getBitrate();
    }
}
