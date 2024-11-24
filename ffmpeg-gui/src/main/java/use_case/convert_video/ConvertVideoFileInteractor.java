package use_case.convert_video;

import data_access.FFmpegService;
import entity.AudioAttributes;
import entity.VideoAttributes;
import entity.VideoJob;
import exceptions.BadFileException;
import lombok.AllArgsConstructor;

import java.io.IOException;


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
            validateInput(videoFileData);
            VideoJob job = createVideoJob(videoFileData);
            this.ffmpegService.convertVideo(job);
            final ConvertVideoFileOutputData outputData = new ConvertVideoFileOutputData(true);
            this.convertVideoFileOutputBoundary.prepareSuccessView(outputData);
        }catch(BadFileException e){
            this.convertVideoFileOutputBoundary.prepareFailView("Invalid or null file path");
        } catch (IllegalArgumentException e){
            this.convertVideoFileOutputBoundary.prepareFailView("Error occurred when processing: possibly invalid codec/format combination");
        } catch (Exception e) {
            this.convertVideoFileOutputBoundary.prepareFailView("Unexpected error occurred.");
        }

    }

    protected void validateInput(ConvertVideoFileData videoFileData) throws BadFileException {
        final String inputFilePath = videoFileData.getInputFileName();
        final String outputFilePath = videoFileData.getOutputFileName();

        if (inputFilePath == null || outputFilePath == null || inputFilePath.trim().isEmpty() || outputFilePath.trim().isEmpty()) {
            throw new BadFileException();
        }
    }

    protected VideoJob createVideoJob(ConvertVideoFileData videoFileData) {
        return new VideoJob(
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
