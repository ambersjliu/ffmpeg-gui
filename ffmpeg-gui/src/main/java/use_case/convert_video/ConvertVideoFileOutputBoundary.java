package use_case.convert_video;

/**
 * Interface define presenter in convert video use case.
 */

public interface ConvertVideoFileOutputBoundary {
    /**
     * Called when interactor is successfully run.
     * @param outputData output file from the interactor.
     */

    void prepareSuccessView(ConvertVideoFileOutputData outputData);

    /**
     * Called when interactor is not successfully run.
     * @param errorMessage error message from the interactor.
     */
    void prepareFailView(String errorMessage);

}
