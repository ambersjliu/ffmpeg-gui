package data_access.config;

/**
 * Loads the locations of ffmpeg.exe and ffprobe.exe
 */
public abstract class AbstractConfigLoader {
    public abstract String getFFmpegPath();
    public abstract String getFFprobePath();
}
