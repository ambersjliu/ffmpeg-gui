package use_case.convert_video;

import data_access.FFmpegService;
import entity.CropVideoJob;
import entity.VideoJob;
import entity.VideoJobFactory;
import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import utils.Validator;

/**
 * Interactor for convert video file use case.
 */

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
        try {

            Validator.validateFilePath(videoFileData.getInputFileName());
            final VideoJob job = createVideoJob(videoFileData);
            this.ffmpegService.convert(job);
            final String successMessage = "Successfully converted with output: " + videoFileData.getOutputFileName();
            final ConvertVideoFileOutputData outputData = new ConvertVideoFileOutputData(true, successMessage);
            this.convertVideoFileOutputBoundary.prepareSuccessView(outputData);
        }
        catch (BadFileException exception) {
            this.convertVideoFileOutputBoundary.prepareFailView("Invalid or null file path");
        }
        catch (IllegalArgumentException exception) {
            this.convertVideoFileOutputBoundary.prepareFailView("Error occurred when processing: invalid argument");
        }
        catch (Exception exception) {
            this.convertVideoFileOutputBoundary.prepareFailView("Unexpected error occurred.");
        }

    }

    VideoJob createVideoJob(ConvertVideoFileData videoFileData) {
        final VideoJobFactory videoJobFactory = new VideoJobFactory();
        return videoJobFactory.create(
                videoFileData.getInputFileName(),
                videoFileData.getOutputFileName(),
                videoFileData.getOutputFormat(),
                videoFileData.getDuration(),
                videoFileData.getStartTime(),
                videoFileData.getVideoAttributes(),
                videoFileData.getAudioAttributes(),
                videoFileData.isCropping()
        );
    }

}
