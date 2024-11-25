package use_case.change_file;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeFileInteractor implements ChangeFileInputBoundary{
    private ChangeFileOutputBoundary changeFilePresenter;
    @Override
    public void execute(ChangeFileInputData changeFileInputData) {
        final String Path = changeFileInputData.getPath();
        final ChangeFileOutputData changeFileOutputData = new ChangeFileOutputData(Path, false);
        changeFilePresenter.prepareSuccessView(changeFileOutputData);
    }
}
