package use_case.get_paths_and_init;

import data_access.FFmpegService;
import exceptions.InvalidExecutableException;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class GetPathsAndInitInteractor implements GetPathsAndInitInputBoundary{

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
            if (!ffmpegPath.contains("ffmpeg.exe") || !ffprobePath.contains("ffprobe.exe")) {
                throw new InvalidExecutableException();
            }
            this.ffmpegService.initialize(ffmpegPath, ffprobePath);
            final GetPathsAndInitOutputData outputData = new GetPathsAndInitOutputData(false);
            this.getPathsAndInitOutputBoundary.prepareSuccessView(outputData);
        } catch (IOException e) {
            this.getPathsAndInitOutputBoundary.prepareFailView("Invalid file type");
        } catch (InvalidExecutableException e) {
            this.getPathsAndInitOutputBoundary.prepareFailView("Invalid executable");
        }
    }
}
