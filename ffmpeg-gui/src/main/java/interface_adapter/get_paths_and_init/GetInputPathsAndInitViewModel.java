package interface_adapter.get_paths_and_init;
import interface_adapter.ViewModel;

public class GetInputPathsAndInitViewModel extends ViewModel<GetInputPathsAndInitState> {
    public static final String TITLE_LABEL = "Welcome to the FFmpeg GUI";
    public static final String INTRUCTION_LABEL = "Install FFmpeg from here";
    public static final String HYPERLINK_LABEL = "[click]";

    public static final String FFMPEG_LABEL = "Find FFmpeg on your computer";
    public static final String FFPROBE_LABEL = "Find FFprobe on your computer";
    public static final String BROWSE_TEXT = "Browse";

    public static final String NEXT_TEXT = "Next";

    public GetInputPathsAndInitViewModel() {
        super("Getter view");
        setState(new GetInputPathsAndInitState());
    }
}
