package use_case.add_input_file;

import data_access.FFmpegService;
import interface_adapter.add_input_file.AddInputFilePresenter;
import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

import java.io.IOException;

@AllArgsConstructor
public class AddInputFileInteractor implements AddInputFileInputBoundary{
    private final FFmpegService ffmpegService;
    private final AddInputFileOutputBoundary addInputFileOutputBoundary;


    @Override
    public void execute(AddInputFileData inputFileData) {
        if (inputFileData.getFilePath() == null || inputFileData.getFilePath().trim().isEmpty()) {
            this.addInputFileOutputBoundary.prepareFailView("Please add a file");
        } else{
            try{
                FFmpegProbeResult result = this.ffmpegService.getFfprobe().probe(inputFileData.getFilePath());
                FFmpegFormat format = result.getFormat();
                FFmpegStream stream = result.getStreams().get(0);
                //todo i actually have to make the entity object now
                AddInputFileOutputData outputData = new AddInputFileOutputData(format, false);
                this.addInputFileOutputBoundary.prepareSuccessView(outputData);
            }catch(IOException e){
                this.addInputFileOutputBoundary.prepareFailView("Invalid file");
            }
        }
    }
}
