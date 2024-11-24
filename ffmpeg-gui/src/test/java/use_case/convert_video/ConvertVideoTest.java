package use_case.convert_video;

import data_access.FFmpegService;
import entity.AudioAttributes;
import entity.VideoAttributes;
import exceptions.BadFileException;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ConvertVideoTest {
    @Test
    public void testConvertVideo() throws IOException, BadFileException {
        FFmpegService ffmpegService = new FFmpegService();
        ffmpegService.initialize("C:/PATH_programs/ffmpeg.exe", "C:/PATH_programs/ffprobe.exe");

        ConvertVideoFileOutputBoundary convertVideoFileOutputBoundary = new ConvertVideoFileOutputBoundary() {
            @Override
            public void prepareSuccessView(ConvertVideoFileOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.fail();
            }
        };

        String inputFilePath = "C:\\Users\\amber\\Documents\\UofT\\YEAR2\\CSC207\\ffmpeg-gui\\ffmpeg-gui\\src\\test\\resources\\test_video.webm";
        String outputFilePath = "C:\\Users\\amber\\Documents\\UofT\\YEAR2\\CSC207\\ffmpeg-gui\\ffmpeg-gui\\src\\test\\resources\\test_output.mp4";

        int targetWidth = 1000;
        int targetHeight = 800;
        double targetFps = 15;
        long targetBitRate = 0;
        String targetFormat = "mp4";
        String targetEncoder = "h264";

        int targetAudioBitrate = 160 * 1000;
        int targetSampleRate = 44100;
        int targetChannels = 1;
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

    }
}
