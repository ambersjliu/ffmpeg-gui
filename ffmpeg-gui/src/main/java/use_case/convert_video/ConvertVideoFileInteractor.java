package use_case.convert_video;

import data_access.FFmpegService;
import entity.AudioAttributes;
import entity.VideoAttributes;
import entity.VideoJob;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ConvertVideoFileInteractor implements ConvertVideoFileInputBoundary {

    private final ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary;
    private final FFmpegService ffmpegService;

    /**
     * Given input of videoFileData, containing a path to the file to be converted, path to save location
     * And parameters of the conversion. It will be saved the converted file at the specified save location
     */
    @Override
    public void execute(ConvertVideoFileData videoFileData) {
        final String inputFilePath = videoFileData.getInputFileName();
        final String outputFilePath = videoFileData.getOutputFileName();
        final double duration = videoFileData.getDuration();
        final double startTime = videoFileData.getStartTime();
        final VideoAttributes videoAttributes = videoFileData.getVideoAttributes();
        final AudioAttributes audioAttributes = videoFileData.getAudioAttributes();

        if(inputFilePath == null || outputFilePath == null || inputFilePath.trim().isEmpty() || outputFilePath.trim().isEmpty()){
            this.convertVideoFileOutputBoundary.prepareFailView("Please choose file and save directory.");
            return;
        }

        try {
            VideoJob job = new VideoJob(inputFilePath, outputFilePath, duration, startTime, videoAttributes, audioAttributes);
            this.ffmpegService.convertVideo(job);
            final ConvertVideoFileOutputData outputData = new ConvertVideoFileOutputData(true);
            this.convertVideoFileOutputBoundary.prepareSuccessView(outputData);
        } catch (Exception e) {  //TODO write proper catch blocks for specific errors.
            this.convertVideoFileOutputBoundary.prepareFailView("Unexpected error occurred.");
        }

    }

}
