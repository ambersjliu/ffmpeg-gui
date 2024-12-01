package interface_adapter.get_paths_and_init;

import use_case.get_paths_and_init.GetPathsAndInitData;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;

/**
 * Controller of get ffmpeg path use case.
 */

public class GetInputPathsAndInitController {

    private final GetPathsAndInitInputBoundary getPathsAndInitInteractor;

    public GetInputPathsAndInitController(GetPathsAndInitInputBoundary getPathsAndInitInteractor) {
        this.getPathsAndInitInteractor = getPathsAndInitInteractor;
    }

    /**
     * Execute logic in interactor.
     * @param ffmpegPath path to ffmpeg.exe
     * @param ffprobePath path to ffprobe.exe
     */

    public void execute(String ffmpegPath, String ffprobePath) {
        final GetPathsAndInitData getPathsAndInitData = new GetPathsAndInitData(ffmpegPath, ffprobePath);
        getPathsAndInitInteractor.execute(getPathsAndInitData);
    }
}
