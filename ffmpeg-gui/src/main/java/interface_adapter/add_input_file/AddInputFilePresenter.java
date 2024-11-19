package interface_adapter.add_input_file;

import interface_adapter.ViewManagerModel;
import lombok.AllArgsConstructor;
import use_case.add_input_file.AddInputFileOutputAudioData;
import use_case.add_input_file.AddInputFileOutputBoundary;
import use_case.add_input_file.AddInputFileOutputVideoData;

@AllArgsConstructor
public class AddInputFilePresenter implements AddInputFileOutputBoundary {

    private final AddInputFileViewModel addInputFileViewModel;
    //todo have to make conversion viewmodel class
    private final ViewManagerModel viewManagerModel;

    @Override
    public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {

    }

    @Override
    public void prepareVideoSuccessView(AddInputFileOutputVideoData outputVideoData){

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final AddInputFileState state = addInputFileViewModel.getState();
        state.setFileError(errorMessage);
        addInputFileViewModel.firePropertyChanged();
    }
}
