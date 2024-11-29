package use_case.convert_audio;

/**
 * Interfaces defines presenter for convert audio file use case.
 */

public interface ConvertAudioFileOutputBoundary {
    /**
     * Called when interactor is successfully ran.
     * @param outputData output data from the interactor.
     */
    void prepareSuccessView(ConvertAudioFileOutputData outputData);

    /**
     * Called when interactor is not successfully ran.
     * @param errorMessage error message from the interactor.
     */
    void prepareFailView(String errorMessage);
}
