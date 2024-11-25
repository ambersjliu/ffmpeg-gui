package use_case.add_input_file;

import data_access.FFmpegService;
import attribute.AudioAttributes;
import attribute.VideoAttributes;
import exceptions.BadFileException;
import lombok.AllArgsConstructor;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import utils.Validator;

import java.io.IOException;

@AllArgsConstructor
public class AddInputFileInteractor implements AddInputFileInputBoundary {
    private final FFmpegService ffmpegService;
    private final AddInputFileOutputBoundary addInputFileOutputBoundary;

    @Override
    public void execute(AddInputFileInputData inputFileData) {
        try {
            Validator.validateFilePath(inputFileData.getFilePath());
            FFmpegProbeResult result = this.ffmpegService.probe(inputFileData.getFilePath());
            FFmpegFormat format = result.getFormat();

            if (isVideoFile(result)) {
                final FFmpegStream videoStream = getVideoStream(result);
                final FFmpegStream audioStream = getAudioStream(result);
                final AddInputFileOutputVideoData addInputFileOutputVideoData = new AddInputFileOutputVideoData(
                        createVideoAttributes(videoStream), createAudioAttributes(audioStream),
                        format, inputFileData.getFilePath()
                );

                this.addInputFileOutputBoundary.prepareVideoSuccessView(addInputFileOutputVideoData);
            } else if (isAudioFile(result)) {
                final FFmpegStream audioStream = getAudioStream(result);
                final AddInputFileOutputAudioData addInputFileOutputAudioData = new AddInputFileOutputAudioData(
                        createAudioAttributes(audioStream), format, inputFileData.getFilePath()
                );

                this.addInputFileOutputBoundary.prepareAudioSuccessView(addInputFileOutputAudioData);
            } else {
                throw new IOException();
            }

        } catch (IOException | BadFileException e) {
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

    private AudioAttributes createAudioAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        return new AudioAttributes(ffmpegStream);
    }

    private VideoAttributes createVideoAttributes(FFmpegStream ffmpegStream) throws BadFileException {
        return new VideoAttributes(ffmpegStream);
    }
}
