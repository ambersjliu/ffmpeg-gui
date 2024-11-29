package data_access.config;

/**
 * Loads the locations of ffmpeg.exe and ffprobe.exe.
 */
public abstract class AbstractConfigLoader {
    /**
     * Obtain the path to FFmpeg.exe.
     * @return String of the required path
     */
    public abstract String getFfmpegPath();

    /**
     * Obtain the path to FFprobe.exe.
     * @return String of the required path
     */
    public abstract String getFfprobePath();
}
