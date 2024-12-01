package interface_adapter.convert_video_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import attribute.VideoAttributes;
import use_case.convert_video.ConvertVideoFileData;
import use_case.convert_video.ConvertVideoFileInputBoundary;

/**
 * Controller for convert video use case.
 */

public class ConvertVideoFileController {

    private final ConvertVideoFileInputBoundary convertVideoFileInteractor;

    public ConvertVideoFileController(ConvertVideoFileInputBoundary convertVideoFileInteractor) {
        this.convertVideoFileInteractor = convertVideoFileInteractor;
    }

    /**
     * Execute interactor for convert video use case.
     * @param convertVideoFileState state for convert video use case.
     */

    public void execute(ConvertVideoFileState convertVideoFileState) {
        final String inputFileName = convertVideoFileState.getInputFilePath();
        final String outputFileName = convertVideoFileState.getOutputFilePath();
        final String outputFormat = convertVideoFileState.getOutputFormatName();

        final TimeCode startTimeCode = new TimeCode(
                convertVideoFileState.getStartTimeHours(),
                convertVideoFileState.getStartTimeMinutes(),
                convertVideoFileState.getStartTimeSeconds());
        final double startTime = startTimeCode.toSeconds();

        final TimeCode endTimeCode = new TimeCode(
                convertVideoFileState.getEndTimeHours(),
                convertVideoFileState.getEndTimeMinutes(),
                convertVideoFileState.getEndTimeSeconds());
        final double endTime = endTimeCode.toSeconds();

        final double duration = endTime - startTime;

        final int width = convertVideoFileState.getWidth();
        final int height = convertVideoFileState.getHeight();
        final double fps = convertVideoFileState.getFrameRate();
        final long videoBitRate = convertVideoFileState.getVideoBitRate();
        final String videoCodecName = convertVideoFileState.getVideoCodecName();
        final VideoAttributes videoAttributes = new VideoAttributes(width, height, fps, videoBitRate, videoCodecName);

        final long audioBitRate = convertVideoFileState.getAudioBitRate();
        final long audioSampleRate = convertVideoFileState.getAudioSampleRate();
        final int numAudioChannels = convertVideoFileState.getNumAudioChannels();
        final String audioCodecName = convertVideoFileState.getAudioCodecName();
        final AudioAttributes audioAttributes = new AudioAttributes(
                audioBitRate, audioSampleRate, numAudioChannels, audioCodecName);

        final boolean cropping = convertVideoFileState.isCropping();

        final ConvertVideoFileData videoFileData = new ConvertVideoFileData(
                inputFileName,
                outputFileName,
                outputFormat,
                startTime,
                duration,
                videoAttributes,
                audioAttributes,
                cropping
        );

        convertVideoFileInteractor.execute(videoFileData);
    }
}
