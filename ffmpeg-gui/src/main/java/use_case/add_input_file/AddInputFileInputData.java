package use_case.add_input_file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Input data for the add input file use case. Only contains the file path.
 */

@Getter
@AllArgsConstructor
public class AddInputFileInputData {
    private final String filePath;
}
