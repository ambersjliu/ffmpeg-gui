package use_case.get_paths_and_init;

public interface GetPathsAndInitOutputBoundary {
    /**
     * Prepares the success view for the Add Input Paths Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GetPathsAndInitOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
