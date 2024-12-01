package use_case.change_file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Input data for change file use case.
 */

@AllArgsConstructor
@Getter
public class ChangeFileInputData {
    private final String path;
}
