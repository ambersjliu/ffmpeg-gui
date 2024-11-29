package data_access.config;

/**
 * Extends the AbstractConfigLoader class.
 * Makes it easier to test initialization of an FFmpegService object
 * since you can pass paths directly instead of editing your application.properties file.
 */
public class DirectConfigLoader extends AbstractConfigLoader{
    private String ffmpegPath;
    private String ffprobePath;

    public DirectConfigLoader(String ffmpegPath, String ffprobePath) {
        this.ffmpegPath = ffmpegPath;
        this.ffprobePath = ffprobePath;
    }

    @Override
    public String getFfmpegPath() {
        return this.ffmpegPath;
    }

    @Override
    public String getFfprobePath() {
        return this.ffprobePath;
    }
}
