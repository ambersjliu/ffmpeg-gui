package use_case.convert_audio;

import data_access.FFmpegService;
import entity.AudioJob;
import entity.AudioJobFactory;
import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import utils.Validator;

/**
 * Interactor for convert audio file use case.
 */

@AllArgsConstructor
public class ConvertAudioFileInteractor implements ConvertAudioFileInputBoundary {
    private final ConvertAudioFileOutputBoundary convertAudioFileOutputBoundary;
    private final FFmpegService ffmpegService;

    /**
     * Given input of AudioFileData, containing a path to the file to be converted, path to save location
     * And parameters of the conversion. It will be saved the converted file at the specified save location
     */
    @Override
    public void execute(ConvertAudioFileData convertAudioFileData) {
        try {
            Validator.validateFilePath(convertAudioFileData.getInputFileName());
            final AudioJob job = createAudioJob(convertAudioFileData);
            this.ffmpegService.convertAudio(job);
            final String successMessage =
                    "Successfully converted with output: " + convertAudioFileData.getOutputFileName();
            final ConvertAudioFileOutputData outputData =
                    new ConvertAudioFileOutputData(true, successMessage);
            this.convertAudioFileOutputBoundary.prepareSuccessView(outputData);
        }
        catch (BadFileException exception) {
            this.convertAudioFileOutputBoundary.prepareFailView("Invalid or null file path");
        }
        catch (IllegalArgumentException exception) {
            this.convertAudioFileOutputBoundary.prepareFailView("Error occurred when processing: invalid argument");
        }
        catch (Exception exception) {
            this.convertAudioFileOutputBoundary.prepareFailView("Unexpected error occurred.");
        }

    }

    protected AudioJob createAudioJob(ConvertAudioFileData convertAudioFileData) {
        final AudioJobFactory audioJobFactory = new AudioJobFactory();
        return audioJobFactory.create(
                convertAudioFileData.getInputFileName(),
                convertAudioFileData.getOutputFileName(),
                convertAudioFileData.getOutputFormat(),
                convertAudioFileData.getDuration(),
                convertAudioFileData.getStartTime(),
                convertAudioFileData.getAudioAttributes()
        );

    }
}
