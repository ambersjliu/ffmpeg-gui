package use_case.convert_video;

import data_access.FFmpegService;
import attribute.AudioAttributes;
import attribute.VideoAttributes;
import entity.VideoJob;
import exceptions.BadFileException;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.Validator;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class ConvertVideoTest {

    @Mock
    FFmpegService mockFFmpegService;

    @Mock
    ConvertVideoFileOutputBoundary mockConvertVideoFileOutputBoundary;

    @Mock
    ConvertVideoFileData convertVideoFileData;


    @InjectMocks
    @Spy
    ConvertVideoFileInteractor convertVideoFileInteractor;

    @Test
    void executeHappyPath(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            Mockito.doNothing().when(mockFFmpegService).convertVideo(any());
            Mockito.doReturn(new VideoJob()).when(convertVideoFileInteractor).createVideoJob(any());
            convertVideoFileInteractor.execute(convertVideoFileData);
            Mockito.verify(mockConvertVideoFileOutputBoundary).prepareSuccessView(any());
        }
    }

    @Test
    void executeWithBadFileExceptionShouldPrepareFailView(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            mocked.when(()->Validator.validateFilePath(any())).thenThrow(new BadFileException());
            convertVideoFileInteractor.execute(convertVideoFileData);
            Mockito.verify(mockConvertVideoFileOutputBoundary).prepareFailView("Invalid or null file path");
        }
    }


    @Test
    void executeWithIllegalArgumentExceptionShouldPrepareFailView(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            Mockito.doReturn(new VideoJob()).when(convertVideoFileInteractor).createVideoJob(any());
            Mockito.doThrow(new IllegalArgumentException()).when(mockFFmpegService).convertVideo(any());
            convertVideoFileInteractor.execute(convertVideoFileData);
            Mockito.verify(mockConvertVideoFileOutputBoundary).prepareFailView("Error occurred when processing: " +
                    "possibly invalid codec/format combination");
        }
    }


    @Test
    void executeWithOtherExceptionShouldPrepareFailView(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            Mockito.doReturn(new VideoJob()).when(convertVideoFileInteractor).createVideoJob(any());
            Mockito.doThrow(new RuntimeException()).when(mockFFmpegService).convertVideo(any());
            convertVideoFileInteractor.execute(convertVideoFileData);
            Mockito.verify(mockConvertVideoFileOutputBoundary).prepareFailView("Unexpected error occurred.");
        }
    }

    /**
     * This is NOT a comprehensive test!!! Treat it like a sandbox if you want
     * to try out the conversion service or to inspect issues with certain formats/codecs.
     * @throws IOException If ffmpeg/ffprobe cannot be initialized
     * @throws BadFileException If the file is missing streams or corrupted for other reasons
     */
    @Disabled("This was manually tested with local files, not meant for automated tests")
    @Test
    public void manualTestConvertVideo() throws IOException, BadFileException {
        FFmpegService ffmpegService = new FFmpegService();
        ffmpegService.initialize("ffmpeg path", "ffprobe path");

        ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary = new ConvertVideoFileOutputBoundary() {
            @Override
            public void prepareSuccessView(ConvertVideoFileOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail();
            }
        };

        String inputFilePath = "path to local file";
        String outputFilePath = "desired output file";

        // Replace with your intended output values
        int targetWidth = 1000;
        int targetHeight = 800;
        double targetFps = 15;
        // Bitrate is in bps by default so you must multiply by 1000
        long targetBitRate = 2000 * 1000;
        String targetFormat = "mov";
        String targetEncoder = "mpeg4";

        int targetAudioBitrate = 160 * 1000;
        int targetSampleRate = 32000;
        int targetChannels = 2;
        String targetAudioCodec = "mp3";

        double startTime = 0;
        double duration = 2;

        FFmpegProbeResult res = ffmpegService.probe(inputFilePath);
//        FFmpegStream vstream = res.getStreams().get(0);
//        FFmpegStream aStream = res.getStreams().get(1);
//        VideoAttributes testAttributes = new VideoAttributes(vstream);
//        AudioAttributes testAudioAttributes = new AudioAttributes(aStream);

        VideoAttributes videoAttributes = new VideoAttributes(targetWidth, targetHeight, targetFps, targetBitRate, targetEncoder);
        AudioAttributes audioAttributes = new AudioAttributes(targetAudioBitrate, targetSampleRate, targetChannels, targetAudioCodec);

        ConvertVideoFileData inputData = new ConvertVideoFileData(inputFilePath, outputFilePath, targetFormat, startTime, duration, videoAttributes, audioAttributes);
        ConvertVideoFileInputBoundary interactor = new ConvertVideoFileInteractor(convertVideoFileOutputBoundary, ffmpegService);
        interactor.execute(inputData);
        // After execution, check by inspection that the file properties are what you expect

    }
}
