package use_case.convert_video;

import data_access.FFmpegService;
import entity.AudioAttributes;
import entity.VideoAttributes;
import entity.VideoJob;
import lombok.AllArgsConstructor;

import java.time.Duration;

@AllArgsConstructor
public class ConvertVideoInteractor implements ConvertVideoFileInputBoundary {

    private final ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary;
    private final FFmpegService ffmpegService;

    /**
     * Given input of videoFileData, containing a path to the file to be converted, path to save location
     * And parameters of the conversion. It will be saved the converted file at the specified save location
     */
    @Override
    public void execute(ConvertVideoFileData videoFileData) {
        final String inputFilePath = videoFileData.getInputFileName();
        final String outputFilePath = videoFileData.getOutputFileName();
//        final Duration duration = videoFileData.getDuration(); // TODO resolve if duration is Double or Duration ASAP
//        final Duration startTime = videoFileData.getStartTime();
        final VideoAttributes videoAttributes = videoFileData.getVideoAttributes();
        final AudioAttributes audioAttributes = videoFileData.getAudioAttributes();

        if(inputFilePath == null || outputFilePath == null){
            this.convertVideoFileOutputBoundary.prepareFailView("Please choose file and save directory.");
            return;
        }

        try {
//            VideoJob job = new VideoJob(inputFilePath, outputFilePath, duration, startTime, videoAttributes, audioAttributes);
//            this.ffmpegService.convertVideo(job);
        } catch (Exception e) {  //TODO write proper catch blocks for specific errors.
            this.convertVideoFileOutputBoundary.prepareFailView("Unexpected error occurred.");
        }

    }

}
