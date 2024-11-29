package interface_adapter.add_input_file;

import lombok.AllArgsConstructor;
import use_case.add_input_file.AddInputFileInputBoundary;
import use_case.add_input_file.AddInputFileInputData;

/**
 * Controller for add input file use case.
 */

@AllArgsConstructor
public class AddInputFileController {
    private final AddInputFileInputBoundary addInputFileInputInteractor;

    /**
     * Executes the add input file use case.
     * @param inputFilePath the inputted path to the file.
     */
    public void execute(String inputFilePath) {
        final AddInputFileInputData inputData = new AddInputFileInputData(inputFilePath);
        addInputFileInputInteractor.execute(inputData);
    }

}
