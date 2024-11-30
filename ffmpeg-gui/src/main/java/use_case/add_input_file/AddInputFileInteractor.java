package use_case.add_input_file;

import java.io.IOException;

import attribute.AudioAttributes;
import attribute.VideoAttributes;
import data_access.FFmpegService;
import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import utils.Truncator;
import utils.Validator;

/**
 * Interactor for the add input file use case, implements the logic of the use case.
 */

@AllArgsConstructor
public class AddInputFileInteractor implements AddInputFileInputBoundary {
    private final FFmpegService ffmpegService;
    private final AddInputFileOutputBoundary addInputFileOutputBoundary;

    @Override
    public void execute(AddInputFileInputData inputFileData) {
        try {
            Validator.validateFilePath(inputFileData.getFilePath());
            final FFmpegProbeResult result = this.ffmpegService.probe(inputFileData.getFilePath());
            final FFmpegFormat format = result.getFormat();

            if (isVideoFile(result)) {
                final FFmpegStream videoStream = getVideoStream(result);
                final FFmpegStream audioStream = getAudioStream(result);
                final AddInputFileOutputVideoData addInputFileOutputVideoData = new AddInputFileOutputVideoData(
                        createVideoAttributes(videoStream), createAudioAttributes(audioStream),
                        format, inputFileData.getFilePath()
                );

                this.addInputFileOutputBoundary.prepareVideoSuccessView(addInputFileOutputVideoData);
            }
            else if (isAudioFile(result)) {
                final FFmpegStream audioStream = getAudioStream(result);
                final AddInputFileOutputAudioData addInputFileOutputAudioData = new AddInputFileOutputAudioData(
                        createAudioAttributes(audioStream), format, inputFileData.getFilePath()
                );

                this.addInputFileOutputBoundary.prepareAudioSuccessView(addInputFileOutputAudioData);
            }
            else {
                throw new BadFileException();
            }

        }
        catch (IOException | BadFileException exception) {
            this.addInputFileOutputBoundary.prepareFailView("Invalid file");
        }

    }

    private FFmpegStream getAudioStream(FFmpegProbeResult probeResult) {
        FFmpegStream audioStream = null;
        for (FFmpegStream stream : probeResult.getStreams()) {
            if (stream.codec_type == FFmpegStream.CodecType.AUDIO) {
                audioStream = stream;
            }
        }
        return audioStream;
    }

    private FFmpegStream getVideoStream(FFmpegProbeResult probeResult) {
        FFmpegStream videoStream = null;
        for (FFmpegStream stream : probeResult.getStreams()) {
            if (stream.codec_type == FFmpegStream.CodecType.VIDEO) {
                videoStream = stream;
            }
        }
        return videoStream;
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

    private AudioAttributes createAudioAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        return new AudioAttributes(ffmpegStream);
    }

    private VideoAttributes createVideoAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        final VideoAttributes res = new VideoAttributes(ffmpegStream);
        // Truncate FPS
        final double currFps = res.getFps();
        res.setFps(Truncator.truncate(currFps));
        return res;
    }
}
