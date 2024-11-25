package interface_adapter.change_file;

import lombok.AllArgsConstructor;
import use_case.change_file.ChangeFileInputBoundary;
import use_case.change_file.ChangeFileInputData;

@AllArgsConstructor
public class ChangeFileController {
    private final ChangeFileInputBoundary changeFileInteractor;

    public void execute(String path){
        final ChangeFileInputData changeFileInputData = new ChangeFileInputData(path);
        changeFileInteractor.execute(changeFileInputData);
    }
}
