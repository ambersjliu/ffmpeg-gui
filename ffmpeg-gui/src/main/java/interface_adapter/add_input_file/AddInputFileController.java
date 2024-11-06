package interface_adapter.add_input_file;

import lombok.AllArgsConstructor;
import use_case.add_input_file.AddInputFileInputBoundary;

@AllArgsConstructor
public class AddInputFileController {
    private final AddInputFileInputBoundary addInputFileInputInteractor;


}
