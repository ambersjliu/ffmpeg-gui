package use_case.change_file;

/**
 * Interface defines the presenter of change file use case.
 */

public interface ChangeFileOutputBoundary {
    /**
     * Called after successfully run the logic in the interactor.
     * @param changeFileOutputData output data from the interactor.
     */
    void prepareSuccessView(ChangeFileOutputData changeFileOutputData);

    /**
     * Called after failing to run the logic in the interactor.
     * @param errorMessage error message return fromt the interactor
     */
    void prepareFailView(String errorMessage);
}
