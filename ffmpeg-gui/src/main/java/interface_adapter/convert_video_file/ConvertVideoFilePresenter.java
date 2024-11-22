package interface_adapter.convert_video_file;

import use_case.convert_video.ConvertVideoFileOutputBoundary;
import use_case.convert_video.ConvertVideoFileOutputData;

public class ConvertVideoFilePresenter implements ConvertVideoFileOutputBoundary {
    public ConvertVideoFilePresenter() {

    }
    @Override
    public void prepareSuccessView(ConvertVideoFileOutputData outputData) {
        System.out.println("Success!");
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
