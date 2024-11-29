package interface_adapter.convert_audio_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import use_case.convert_audio.ConvertAudioFileData;
import use_case.convert_audio.ConvertAudioFileInputBoundary;

/**
 * Controller of the convert audio file use case.
 */

public class ConvertAudioFileController {

    private final ConvertAudioFileInputBoundary convertAudioFileInteractor;

    public ConvertAudioFileController(ConvertAudioFileInputBoundary convertAudioFileInteractor) {
        this.convertAudioFileInteractor = convertAudioFileInteractor;
    }

    /**
     * Executes logic in the interactor.
     * @param convertAudioFileState audio state from add input file use case.
     */

    public void execute(ConvertAudioFileState convertAudioFileState) {
        final String inputFileName = convertAudioFileState.getInputFilePath();
        final String outputFileName = convertAudioFileState.getOutputFilePath();
        final String outputFormat = convertAudioFileState.getOutputFormatName();

        final TimeCode startTimeCode = new TimeCode(
                convertAudioFileState.getStartTimeHours(),
                convertAudioFileState.getStartTimeMinutes(),
                convertAudioFileState.getStartTimeSeconds());
        final double startTime = startTimeCode.toSeconds();

        final TimeCode endTimeCode = new TimeCode(
                convertAudioFileState.getEndTimeHours(),
                convertAudioFileState.getEndTimeMinutes(),
                convertAudioFileState.getEndTimeSeconds());
        final double endTime = endTimeCode.toSeconds();

        final double duration = endTime - startTime;

        final long audioBitRate = convertAudioFileState.getAudioBitRate();
        final long audioSampleRate = convertAudioFileState.getAudioSampleRate();
        final int numAudioChannels = convertAudioFileState.getNumAudioChannels();
        final String audioCodecName = convertAudioFileState.getAudioCodecName();
        final AudioAttributes audioAttributes = new AudioAttributes(
                audioBitRate, audioSampleRate, numAudioChannels, audioCodecName);

        final ConvertAudioFileData audioFileData = new ConvertAudioFileData(
                inputFileName,
                outputFileName,
                outputFormat,
                startTime,
                duration,
                audioAttributes
        );

        convertAudioFileInteractor.execute(audioFileData);
    }
}
