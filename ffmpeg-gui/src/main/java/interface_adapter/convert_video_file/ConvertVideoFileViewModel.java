package interface_adapter.convert_video_file;

import constant.AudioCodec;
import constant.VideoCodec;
import constant.VideoFormat;
import interface_adapter.ViewModel;

import java.io.IOException;

public class ConvertVideoFileViewModel extends ViewModel<ConvertVideoFileState> {
    public static final String TITLE_LABEL = "Video Conversion";

    public static final String FILE_CHANGE_LABEL = "Change File";

    public static final String START_ENCODE_LABEL = "Start Encode";

    public static final String OUTPUT_FORMAT_LABEL = "Output Format";
    public static final String[] FILE_FORMAT = VideoFormat.VIDEO_FORMATS;

    public static final String START_TIME_LABEL = "Start Time";
    public static final String END_TIME_LABEL = "End Time";

    public static final String DIMENSION_LABEL = "Dimension";

    public static final String FRAME_RATE_LABEL = "Frame Rate";

    public static final String VIDEO_BITRATE_LABEL = "Video Bitrate";

    public static final String VIDEO_CODEC_LABEL = "Video codec";
    public static final String[] VIDEO_CODEC = VideoCodec.VIDEO_CODEC;

    public static final String AUDIO_CODEC_LABEL = "Audio Codec";
    public static final String[] AUDIO_CODEC = AudioCodec.AUDIO_CODEC;

    public static final String AUDIO_BITRATE_LABEL = "Audio Bitrate";

    public static final String CHANNEL_LABEL = "Channels";

    public static final String SAMPLE_RATE_LABEL = "Sample Rate";

    public static final String SAVE_AS_DESTINATION_LABEL = "Save as:";
    public static final String BROWSE_LABEL = "Browse";

    public static final String PROGRESS_MESSAGE = "Converting, please wait...";

    public ConvertVideoFileViewModel(){
        super("convert video file");
        setState(new ConvertVideoFileState());
    }
}
