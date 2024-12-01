package data_access;

import java.io.IOException;

import net.bramp.ffmpeg.probe.FFmpegProbeResult;

/**
 * Interface to retreive information of a file.
 */

public interface GetMediaInfoInterface {
    /**
     * Get attribuel of a file.
     * @param filePath path of the input file
     * @return information of the file
     * @throws IOException thrown when the file format isn't support ffmpeg
     */
    FFmpegProbeResult probe(String filePath) throws IOException;
}
