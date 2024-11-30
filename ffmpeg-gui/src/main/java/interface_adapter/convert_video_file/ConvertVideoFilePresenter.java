package interface_adapter.convert_video_file;

import lombok.AllArgsConstructor;
import use_case.convert_video.ConvertVideoFileOutputBoundary;
import use_case.convert_video.ConvertVideoFileOutputData;

/**
 * Present for convert video use case.
 */

@AllArgsConstructor
public class ConvertVideoFilePresenter implements ConvertVideoFileOutputBoundary {

    private final ConvertVideoFileViewModel convertVideoFileViewModel;

    @Override
    public void prepareSuccessView(ConvertVideoFileOutputData outputData) {
        final ConvertVideoFileState state = convertVideoFileViewModel.getState();
        state.setConversionWarningMessage("");
        state.setConversionSuccessMessage(outputData.getSuccessMessage());
        convertVideoFileViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ConvertVideoFileState state = convertVideoFileViewModel.getState();
        state.setConversionWarningMessage("");
        state.setConversionErrorMessage(errorMessage);
        convertVideoFileViewModel.firePropertyChanged();
    }
}
