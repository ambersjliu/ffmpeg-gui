package data_access;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FFmpegServiceTest {

    FFmpegService ffmpegService;

    @BeforeEach
    void setup(){
        ffmpegService = new FFmpegService();
    }


    // Mock out the constructor for FFMpeg so new FFMpeg(xx,yy) will not throw
    // https://rieckpil.de/mock-java-constructors-and-their-object-creation-with-mockito/
    @Test
    void mockFFMpegConstructorForFFmpegServiceInit() {
        try (
                MockedConstruction<FFmpeg> mockFFmpeg = Mockito.mockConstruction(FFmpeg.class,
                        (mock, context) -> {
                        });
        ) {
            assertDoesNotThrow(() -> ffmpegService.initialize("Doesnt exist", "Doesnt exist"));
            assertNotNull(ffmpegService.getFfmpeg());
            assertNotNull(ffmpegService.getFfprobe());
        }
    }

    @Test
    void mockFFMpegConstructorForFFmpegServiceValidateBinary() throws IOException{
        try (
                MockedConstruction<FFmpeg> mockFFmpeg = Mockito.mockConstruction(FFmpeg.class,
                        (mock, context) -> {
                            Mockito.when(mock.isFFmpeg()).thenReturn(true);
                        });
                MockedConstruction<FFprobe> mockFFprobe = Mockito.mockConstruction(FFprobe.class,
                        (mock, context) -> {
                            Mockito.when(mock.isFFprobe()).thenReturn(true);
                        });
        ) {
            ffmpegService.initialize("Doesnt exist", "Doesnt exist");
            assertDoesNotThrow(() -> ffmpegService.validateBinaries());
        }
    }

}
