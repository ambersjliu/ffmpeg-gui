package data_access;

import entity.AudioJob;
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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Getter
@Setter
public class FFmpegService implements GetMediaInfoInterface, ConvertInterface{
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;


    public void initialize(String ffmpegPath, String ffprobePath) throws IOException {
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
    }

    public void validateBinaries() throws IOException, InvalidExecutableException {
        if (!ffmpeg.isFFmpeg() || !ffprobe.isFFprobe()) {
            throw new InvalidExecutableException();
        }
    }

    @Override
    public FFmpegProbeResult probe(String filePath) throws IOException{
        return this.ffprobe.probe(filePath);
    }

    @Override
    public void convertVideo(VideoJob job) {

        String input = job.getInputFileName();
        String output = job.getOutputFileName();
        String format = job.getOutputFormat();

        long startTime = (long) job.getStartTime();
        long duration = (long) job.getDuration();

        int audioChannels = job.getAudioAttributes().getChannels();
        String audioCodec = job.getAudioAttributes().getCodecName();
        int audioSampleRate = job.getAudioAttributes().getSampleRate();
        int audioBitRate = job.getAudioAttributes().getBitrate();

        String videoCodec = job.getVideoAttributes().getCodecName();
        long videoBitrate = job.getVideoAttributes().getBitrate();
        double frameRate = job.getVideoAttributes().getFps();
        int width = job.getVideoAttributes().getWidth();
        int height = job.getVideoAttributes().getHeight();

        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput(input)
                .overrideOutputFiles(true) // Override the output if it exists

                .addOutput(output)   // Filename for the destination
//                .setFormat("mp4")        // Format is inferred from filename, or can be set
//                .setTargetSize(250_000)  // Target size in KB

                .disableSubtitle()
                .setStartOffset(startTime, TimeUnit.SECONDS)// No subtiles
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

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        // Run a one-pass encode
        executor.createJob(builder).run();
    }

    @Override
    public void convertAudio(AudioJob job) {

    }

    // TODO: Create Entities to represent the information about a file as well as user input
    // TODO: Write method using ffprobe to return information about an input file
    // TODO: Encoding method

}
