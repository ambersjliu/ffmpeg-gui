package use_case.convert_video;

public interface ConvertVideoFileOutputBoundary {

    void prepareSuccessView(ConvertVideoFileOutputData outputData);

    void prepareFailView(String errorMessage);

}
