package use_case.add_input_file;

import use_case.get_paths_and_init.GetPathsAndInitOutputData;

public interface AddInputFileOutputBoundary {
    /**
     * Prepares the success view for the Add Input File Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(AddInputFileOutputData outputData);

    /**
     * Prepares the failure view for the Add Input File Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
