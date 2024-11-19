package use_case.add_input_file;

import data_access.FFmpegService;

import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class AddInputFileTest {

    @Mock
    private FFmpegService ffmpegService;

    @Test
    void successValidVideoFile() throws IOException {
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareVideoSuccessView(AddInputFileOutputVideoData outputData) {

            }

            @Override
            public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
                Assertions.fail("Should not show audio success view");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("Should not fail");
            }
        };
        // todo make the video result: has a video stream, has an audio stream
        FFmpegProbeResult videoResult = new FFmpegProbeResult();

        FFmpegStream videoStream = new FFmpegStream();
        videoStream.codec_type = FFmpegStream.CodecType.VIDEO;

        FFmpegStream audioStream = new FFmpegStream();
        audioStream.codec_type = FFmpegStream.CodecType.AUDIO;

        List<FFmpegStream> streams = new ArrayList<>();

        streams.add(videoStream);
        streams.add(audioStream);

        FFmpegFormat format = new FFmpegFormat();

        videoResult.format = format;
        videoResult.streams = streams;

        Mockito.when(ffmpegService.probe(anyString())).thenReturn(videoResult);
        AddInputFileInputData inputData = new AddInputFileInputData("blah");
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }

    @Test
    void successValidAudioFile() throws IOException {
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareVideoSuccessView(AddInputFileOutputVideoData outputData) {
                Assertions.fail("Should not show video success view");
            }

            @Override
            public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail("Should not fail");
            }
        };
        FFmpegProbeResult audioResult = new FFmpegProbeResult();

        FFmpegStream audioStream = new FFmpegStream();
        audioStream.codec_type = FFmpegStream.CodecType.AUDIO;

        List<FFmpegStream> streams = new ArrayList<>();

        streams.add(audioStream);

        FFmpegFormat format = new FFmpegFormat();

        audioResult.format = format;
        audioResult.streams = streams;

        Mockito.when(ffmpegService.probe(anyString())).thenReturn(audioResult);
        AddInputFileInputData inputData = new AddInputFileInputData("blah");
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }


    @Test
    void invalidFileShouldPrepareFailView() throws IOException {
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareVideoSuccessView(AddInputFileOutputVideoData outputData) {
                Assertions.fail("Should not show video success view");
            }

            @Override
            public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
                Assertions.fail("Should not show audio success view");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Invalid file", errorMessage);
            }
        };
        FFmpegProbeResult result = new FFmpegProbeResult();
        Mockito.when(ffmpegService.probe(anyString())).thenReturn(result);
        AddInputFileInputData inputData = new AddInputFileInputData("blah");
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }

    @Test
    void ioExceptionShouldPrepareFailView() throws IOException {
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareVideoSuccessView(AddInputFileOutputVideoData outputData) {
                Assertions.fail("Should not show video success view");
            }

            @Override
            public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
                Assertions.fail("Should not show audio success view");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Invalid file", errorMessage);
            }
        };
        Mockito.when(ffmpegService.probe(anyString())).thenThrow(new IOException());
        AddInputFileInputData inputData = new AddInputFileInputData("blah");
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }

    @Test
    void failureEmptyPath(){
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareVideoSuccessView(AddInputFileOutputVideoData outputData) {
                Assertions.fail("Should not show video success view");
            }

            @Override
            public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
                Assertions.fail("Should not show audio success view");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Please add a file", errorMessage);
            }
        };
        AddInputFileInputData inputData = new AddInputFileInputData("");
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }

    @Test
    void failureNullPath(){
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareVideoSuccessView(AddInputFileOutputVideoData outputData) {
                Assertions.fail("Should not show video success view");
            }

            @Override
            public void prepareAudioSuccessView(AddInputFileOutputAudioData outputData) {
                Assertions.fail("Should not show audio success view");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Please add a file", errorMessage);
            }
        };
        AddInputFileInputData inputData = new AddInputFileInputData(null);
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }


}
