package use_case.get_paths_and_init;

import data_access.FFmpegService;
import exceptions.BadFileException;
import exceptions.InvalidExecutableException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.Validator;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class GetPathsAndInitTest {
    @Mock
    FFmpegService ffmpegService;

    @Test
    @SneakyThrows
    public void successTest(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {

            GetPathsAndInitData inputData = new GetPathsAndInitData(".", ".");
            GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary() {
                @Override
                @SneakyThrows
                public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                    Assertions.assertFalse(outputData.isUseCaseFailed());
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    Assertions.fail("Not supposed to fail");
                }
            };
            Mockito.doNothing().when(ffmpegService).validateBinaries();
            GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
            interactor.execute(inputData);
        }
    }

    @Test
    @SneakyThrows
    public void validateBinariesIOExceptionShouldPrepareFailView(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {

            GetPathsAndInitData inputData = new GetPathsAndInitData(".", ".");
            GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary() {

                @Override
                public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                    Assertions.fail("Should not succeed");
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    // check that we got the correct reason for failing
                    Assertions.assertEquals("Invalid file type", errorMessage);
                }
            };
            Mockito.doThrow(new IOException()).when(ffmpegService).validateBinaries();
            GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
            interactor.execute(inputData);
        }
    }

    @Test
    @SneakyThrows
    public void doesEndOfPathContainIOExceptionShouldPrepareFailView(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {

            GetPathsAndInitData inputData = new GetPathsAndInitData(".", ".");
            GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary() {

                @Override
                public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                    Assertions.fail("Should not succeed");
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    // check that we got the correct reason for failing
                    Assertions.assertEquals("Invalid file type", errorMessage);
                }
            };
            mocked.when(()->Validator.doesEndOfPathContain(Mockito.anyString(), Mockito.anyString())).thenThrow(new IOException());
            GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
            interactor.execute(inputData);
        }
    }

    @Test
    @SneakyThrows
    public void failureInvalidExecutableExceptionAtInitializationTest(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {

            GetPathsAndInitData inputData = new GetPathsAndInitData(".", ".");
            GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary() {

                @Override
                public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                    Assertions.fail("Should not succeed");
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    // check that we got the correct reason for failing
                    Assertions.assertEquals("Invalid executable", errorMessage);
                }
            };
            Mockito.doThrow(new InvalidExecutableException()).when(ffmpegService).validateBinaries();
            GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
            interactor.execute(inputData);
        }
    }

    @Test
    @SneakyThrows
    public void failureEmptyPaths(){

        GetPathsAndInitData inputData = new GetPathsAndInitData("","");
        GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary(){

            @Override
            public void prepareSuccessView(GetPathsAndInitOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Please add both paths!", errorMessage);
            }
        };
        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        interactor.execute(inputData);
    }

    @Test
    @SneakyThrows
    public void failureNullPaths(){
        GetPathsAndInitData inputData = new GetPathsAndInitData(null,null);
        GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary(){

            @Override
            public void prepareSuccessView(GetPathsAndInitOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                Assertions.assertEquals("Please add both paths!", errorMessage);
            }
        };
        GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
        interactor.execute(inputData);
    }

    @Test
    @SneakyThrows
    public void anyOtherExceptionShouldPrepareFailView(){
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            GetPathsAndInitData inputData = new GetPathsAndInitData(".", ".");
            GetPathsAndInitOutputBoundary getPathsAndInitOutputBoundary = new GetPathsAndInitOutputBoundary() {

                @Override
                public void prepareSuccessView(GetPathsAndInitOutputData outputData) {
                    Assertions.fail("Should not succeed");
                }

                @Override
                public void prepareFailView(String errorMessage) {
                    // check that we got the correct reason for failing
                    Assertions.assertEquals("Unexpected error happened", errorMessage);
                }
            };
            Mockito.doThrow(new RuntimeException()).when(ffmpegService).validateBinaries();
            GetPathsAndInitInputBoundary interactor = new GetPathsAndInitInteractor(getPathsAndInitOutputBoundary, ffmpegService);
            interactor.execute(inputData);
        }
    }
}
