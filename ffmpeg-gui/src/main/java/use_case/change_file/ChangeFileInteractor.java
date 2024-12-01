package use_case.change_file;

import lombok.AllArgsConstructor;

/**
 * Interactor for change file.
 */

@AllArgsConstructor
public class ChangeFileInteractor implements ChangeFileInputBoundary {
    private ChangeFileOutputBoundary changeFilePresenter;

    @Override
    public void execute(ChangeFileInputData changeFileInputData) {
        final String path = changeFileInputData.getPath();
        final ChangeFileOutputData changeFileOutputData = new ChangeFileOutputData(path, false);
        changeFilePresenter.prepareSuccessView(changeFileOutputData);
    }
}
