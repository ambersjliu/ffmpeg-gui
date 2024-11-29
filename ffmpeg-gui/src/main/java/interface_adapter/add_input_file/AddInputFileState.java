package interface_adapter.add_input_file;

import lombok.Getter;
import lombok.Setter;

/**
 * State for add input file use case.
 */

@Getter
@Setter
public class AddInputFileState {
    private String filePath = "";
    private String fileError;
}
