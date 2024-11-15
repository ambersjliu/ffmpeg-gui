package use_case.add_input_file;

import data_access.FFmpegService;
import exceptions.InvalidExecutableException;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.ResourcePathUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class AddInputFileTest {
    String validPath;
    String invalidPath;
    FFmpegService ffmpegService;

    @Before
    @SneakyThrows
    public void setup() throws URISyntaxException, IOException, InvalidExecutableException {
        final String ffmpegPath = "C:/PATH_programs/ffmpeg.exe";
        final String ffprobePath = "C:/PATH_programs/ffprobe.exe";
        invalidPath = ResourcePathUtil.getResourceFilePath("test_invalid.py");
        validPath = ResourcePathUtil.getResourceFilePath("test_video.webm");
        ffmpegService = new FFmpegService();
        ffmpegService.initialize(ffmpegPath, ffprobePath);
    }

    /**
     * Helps you test if the paths are not null,
     * and are pointing to the correct files on your machine
     */
    @Test
    @SneakyThrows
    public void pathTest(){
        Assert.assertNotNull(validPath);
        Assert.assertNotNull(invalidPath);
        System.out.println("Valid Path: " + validPath);
        System.out.println("Invalid Path: " + invalidPath);
    }

    /**
     * Helps you test that the FFmpegService object was initialized correctly.
     */
    @Test
    public void ffmpegServiceTest(){
        Assert.assertNotNull(ffmpegService);
        Assert.assertNotNull(ffmpegService.getFfmpeg());
        Assert.assertNotNull(ffmpegService.getFfprobe());
    }

    @Test
    @SneakyThrows
    public void successTest(){
        String path = validPath;
        AddInputFileData inputFileData = new AddInputFileData(path);
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
            @Override
            public void prepareSuccessView(AddInputFileOutputData outputData) {
                Assert.assertNotNull(outputData);
                Assert.assertNotNull(outputData.getFfmpegFormat());
                Assert.assertFalse(outputData.isUseCaseFailed());
                // Assert that ffprobe is returning correct information about the correct file
                Assert.assertEquals(validPath, outputData.getFfmpegFormat().filename);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assert.fail("Should not fail unless test file is missing from test/resources folder.");
            }
        };
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputFileData);
    }

    @Test
    public void failureInvalidFileTest(){
        String path = invalidPath;
        AddInputFileData inputFileData = new AddInputFileData(path);
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {

            @Override
            public void prepareSuccessView(AddInputFileOutputData outputData) {
                Assert.fail("Should not succeed, since we shouldn't be able to accept this file as input.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assert.assertEquals("Invalid file", errorMessage);
            }
        };
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputFileData);
    }

    @Test
    public void failureNullInputTest(){
        String path = null;
        AddInputFileData inputFileData = new AddInputFileData(path);
        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {

            @Override
            public void prepareSuccessView(AddInputFileOutputData outputData) {
                Assert.fail("Should not succeed, since we cannot perform operations with a null path");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assert.assertEquals("Please add a file", errorMessage);
            }
        };
        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
        interactor.execute(inputFileData);
    }

}
