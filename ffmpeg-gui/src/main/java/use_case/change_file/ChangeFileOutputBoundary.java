package use_case.change_file;

public interface ChangeFileOutputBoundary {

    void prepareSuccessView(ChangeFileOutputData changeFileOutputData);

    void prepareFailView(String errorMessage);
}
