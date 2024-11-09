package use_case.get_paths_and_init;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

public class GetPathsAndInitTest {
    @Test
    @SneakyThrows
    public void successTest(){
        // Put the correct paths to your ffmpeg and ffprobe here
        String ffmpegPath = "C:/PATH_programs/ffmpeg.exe";
        String ffprobePath = "C:/PATH_programs/ffprobe.exe";
        GetPathsAndInitData inputData = new GetPathsAndInitData(ffmpegPath, ffprobePath);
        GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary(){
            @Override
            @SneakyThrows
            public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                // Make sure none of the objects are null
                Assert.assertNotNull(outputData.getFfmpegService());
                Assert.assertNotNull(outputData.getFfmpegService().getFfmpeg());
                Assert.assertNotNull(outputData.getFfmpegService().getFfprobe());
                // Make sure the ffmpeg and ffprobe executables are guaranteed to actually be ffmpeg and ffprobe
                Assert.assertTrue(outputData.getFfmpegService().getFfmpeg().isFFmpeg());
                Assert.assertTrue(outputData.getFfmpegService().getFfprobe().isFFprobe());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assert.fail("Not supposed to fail");
            }
        };

        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary);
        interactor.execute(inputData);
    }

    @Test
    @SneakyThrows
    public void failureInvalidFileTest(){
        // Put paths to an incorrect file and a correct file (or both incorrect lol)
        String path1 = "C:/PATH_programs/ffmpeg.exe";
        String path2 = "C:/Users/amber/random.txt";
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

        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary);
        interactor.execute(inputData);
    }

    @Test
    @SneakyThrows
    public void failureInvalidExecutableTest(){
        // TODO someone else write this its good practice
    }
}
