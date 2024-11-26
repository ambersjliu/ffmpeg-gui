package use_case.convert_audio;

import use_case.convert_video.ConvertVideoFileOutputData;

public interface ConvertAudioFileOutputBoundary {

    void prepareSuccessView(ConvertAudioFileOutputData outputData);

    void prepareFailView(String errorMessage);
}
