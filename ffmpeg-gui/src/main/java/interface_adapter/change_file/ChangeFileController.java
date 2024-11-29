package interface_adapter.change_file;

import lombok.AllArgsConstructor;
import use_case.change_file.ChangeFileInputBoundary;
import use_case.change_file.ChangeFileInputData;

/**
 * Controller for change file use case.
 */

@AllArgsConstructor
public class ChangeFileController {
    private final ChangeFileInputBoundary changeFileInteractor;

    /**
     * Executes the interactors use case.
     * @param path path to new file
     */

    public void execute(String path) {
        final ChangeFileInputData changeFileInputData = new ChangeFileInputData(path);
        changeFileInteractor.execute(changeFileInputData);
    }
}
