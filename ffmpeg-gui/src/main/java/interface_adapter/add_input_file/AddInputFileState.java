package interface_adapter.add_input_file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddInputFileState {
    private String filePath = "";
    private String fileError;
}
