package app;

import data_access.FFmpegService;
import data_access.config.AbstractConfigLoader;
import data_access.config.PropertiesConfigLoader;

import java.io.IOException;

public class AppBuilder {
    private final AbstractConfigLoader configLoader;
    private final FFmpegService ffmpegService;

    public AppBuilder() throws IOException {
        configLoader = new PropertiesConfigLoader();
        String ffmpegPath = configLoader.getFFmpegPath();
        String ffprobePath = configLoader.getFFprobePath();
        ffmpegService = new FFmpegService(ffmpegPath, ffprobePath);
    }
}
