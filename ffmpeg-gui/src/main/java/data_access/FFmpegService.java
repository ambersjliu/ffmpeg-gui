package data_access;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import entity.AbstractJob;
import entity.AudioJob;
import entity.CropVideoJob;
import entity.VideoJob;
import exceptions.InvalidExecutableException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

/**
 * A class that exposes access to FFmpeg's file probing
 * and conversion features.
 * Note: The class name violates convention but is in accordance
 * with the FFmpeg LGPL.
 * <a href="https://www.ffmpeg.org/legal.html">...</a>
 */
@NoArgsConstructor
@Getter
@Setter
public class FFmpegService implements GetMediaInfoInterface, ConvertInterface {
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;

    /**
     * Initializes FFmpeg and FFprobe class attributes with the given paths.
     * @param ffmpegPath the path to the FFmpeg binary
     * @param ffprobePath the path to the FFprobe binary
     * @throws IOException if the initialization fails
     */
    public void initialize(String ffmpegPath, String ffprobePath) throws IOException {
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
    }

    /**
     * Checks that the class attributes are actually ffmpeg and ffprobe binaries.
     * @throws IOException if the binary file cannot be read
     * @throws InvalidExecutableException if the binary file cannot be validated
     */
    public void validateBinaries() throws IOException, InvalidExecutableException {
        if (!ffmpeg.isFFmpeg() || !ffprobe.isFFprobe()) {
            throw new InvalidExecutableException();
        }
    }

    @Override
    public FFmpegProbeResult probe(String filePath) throws IOException {
        return this.ffprobe.probe(filePath);
    }

    @Override
    public void convertVideo(VideoJob job) {
        final String input = job.getInputFileName();
        final String output = job.getOutputFileName();
        final String format = job.getOutputFormat();

        final long startTime = (long) job.getStartTime();
        final long duration = (long) job.getDuration();

        final int audioChannels = job.getAudioAttributes().getChannels();
        final String audioCodec = job.getAudioAttributes().getCodecName();
        final int audioSampleRate = (int) job.getAudioAttributes().getSampleRate();
        final long audioBitRate = job.getAudioAttributes().getBitrate();

        final String videoCodec = job.getVideoAttributes().getCodecName();
        final long videoBitrate = job.getVideoAttributes().getBitrate();
        final double frameRate = job.getVideoAttributes().getFps();
        final int width = job.getVideoAttributes().getWidth();
        final int height = job.getVideoAttributes().getHeight();

        final FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(input)
                .overrideOutputFiles(true)

                .addOutput(output)

                .disableSubtitle()
                .setStartOffset(startTime, TimeUnit.SECONDS)
                .setDuration(duration, TimeUnit.SECONDS)
                .setFormat(format)

                .setAudioChannels(audioChannels)
                .setAudioCodec(audioCodec)
                .setAudioSampleRate(audioSampleRate)
                .setAudioBitRate(audioBitRate)

                .setVideoCodec(videoCodec)
                .setVideoFrameRate(frameRate)
                .setVideoBitRate(videoBitrate)
                .setVideoHeight(height)
                .setVideoWidth(width)

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        final FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    @Override
    public void convertAudio(AudioJob job) {
        final String input = job.getInputFileName();
        final String output = job.getOutputFileName();
        final String format = job.getOutputFormat();

        final long startTime = (long) job.getStartTime();
        final long duration = (long) job.getDuration();

        final int audioChannels = job.getAudioAttributes().getChannels();
        final String audioCodec = job.getAudioAttributes().getCodecName();
        final int audioSampleRate = (int) job.getAudioAttributes().getSampleRate();
        final long audioBitRate = job.getAudioAttributes().getBitrate();

        final FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(input)
                .overrideOutputFiles(true)

                .addOutput(output)

                .disableSubtitle()
                .setStartOffset(startTime, TimeUnit.SECONDS)
                .setDuration(duration, TimeUnit.SECONDS)
                .setFormat(format)

                .setAudioChannels(audioChannels)
                .setAudioCodec(audioCodec)
                .setAudioSampleRate(audioSampleRate)
                .setAudioBitRate(audioBitRate)

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        final FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    /**
     * Convert from video to a GIF.
     * @param job the query to be processed
     */
    public void convertVideoToGif(VideoJob job) {
        final String input = job.getInputFileName();
        final String output = job.getOutputFileName();
        final String format = job.getOutputFormat();

        final long startTime = (long) job.getStartTime();
        final long duration = (long) job.getDuration();

        final double frameRate = job.getVideoAttributes().getFps();
        final int width = job.getVideoAttributes().getWidth();

        final FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(input)
                .overrideOutputFiles(true)

                .addOutput(output)

                .disableSubtitle()
                .setStartOffset(startTime, TimeUnit.SECONDS)
                .setDuration(duration, TimeUnit.SECONDS)
                .setFormat(format)

                .setVideoFrameRate(frameRate)
                .setVideoFilter(String.format(
                        "[0:v] fps=%s,scale=%s:-1,split [a][b];[a] palettegen [p];[b][p] paletteuse",
                        frameRate, width))

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        final FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    /**
     * Converting and cropping a CropVideoJob.
     * @param job a cropped video job
     */
    public void convertCropVideo(VideoJob job) {

        final String input = job.getInputFileName();
        final String output = job.getOutputFileName();
        final String format = job.getOutputFormat();

        final long startTime = (long) job.getStartTime();
        final long duration = (long) job.getDuration();

        final int audioChannels = job.getAudioAttributes().getChannels();
        final String audioCodec = job.getAudioAttributes().getCodecName();
        final int audioSampleRate = (int) job.getAudioAttributes().getSampleRate();
        final long audioBitRate = job.getAudioAttributes().getBitrate();

        final String videoCodec = job.getVideoAttributes().getCodecName();
        final long videoBitrate = job.getVideoAttributes().getBitrate();
        final double frameRate = job.getVideoAttributes().getFps();
        final int width = job.getVideoAttributes().getWidth();
        final int height = job.getVideoAttributes().getHeight();

        final FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(input)
                .overrideOutputFiles(true)

                .addOutput(output)

                .disableSubtitle()
                .setStartOffset(startTime, TimeUnit.SECONDS)
                .setDuration(duration, TimeUnit.SECONDS)
                .setFormat(format)

                .setAudioChannels(audioChannels)
                .setAudioCodec(audioCodec)
                .setAudioSampleRate(audioSampleRate)
                .setAudioBitRate(audioBitRate)

                .setVideoCodec(videoCodec)
                .setVideoFrameRate(frameRate)
                .setVideoBitRate(videoBitrate)

                .setVideoFilter(String.format("crop=%s:%s:0:0", width, height))

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
                .done();

        final FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    /**
     * Converting, and calling the correct method from above.
     * @param job an abstract job
     * @throws IllegalArgumentException if an unsupported job is given.
     */
    public void convert(AbstractJob job) {
        if (job instanceof CropVideoJob) {
            convertCropVideo((CropVideoJob) job);
        }
        else if (job instanceof VideoJob) {
            convertVideo((VideoJob) job);
        }
        else if (job instanceof AudioJob) {
            convertAudio((AudioJob) job);
        }
        else {
            throw new IllegalArgumentException("Unsupported job type: " + job.getClass().getName());
        }
    }

}
