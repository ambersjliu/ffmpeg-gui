package interface_adapter.get_paths_and_init;

import use_case.get_paths_and_init.GetPathsAndInitData;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;

public class GetInputPathsAndInitController {

    private final GetPathsAndInitInputBoundary getPathsAndInitInteractor;

    public GetInputPathsAndInitController(GetPathsAndInitInputBoundary getPathsAndInitInteractor) {
        this.getPathsAndInitInteractor = getPathsAndInitInteractor;
    }

    public void execute(String ffmpegPath, String ffprobePath){
        final GetPathsAndInitData getPathsAndInitData = new GetPathsAndInitData(ffmpegPath, ffprobePath);
        getPathsAndInitInteractor.execute(getPathsAndInitData);
    }
}
