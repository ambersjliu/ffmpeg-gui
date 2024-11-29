package interface_adapter.convert_audio_file;

import lombok.AllArgsConstructor;
import use_case.convert_audio.ConvertAudioFileOutputBoundary;
import use_case.convert_audio.ConvertAudioFileOutputData;

/**
 * Presenter of the convert audio file use case.
 */

@AllArgsConstructor
public class ConvertAudioFilePresenter implements ConvertAudioFileOutputBoundary {

    private final ConvertAudioFileViewModel convertAudioFileViewModel;

    @Override
    public void prepareSuccessView(ConvertAudioFileOutputData outputData) {
        final ConvertAudioFileState state = convertAudioFileViewModel.getState();
        state.setConversionSuccessMessage(outputData.getSuccessMessage());
        convertAudioFileViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ConvertAudioFileState state = convertAudioFileViewModel.getState();
        state.setConversionErrorMessage(errorMessage);
        convertAudioFileViewModel.firePropertyChanged();
    }
}
