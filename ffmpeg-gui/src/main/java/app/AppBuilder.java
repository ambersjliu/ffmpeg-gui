package app;

import data_access.FFmpegService;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitPresenter;
import interface_adapter.get_paths_and_init.GetInputPathsAndInitViewModel;
import use_case.get_paths_and_init.GetPathsAndInitInputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitInteractor;
import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;

public class AppBuilder {
    // This is our "DAO"
    private final FFmpegService ffmpegService = new FFmpegService();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();

    private AddInputFileViewModel addInputFileViewModel;
    private GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;

    public AppBuilder(){

    }

    public AppBuilder addGetPathsAndInitUseCase(){
        // todo: incomplete
        final GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary =
                new GetInputPathsAndInitPresenter(viewManagerModel, addInputFileViewModel, getInputPathsAndInitViewModel); // should have viewmodel as param

        final GetPathsAndInitInputBoundary getPathsAndInitInputBoundary =
                new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        // Todo: controller
        // todo: set controller of the getpathsinit view
        return this;
    }

}
