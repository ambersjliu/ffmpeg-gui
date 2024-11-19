package data_access;

import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.IOException;

public interface GetMediaInfoInterface {
    FFmpegProbeResult probe(String filePath) throws IOException;
}
