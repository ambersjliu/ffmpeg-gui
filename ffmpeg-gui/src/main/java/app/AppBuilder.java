package app;

import data_access.FFmpegService;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitPresenter;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInteractor;
import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;

public class AppBuilder {
    // This is our "DAO"
    private final FFmpegService ffmpegService = new FFmpegService();

    public AppBuilder(){

    }

    public AppBuilder addGetPathsAndInitUseCase(){
        // todo: incomplete
        final GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary =
                new GetInputPathsAndInitPresenter(); // should have viewmodel as param

        final GetPathsAndInitInputBoundary getPathsAndInitInputBoundary =
                new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        // Todo: controller
        // todo: set controller of the getpathsinit view
        return this;
    }

}
