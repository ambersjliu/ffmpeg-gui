package use_case.convert_audio;

/**
 * Interface defines the interactor for convert audio file use case.
 */

public interface ConvertAudioFileInputBoundary {
    /**
     * Executes logic in interactor.
     * @param convertAudioFileData Input data from the controller.
     */
    void execute(ConvertAudioFileData convertAudioFileData);
}
