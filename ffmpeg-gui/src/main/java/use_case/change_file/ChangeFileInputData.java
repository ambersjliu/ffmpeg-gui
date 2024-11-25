package use_case.change_file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ChangeFileInputData {
    @Getter
    private final String Path;
}
