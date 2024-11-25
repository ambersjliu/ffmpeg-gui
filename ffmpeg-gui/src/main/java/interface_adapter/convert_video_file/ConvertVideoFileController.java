package interface_adapter.convert_video_file;

import attribute.AudioAttributes;
import attribute.TimeCode;
import attribute.VideoAttributes;
import use_case.convert_video.ConvertVideoFileData;
import use_case.convert_video.ConvertVideoFileInputBoundary;

public class ConvertVideoFileController {

    private final ConvertVideoFileInputBoundary convertVideoFileInteractor;

    public ConvertVideoFileController(ConvertVideoFileInputBoundary convertVideoFileInteractor) {
        this.convertVideoFileInteractor = convertVideoFileInteractor;
    }

    public void execute(ConvertVideoFileState convertVideoFileState) {
        String inputFileName = convertVideoFileState.getInputFilePath();
        String outputFileName = convertVideoFileState.getOutputFilePath();
        String outputFormat = convertVideoFileState.getOutputFormatName();


        TimeCode startTimeCode = new TimeCode(convertVideoFileState.getStartTimeHours(), convertVideoFileState.getEndTimeMinutes(), convertVideoFileState.getStartTimeSeconds());
        double startTime = startTimeCode.toSeconds();

        TimeCode endTimeCode = new TimeCode(convertVideoFileState.getEndTimeHours(), convertVideoFileState.getEndTimeMinutes(), convertVideoFileState.getEndTimeSeconds());
        double endTime = endTimeCode.toSeconds();

        double duration = endTime - startTime;


        int width = convertVideoFileState.getWidth();
        int height = convertVideoFileState.getHeight();
        double fps = convertVideoFileState.getFrameRate();
        long videoBitRate = convertVideoFileState.getVideoBitRate();
        String videoCodecName = convertVideoFileState.getVideoCodecName();
        VideoAttributes videoAttributes = new VideoAttributes(width, height, fps, videoBitRate, videoCodecName);

        long audioBitRate = convertVideoFileState.getAudioBitRate();
        long audioSampleRate = convertVideoFileState.getAudioSampleRate();
        int numAudioChannels = convertVideoFileState.getNumAudioChannels();
        String audioCodecName = convertVideoFileState.getAudioCodecName();
        AudioAttributes audioAttributes = new AudioAttributes(audioBitRate, audioSampleRate, numAudioChannels, audioCodecName);

        final ConvertVideoFileData videoFileData = new ConvertVideoFileData(
                inputFileName,
                outputFileName,
                outputFormat,
                startTime,
                duration,
                videoAttributes,
                audioAttributes
        );

         convertVideoFileInteractor.execute(videoFileData);
    }
}
