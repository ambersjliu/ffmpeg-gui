package interface_adapter.change_file;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_input_file.AddInputFileState;
import interface_adapter.add_input_file.AddInputFileViewModel;
import interface_adapter.convert_video_file.ConvertVideoFileState;
import interface_adapter.convert_video_file.ConvertVideoFileViewModel;
import lombok.AllArgsConstructor;
import use_case.change_file.ChangeFileOutputBoundary;
import use_case.change_file.ChangeFileOutputData;

@AllArgsConstructor
public class ChangeFilePresenter implements ChangeFileOutputBoundary {

    private ConvertVideoFileViewModel convertVideoFileViewModel;
    private ViewManagerModel viewManagerModel;
    private AddInputFileViewModel addInputFileViewModel;

    @Override
    public void prepareSuccessView(ChangeFileOutputData changeFileOutputData) {
        final ConvertVideoFileState convertVideoFileState = convertVideoFileViewModel.getState();
        convertVideoFileState.setInputFilePath("");
        convertVideoFileViewModel.setState(convertVideoFileState);
        convertVideoFileViewModel.firePropertyChanged();
        final AddInputFileState addInputFileState = addInputFileViewModel.getState();
        addInputFileState.setFilePath(changeFileOutputData.getPath());
        addInputFileViewModel.setState(addInputFileState);
        addInputFileViewModel.firePropertyChanged();
        this.viewManagerModel.setState(addInputFileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        //never fails
    }
}
