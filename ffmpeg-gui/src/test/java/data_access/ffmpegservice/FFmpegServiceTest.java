package data_access.ffmpegservice;

import data_access.FFmpegService;
import data_access.config.AbstractConfigLoader;
import data_access.config.DirectConfigLoader;
import exceptions.InvalidExecutableException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FFmpegServiceTest {


    @Test
    public void testInitializeCorrectPath() throws IOException, InvalidExecutableException {
        String ffmpegPath = "C:/PATH_programs/ffmpeg.exe";
        String ffprobePath = "C:/PATH_programs/ffprobe.exe";
        AbstractConfigLoader loader = new DirectConfigLoader(ffmpegPath, ffprobePath);
        FFmpegService ffmpegService = new FFmpegService(loader.getFFmpegPath(), loader.getFFprobePath());

        Assert.assertNotNull(ffmpegService);
        Assert.assertNotNull(ffmpegService.getFfmpeg());
        Assert.assertNotNull(ffmpegService.getFfprobe());
        Assert.assertTrue(ffmpegService.getFfmpeg().isFFmpeg());
        Assert.assertTrue(ffmpegService.getFfprobe().isFFprobe());
    }

}
