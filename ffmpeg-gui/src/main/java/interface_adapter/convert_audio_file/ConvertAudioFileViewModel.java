package interface_adapter.convert_audio_file;

import constant.AudioCodec;
import constant.AudioFormat;
import interface_adapter.ViewModel;

/**
 * View model for convert audio file use case.
 */

public class ConvertAudioFileViewModel extends ViewModel<ConvertAudioFileState> {
    public static final String TITLE_LABEL = "Audio Conversion";

    public static final String FILE_CHANGE_LABEL = "Change File";

    public static final String START_ENCODE_LABEL = "Start Encode";

    public static final String OUTPUT_FORMAT_LABEL = "Output Format";
    public static final String[] FILE_FORMAT = AudioFormat.AUDIO_FORMATS;

    public static final String START_TIME_LABEL = "Start Time";
    public static final String END_TIME_LABEL = "End Time";

    public static final String AUDIO_CODEC_LABEL = "Audio Codec";
    public static final String[] AUDIO_CODEC = AudioCodec.AUDIO_CODEC;

    public static final String AUDIO_BITRATE_LABEL = "Audio Bitrate";

    public static final String CHANNEL_LABEL = "Channels";

    public static final String SAMPLE_RATE_LABEL = "Sample Rate";

    public static final String SAVE_AS_DESTINATION_LABEL = "Save as:";
    public static final String BROWSE_LABEL = "Browse";

    public static final String WARNING_LABEL = "Please fill all fields.";

    public static final int COLUMN_OF_TEXTFIELD_SHORT = 4;
    public static final int COLUMN_OF_TEXTFIELD_LONG = 4;

    public static final int SPINNER_WIDTH = 75;
    public static final int SPINNER_HEIGHT = 20;

    public static final String COLON = ":";

    public ConvertAudioFileViewModel() {
        super("convert audio file");
        setState(new ConvertAudioFileState());
    }
}
