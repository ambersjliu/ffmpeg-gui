package use_case.convert_video;

import data_access.FFmpegService;
import entity.VideoJob;
import entity.VideoJobFactory;
import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import utils.Validator;


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
            VideoJob job = createVideoJob(videoFileData);
            this.ffmpegService.convertVideo(job);
            String successMessage = "Successfully converted with output: " + videoFileData.getOutputFileName();
            final ConvertVideoFileOutputData outputData = new ConvertVideoFileOutputData(true, successMessage);
            this.convertVideoFileOutputBoundary.prepareSuccessView(outputData);
        }catch(BadFileException e){
            this.convertVideoFileOutputBoundary.prepareFailView("Invalid or null file path");
        } catch (IllegalArgumentException e){
            this.convertVideoFileOutputBoundary.prepareFailView("Error occurred when processing: possibly invalid codec/format combination");
        } catch (Exception e) {
            this.convertVideoFileOutputBoundary.prepareFailView("Unexpected error occurred.");
        }

    }


    protected VideoJob createVideoJob(ConvertVideoFileData videoFileData) {
        VideoJobFactory videoJobFactory = new VideoJobFactory();
        return videoJobFactory.create(
                videoFileData.getInputFileName(),
                videoFileData.getOutputFileName(),
                videoFileData.getOutputFormat(),
                videoFileData.getDuration(),
                videoFileData.getStartTime(),
                videoFileData.getVideoAttributes(),
                videoFileData.getAudioAttributes()
        );
    }

}
