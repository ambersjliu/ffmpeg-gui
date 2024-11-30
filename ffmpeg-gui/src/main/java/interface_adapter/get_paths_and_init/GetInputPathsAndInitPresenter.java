package interface_adapter.get_paths_and_init;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileState;
import interface_adapter.add_input_file.AddInputFileViewModel;
import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitOutputData;

/**
 * Presenter for get ffmpeg path use case.
 */

public class GetInputPathsAndInitPresenter implements GetPathsAndInitOutputBoundary {
    private final GetInputPathsAndInitViewModel getInputPathsAndInitViewModel;
    private final AddInputFileViewModel addInputFileViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetInputPathsAndInitPresenter(ViewManagerModel viewManagerModel,
                                         AddInputFileViewModel addInputFileViewModel,
                                         GetInputPathsAndInitViewModel getInputPathsAndInitViewModel) {
        this.addInputFileViewModel = addInputFileViewModel;
        this.getInputPathsAndInitViewModel = getInputPathsAndInitViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
        System.out.printf("Success\n");
        final AddInputFileState addInputFileState = addInputFileViewModel.getState();
        this.addInputFileViewModel.setState(addInputFileState);
        this.addInputFileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(addInputFileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final GetInputPathsAndInitState getInputPathsAndInitState = getInputPathsAndInitViewModel.getState();
        getInputPathsAndInitState.setPathError(errorMessage);
        System.out.printf("Failure\n"
                + errorMessage
                + "\nffmpeg: " + getInputPathsAndInitState.getFfmpegPath()
                + "\nffprobe: " + getInputPathsAndInitState.getFfprobePath() + '\n');
        getInputPathsAndInitViewModel.firePropertyChanged();

    }
}
