package use_case.get_paths_and_init;

import data_access.FFmpegService;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetPathsAndInitTest {
    FFmpegService ffmpegService;

    @Before
    public void setup(){
        ffmpegService = new FFmpegService();
    }

    @Test
    @SneakyThrows
    public void successTest(){
        // Put the correct paths to your ffmpeg and ffprobe here
        String ffmpegPath = "C:/ProgramData/chocolatey/bin/ffmpeg.exe";
        String ffprobePath = "C:/ProgramData/chocolatey/bin/ffprobe.exe";
        GetPathsAndInitData inputData = new GetPathsAndInitData(ffmpegPath, ffprobePath);
        GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary(){
            @Override
            @SneakyThrows
            public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                Assert.assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assert.fail("Not supposed to fail");
            }
        };

        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        interactor.execute(inputData);
        Assert.assertNotNull(ffmpegService);
        Assert.assertNotNull(ffmpegService.getFfmpeg());
        Assert.assertNotNull(ffmpegService.getFfprobe());
    }

    @Test
    @SneakyThrows
    public void failureInvalidFileTest(){
        // Put paths to an incorrect file and a correct file (or both incorrect lol)
        String path1 = "C:/ProgramData/chocolatey/bin/ffmpeg.exe";
        String path2 = "C:/Users/yinmi/OneDrive/Desktop/test.txt";
        GetPathsAndInitData inputData = new GetPathsAndInitData(path1, path2);
        GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary(){

            @Override
            public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                Assert.fail("Should not succeed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // check that we got the correct reason for failing
                Assert.assertEquals("Invalid file type", errorMessage);
            }
        };

        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        interactor.execute(inputData);
    }

    @Test
    @SneakyThrows
    public void failureInvalidExecutableTest(){
        // Put invalid paths to executables here
        String path1 = "C:\\Logic 2010\\Logic 2010.exe";
        String path2 = "C:\\Logic 2010\\Logic 2010.exe";
        GetPathsAndInitData inputData = new GetPathsAndInitData(path1, path2);
        GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary(){

            @Override
            public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                Assert.fail("Should not succeed");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // check that we got the correct reason for failing
                Assert.assertEquals("Invalid executable", errorMessage);
            }
        };

        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        interactor.execute(inputData);
    }
}
