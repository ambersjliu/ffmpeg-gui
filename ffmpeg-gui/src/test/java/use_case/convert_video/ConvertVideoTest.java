package use_case.convert_video;

import data_access.FFmpegService;
import entity.AudioAttributes;
import entity.VideoAttributes;
import entity.VideoJob;
import exceptions.BadFileException;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class ConvertVideoTest {

//    @Mock
//    FFmpegService mockFFmpegService;
//
//    @Test
//    public void ffmpegServiceThrowsIllegalArgumentExceptionShouldFail() throws BadFileException {
//        ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary = new ConvertVideoFileOutputBoundary() {
//            @Override
//            public void prepareSuccessView(ConvertVideoFileOutputData outputData) {
//                Assertions.fail();
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                Assertions.assertEquals("Error occurred when processing: possibly invalid codec/format combination",
//                        errorMessage);
//            }
//        };
//
//        ConvertVideoFileData inputData = Mockito.mock(ConvertVideoFileData.class);
//
//        Mockito.doThrow(IllegalArgumentException.class)
//                .when(mockFFmpegService)
//                .convertVideo(Mockito.any(VideoJob.class));
//        ConvertVideoFileInteractor interactor = new ConvertVideoFileInteractor(convertVideoFileOutputBoundary, mockFFmpegService);
//        Mockito.doNothing().when(interactor).validateInput((Mockito.any(ConvertVideoFileData.class)));
//        Mockito.when(interactor.createVideoJob(Mockito.any(ConvertVideoFileData.class))).thenReturn(new VideoJob());
//        interactor.execute(inputData);
//    }

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
        ffmpegService.initialize("your ffmpeg location", "your ffprobe location");

        ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary = new ConvertVideoFileOutputBoundary() {
            @Override
            public void prepareSuccessView(ConvertVideoFileOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail();
            }
        };

        String inputFilePath = "input file path here";
        String outputFilePath = "output file path here";

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
