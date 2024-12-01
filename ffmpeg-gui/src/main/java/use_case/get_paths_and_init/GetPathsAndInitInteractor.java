package use_case.get_paths_and_init;

import java.io.IOException;

import data_access.FFmpegService;
import exceptions.BadFileException;
import exceptions.InvalidExecutableException;
import lombok.AllArgsConstructor;
import utils.Validator;

/**
 * Interactor fot the get ffmpeg path use case.
 */

@AllArgsConstructor
public class GetPathsAndInitInteractor implements GetPathsAndInitInputBoundary {

    private final GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary;
    private final FFmpegService ffmpegService;

    /**
     * Given an input object containing paths to ffmpeg and ffprobe executables,
     * attempts to initialize an FFmpegService object.
     * If any issues occur, informs the output boundary to display a fail view.
     * @param input the paths to ffmpeg.exe and ffprobe.exe
     */
    @Override
    public void execute(GetPathsAndInitData input) {
        final String ffmpegPath = input.getFfmpegPath();
        final String ffprobePath = input.getFfprobePath();

        try {
            Validator.validateFilePaths(ffmpegPath, ffprobePath);
            Validator.doesEndOfPathContain(ffmpegPath, "ffmpeg");
            Validator.doesEndOfPathContain(ffprobePath, "ffprobe");

            this.ffmpegService.initialize(ffmpegPath, ffprobePath);
            this.ffmpegService.validateBinaries();
            final GetPathsAndInitOutputData outputData = new GetPathsAndInitOutputData(false);
            this.getPathsAndInitOutputBoundary.prepareSuccessView(outputData);
        }
        catch (BadFileException exception) {
            this.getPathsAndInitOutputBoundary.prepareFailView("Please add both paths!");
        }
        catch (IOException exception) {
            this.getPathsAndInitOutputBoundary.prepareFailView("Invalid file type");
        }
        catch (InvalidExecutableException exception) {
            this.getPathsAndInitOutputBoundary.prepareFailView("Invalid executable");
        }
        catch (Exception exception) {
            this.getPathsAndInitOutputBoundary.prepareFailView("Unexpected error happened");
        }
    }
}
