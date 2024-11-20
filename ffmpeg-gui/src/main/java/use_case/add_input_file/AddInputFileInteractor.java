package use_case.add_input_file;

import data_access.FFmpegService;
import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;

import java.io.IOException;

@AllArgsConstructor
public class AddInputFileInteractor implements AddInputFileInputBoundary {
    private final FFmpegService ffmpegService;
    private final AddInputFileOutputBoundary addInputFileOutputBoundary;

    @Override
    public void execute(AddInputFileInputData inputFileData) {
        if (inputFileData.getFilePath() == null || inputFileData.getFilePath().trim().isEmpty()) {
            this.addInputFileOutputBoundary.prepareFailView("Please add a file");
            return;
        }
        try {
            FFmpegProbeResult result = this.ffmpegService.probe(inputFileData.getFilePath());
            FFmpegFormat format = result.getFormat();

            if (isVideoFile(result)) {
                final AddInputFileOutputVideoData addInputFileOutputVideoData = new AddInputFileOutputVideoData(
                        getVideoStream(result), getAudioStream(result), format, inputFileData.getFilePath()
                );

                this.addInputFileOutputBoundary.prepareVideoSuccessView(addInputFileOutputVideoData);
            } else if (isAudioFile(result)) {
                final AddInputFileOutputAudioData addInputFileOutputAudioData = new AddInputFileOutputAudioData(
                        getAudioStream(result), format, inputFileData.getFilePath()
                );

                this.addInputFileOutputBoundary.prepareAudioSuccessView(addInputFileOutputAudioData);
            } else {
                throw new IOException();
            }

        } catch (IOException e) {
            this.addInputFileOutputBoundary.prepareFailView("Invalid file");
        }

    }

    private FFmpegStream getAudioStream(FFmpegProbeResult probeResult) {
        for (FFmpegStream stream : probeResult.getStreams()) {
            if (stream.codec_type == FFmpegStream.CodecType.AUDIO) {
                return stream;
            }
        }
        return null;
    }

    private FFmpegStream getVideoStream(FFmpegProbeResult probeResult) {
        for (FFmpegStream stream : probeResult.getStreams()) {
            if (stream.codec_type == FFmpegStream.CodecType.VIDEO) {
                return stream;
            }
        }
        return null;
    }

    private boolean isVideoFile(FFmpegProbeResult probeResult) {
        boolean res = false;
        for (FFmpegStream stream : probeResult.getStreams()) {
            if (stream.codec_type == FFmpegStream.CodecType.VIDEO) {
                res = true;
                break;
            }
        }
        return res;
    }

    private boolean isAudioFile(FFmpegProbeResult probeResult) {
        boolean res = false;
        for (FFmpegStream stream : probeResult.getStreams()) {
            if (stream.codec_type == FFmpegStream.CodecType.AUDIO) {
                res = true;
                break;
            }
        }
        return res;
    }
}
