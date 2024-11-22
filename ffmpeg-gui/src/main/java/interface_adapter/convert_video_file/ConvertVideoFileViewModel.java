package interface_adapter.convert_video_file;

import interface_adapter.ViewModel;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.builder.*;
import net.bramp.ffmpeg.info.Codec;

import java.io.IOException;
import java.util.List;

public class ConvertVideoFileViewModel extends ViewModel<ConvertVideoFileState> {
    public static final String TITLE_LABEL = "Video Conversion";

    public static final String FILE_CHANGE_LABEL = "Change File";

    public static final String START_ENCODE_LABEL = "Start Encode";

    public static final String OUTPUT_FORMAT_LABEL = "Output Format";
    public static final String[] FILE_FORMAT = {"mp4", "mov", "avi", "webm", "mpeg", "m4v"};

    public static final String START_TIME_LABEL = "Start Time";
    public static final String END_TIME_LABEL = "End Time";

    public static final String DIMENSION_LABEL = "Dimension";

    public static final String FRAME_RATE_LABEL = "Frame Rate";

    public static final String VIDEO_CODEC_LABEL = "Video codec";
    public static final String[] VIDEO_CODEC = {"av1", "h264", "hevc", "mpeg1video", "mpeg2video", "mpeg4", "vp9"};

    public static final String AUDIO_CODEC_LABEL = "Audio Codec";
    public static final String[] AUDIO_CODEC = {"flac", "alac", "wavpack", "tta", "wmalossless", "tak", "aac", "mp4als", "mp3"};

    public static final String BITRATE_LABEL = "Bitrate";

    public static final String CHANNEL_LABEL = "Channels";

    public static final String SAMPLE_RATE_LABEL = "Sample Rate";

    public static final String SAVE_AS_DESTINATION_LABEL = "Save as:";
    public static final String BROWSE_LABEL = "Browse";

    public ConvertVideoFileViewModel() throws IOException {
        super("convert video file");
        setState(new ConvertVideoFileState());
    }
}
