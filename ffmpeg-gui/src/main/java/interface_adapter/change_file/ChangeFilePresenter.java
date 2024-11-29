package interface_adapter.change_file;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileState;
import interface_adapter.add_input_file.AddInputFileViewModel;
import lombok.AllArgsConstructor;
import use_case.change_file.ChangeFileOutputBoundary;
import use_case.change_file.ChangeFileOutputData;

/**
 * Presenter for the change file use case.
 */

@AllArgsConstructor
public class ChangeFilePresenter implements ChangeFileOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private AddInputFileViewModel addInputFileViewModel;

    @Override
    public void prepareSuccessView(ChangeFileOutputData changeFileOutputData) {
        final AddInputFileState addInputFileState = addInputFileViewModel.getState();
        addInputFileState.setFileError("");
        addInputFileState.setFilePath(changeFileOutputData.getPath());
        addInputFileViewModel.setState(addInputFileState);
        addInputFileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(addInputFileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // never fails
    }
}
