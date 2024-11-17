package use_case.add_input_file;

import data_access.FFmpegService;
import entity.VideoAttributes;
import entity.VideoJob;
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

                AddInputFileOutputData outputData = new AddInputFileOutputData(format, false);
                this.addInputFileOutputBoundary.prepareSuccessView(outputData);
            }catch(IOException e){
                this.addInputFileOutputBoundary.prepareFailView("Invalid file");
            }
        }
    }

    private boolean isVideoFile(FFmpegProbeResult probeResult){
        boolean res = false;
        for(FFmpegStream stream : probeResult.getStreams()){
            if (stream.codec_type == FFmpegStream.CodecType.VIDEO) {
                res = true;
                break;
            }
        }
        return res;
    }
}
