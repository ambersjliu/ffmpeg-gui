package interface_adapter.convert_audio_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import use_case.convert_audio.ConvertAudioFileData;
import use_case.convert_audio.ConvertAudioFileInputBoundary;

public class ConvertAudioFileController {

    private final ConvertAudioFileInputBoundary convertAudioFileInteractor;

    public ConvertAudioFileController(ConvertAudioFileInputBoundary convertAudioFileInteractor) {
        this.convertAudioFileInteractor = convertAudioFileInteractor;
    }

    public void execute(ConvertAudioFileState convertAudioFileState) {
        String inputFileName = convertAudioFileState.getInputFilePath();
        String outputFileName = convertAudioFileState.getOutputFilePath();
        String outputFormat = convertAudioFileState.getOutputFormatName();

        TimeCode startTimeCode = new TimeCode(convertAudioFileState.getStartTimeHours(), convertAudioFileState.getStartTimeHours(), convertAudioFileState.getStartTimeSeconds());
        double startTime = startTimeCode.toSeconds();

        TimeCode endTimeCode = new TimeCode(convertAudioFileState.getEndTimeHours(), convertAudioFileState.getEndTimeMinutes(), convertAudioFileState.getEndTimeSeconds());
        double endTime = endTimeCode.toSeconds();

        double duration = endTime - startTime;

        long audioBitRate = convertAudioFileState.getAudioBitRate();
        long audioSampleRate = convertAudioFileState.getAudioSampleRate();
        int numAudioChannels = convertAudioFileState.getNumAudioChannels();
        String audioCodecName = convertAudioFileState.getAudioCodecName();
        AudioAttributes audioAttributes = new AudioAttributes(audioBitRate, audioSampleRate, numAudioChannels, audioCodecName);

        final ConvertAudioFileData AudioFileData = new ConvertAudioFileData(
                inputFileName,
                outputFileName,
                outputFormat,
                startTime,
                duration,
                audioAttributes
        );

        convertAudioFileInteractor.execute(AudioFileData);
    }
}
