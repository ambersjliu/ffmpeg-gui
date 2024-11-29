package use_case.add_input_file;

/**
 * An abstract interface of the interactor in add input file use case.
 */

public interface AddInputFileInputBoundary {
    /**
     * Execute our add input file use case.
     * @param inputFileData Data of the input file
     */
    void execute(AddInputFileInputData inputFileData);
}
