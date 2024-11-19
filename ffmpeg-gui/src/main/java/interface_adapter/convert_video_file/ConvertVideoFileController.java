package interface_adapter.convert_video_file;

import use_case.convert_video.ConvertVideoFileData;
import use_case.convert_video.ConvertVideoFileInputBoundary;

public class ConvertVideoFileController {
    private final ConvertVideoFileInputBoundary convertVideoFileInteractor;

    public ConvertVideoFileController(ConvertVideoFileInputBoundary convertVideoFileInteractor) {
        this.convertVideoFileInteractor = convertVideoFileInteractor;
    }

    public void execute(){
        // final ConvertVideoFileData videoFileData= new ConvertVideoFileData();
        // TODO: add all parameters to ConvertVideoFileData.java, and then give necessary parameters to function call.
        // convertVideoFileInteractor.execute(videoFileData);
    }
}
