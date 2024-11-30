package use_case.add_input_file;

import data_access.FFmpegService;

import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.apache.commons.lang3.math.Fraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    FFmpegProbeResult result;

    @BeforeEach
    void setup(){

    }

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

        FFmpegProbeResult videoResult = new FFmpegProbeResult();

        // Make a detailed fake video stream
        FFmpegStream videoStream = new FFmpegStream();
        videoStream.width = 1;
        videoStream.height = 1;
        videoStream.avg_frame_rate = Fraction.getFraction((double) 4 /5);
        videoStream.bit_rate = 123;
        videoStream.codec_name = "hevc";
        videoStream.codec_type = FFmpegStream.CodecType.VIDEO;

        FFmpegStream audioStream = new FFmpegStream();
        audioStream.codec_type = FFmpegStream.CodecType.AUDIO;

        List<FFmpegStream> streams = new ArrayList<>();

        streams.add(videoStream);
        streams.add(audioStream);

        videoResult.format = new FFmpegFormat();
        videoResult.streams = streams;

        Mockito.when(ffmpegService.probe(anyString())).thenReturn(videoResult);
        AddInputFileInputData inputData = new AddInputFileInputData("blah");
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }

    @Test
    void failureInvalidVideoFile() throws IOException {
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

            }
        };
        FFmpegProbeResult videoResult = new FFmpegProbeResult();

        // Make a detailed fake video stream
        FFmpegStream videoStream = new FFmpegStream();
        videoStream.codec_type = FFmpegStream.CodecType.VIDEO;
        videoStream.width = 1;
        videoStream.height = 1;
        videoStream.avg_frame_rate = Fraction.getFraction((double) 4 /5);
        videoStream.bit_rate = 123;
        videoStream.codec_name = "hevc";

        List<FFmpegStream> streams = new ArrayList<>();
        streams.add(videoStream);

        videoResult.format = new FFmpegFormat();
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

        // Make a detailed fake audio stream
        FFmpegStream audioStream = new FFmpegStream();
        audioStream.codec_type = FFmpegStream.CodecType.AUDIO;
        audioStream.bit_rate = 123;
        audioStream.codec_name = "flac";
        audioStream.sample_rate = 44100;
        audioStream.channels = 2;

        List<FFmpegStream> streams = new ArrayList<>();

        streams.add(audioStream);

        audioResult.format = new FFmpegFormat();
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
                Assertions.assertEquals("Invalid file", errorMessage);
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
                Assertions.assertEquals("Invalid file", errorMessage);
            }
        };
        AddInputFileInputData inputData = new AddInputFileInputData(null);
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputData);
    }


}
