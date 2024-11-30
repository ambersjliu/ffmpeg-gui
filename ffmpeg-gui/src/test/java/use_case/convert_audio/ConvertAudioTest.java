package use_case.convert_audio;

import attribute.AudioAttributes;
import data_access.FFmpegService;
import entity.AudioJob;

import exceptions.BadFileException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import utils.Validator;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class ConvertAudioTest {
    @Mock
    FFmpegService mockFFmpegService;

    @Mock
    ConvertAudioFileOutputBoundary mockConvertAudioFileOutputBoundary;

    @Mock
    ConvertAudioFileData convertAudioFileData;


    @InjectMocks
    ConvertAudioFileInteractor convertAudioFileInteractor;

    ConvertAudioFileData inputData;

    @BeforeEach
    void setup(){
        // make a fake convertAudioFileData
        AudioAttributes attributes = new AudioAttributes(1,2,3, "hi");
        inputData = new ConvertAudioFileData(
                "bye", "bye", "mp3", 12.00, 19.00, attributes);
    }

    @Test
    void executeHappyPath() {
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            Mockito.doNothing().when(mockFFmpegService).convertAudio(any());
//            Mockito.doReturn(new AudioJob()).when(convertAudioFileInteractor).createAudioJob(any());
            convertAudioFileInteractor.execute(inputData);
            Mockito.verify(mockConvertAudioFileOutputBoundary).prepareSuccessView(any());
        }
    }

    @Test
    void executeWithBadFileExceptionShouldPrepareFailView() {
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
            mocked.when(() -> Validator.validateFilePath(any())).thenThrow(new BadFileException());
            convertAudioFileInteractor.execute(convertAudioFileData);
            Mockito.verify(mockConvertAudioFileOutputBoundary).prepareFailView("Invalid or null file path");
        }
    }


    @Test
    void executeWithIllegalArgumentExceptionShouldPrepareFailView() {
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
//            Mockito.doReturn(new AudioJob()).when(convertAudioFileInteractor).createAudioJob(any());
            Mockito.doThrow(new IllegalArgumentException()).when(mockFFmpegService).convertAudio(any());
            convertAudioFileInteractor.execute(convertAudioFileData);
            Mockito.verify(mockConvertAudioFileOutputBoundary).prepareFailView("Error occurred when processing: invalid argument");
        }
    }


    @Test
    void executeWithOtherExceptionShouldPrepareFailView() {
        try (MockedStatic<Validator> mocked = mockStatic(Validator.class)) {
//            Mockito.doReturn(new AudioJob()).when(convertAudioFileInteractor).createAudioJob(any());
            Mockito.doThrow(new RuntimeException()).when(mockFFmpegService).convertAudio(any());
            convertAudioFileInteractor.execute(convertAudioFileData);
            Mockito.verify(mockConvertAudioFileOutputBoundary).prepareFailView("Unexpected error occurred.");
        }

    }
}
