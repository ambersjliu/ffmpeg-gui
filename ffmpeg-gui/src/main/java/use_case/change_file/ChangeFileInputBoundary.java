package use_case.change_file;

/**
 * Interface defining the interactor.
 */

public interface ChangeFileInputBoundary {
    /**
     * Executes logic in the interactor.
     * @param changeFileInputData Input data when calling change file
     */
    void execute(ChangeFileInputData changeFileInputData);
}

