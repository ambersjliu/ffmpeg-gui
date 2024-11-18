package use_case.add_input_file;

import data_access.FFmpegService;
import exceptions.InvalidExecutableException;
import lombok.SneakyThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ResourcePathUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class AddInputFileTest {
    String validVideoPath;
    String validAudioPath;
    String invalidVideoPath;
    String invalidAudioPath;
    FFmpegService ffmpegService;

    @BeforeEach
    @SneakyThrows
    public void setup() throws URISyntaxException, IOException, InvalidExecutableException {
        final String ffmpegPath = "C:/PATH_programs/ffmpeg.exe";
        final String ffprobePath = "C:/PATH_programs/ffprobe.exe";
        invalidVideoPath = ResourcePathUtil.getResourceFilePath("test_invalid.py");
        invalidAudioPath = invalidVideoPath;
        validVideoPath = ResourcePathUtil.getResourceFilePath("test_video.webm");
        validVideoPath = ResourcePathUtil.getResourceFilePath("test_video.mp4");
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
        Assertions.assertNotNull(validAudioPath);
        Assertions.assertNotNull(invalidAudioPath);
        System.out.println("Valid Audio Path: " + validAudioPath);
        System.out.println("Invalid Audio Path: " + invalidAudioPath);
        Assertions.assertNotNull(invalidVideoPath);
        Assertions.assertNotNull(validVideoPath);
        System.out.println("Valid Video Path: " + validVideoPath);
        System.out.println("Invalid Video Path: " + invalidVideoPath);
    }

    /**
     * Helps you test that the FFmpegService object was initialized correctly.
     */
    @Test
    public void ffmpegServiceTest(){
        Assertions.assertNotNull(ffmpegService);
        Assertions.assertNotNull(ffmpegService.getFfmpeg());
        Assertions.assertNotNull(ffmpegService.getFfprobe());
    }


// TODO: ALL THE TESTS NEED TO BE REWRITTEN TO REFLECT THE TWO DIFFERENT VIEWS WE'RE SHOWING IN OUTPUTBOUNDARY

//    @Test
//    @SneakyThrows
//    public void successTest(){
//        String path = validPath;
//        AddInputFileData inputFileData = new AddInputFileData(path);
//        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
//            @Override
//            public void prepareSuccessView(AddInputFileOutputData outputData) {
//                Assert.assertNotNull(outputData);
//                Assert.assertNotNull(outputData.getFfmpegFormat());
//                Assert.assertFalse(outputData.isUseCaseFailed());
//                // Assert that ffprobe is returning correct information about the correct file
//                Assert.assertEquals(validPath, outputData.getFfmpegFormat().filename);
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                Assert.fail("Should not fail unless test file is missing from test/resources folder.");
//            }
//        };
//        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
//        interactor.execute(inputFileData);
//    }
//
//    @Test
//    public void failureInvalidFileTest(){
//        String path = invalidPath;
//        AddInputFileData inputFileData = new AddInputFileData(path);
//        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
//
//            @Override
//            public void prepareSuccessView(AddInputFileOutputData outputData) {
//                Assert.fail("Should not succeed, since we shouldn't be able to accept this file as input.");
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                Assert.assertEquals("Invalid file", errorMessage);
//            }
//        };
//        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
//        interactor.execute(inputFileData);
//    }
//
//    @Test
//    public void failureNullInputTest(){
//        String path = null;
//        AddInputFileData inputFileData = new AddInputFileData(path);
//        AddInputFileOutputBoundary outputBoundary = new AddInputFileOutputBoundary() {
//
//            @Override
//            public void prepareSuccessView(AddInputFileOutputData outputData) {
//                Assert.fail("Should not succeed, since we cannot perform operations with a null path");
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                Assert.assertEquals("Please add a file", errorMessage);
//            }
//        };
//        AddInputFileInputBoundary interactor = new AddInputFileInteractor(ffmpegService, outputBoundary);
//        interactor.execute(inputFileData);
//    }

}
