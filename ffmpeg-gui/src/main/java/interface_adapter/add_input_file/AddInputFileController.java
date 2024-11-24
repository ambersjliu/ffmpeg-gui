package interface_adapter.add_input_file;

import lombok.AllArgsConstructor;
import use_case.add_input_file.AddInputFileInputBoundary;
import use_case.add_input_file.AddInputFileInputData;

@AllArgsConstructor
public class AddInputFileController {
    private final AddInputFileInputBoundary addInputFileInputInteractor;

    public void execute(String inputFilePath) {
        AddInputFileInputData inputData = new AddInputFileInputData(inputFilePath);
        addInputFileInputInteractor.execute(inputData);
    }

}
