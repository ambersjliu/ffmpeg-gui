package interface_adapter.get_paths_and_init;

import use_case.get_paths_and_init.GetPathsAndInitOutputBoundary;
import use_case.get_paths_and_init.GetPathsAndInitOutputData;

public class GetInputPathsAndInitPresenter implements GetPathsAndInitOutputBoundary {

    @Override
    public void prepareSuccessView(GetPathsAndInitOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
