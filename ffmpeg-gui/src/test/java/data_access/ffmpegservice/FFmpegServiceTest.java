package data_access.ffmpegservice;

import data_access.FFmpegService;
import data_access.config.AbstractConfigLoader;
import data_access.config.DirectConfigLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FFmpegServiceTest {
    @Test
    public void testInitializeCorrectPath() throws IOException {
        String ffmpegPath = "C:/your/path/here";
        String ffprobePath = "C:/your/path/here";
        AbstractConfigLoader loader = new DirectConfigLoader(ffmpegPath, ffprobePath);
        FFmpegService ffmpegService = new FFmpegService(loader.getFFmpegPath(), loader.getFFprobePath());

        Assert.assertNotNull(ffmpegService);
        Assert.assertNotNull(ffmpegService.getFfmpeg());
        Assert.assertNotNull(ffmpegService.getFfprobe());
    }


}
