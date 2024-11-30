package use_case.get_paths_and_init;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data of the get ffmpeg path.
 */

@Getter
@AllArgsConstructor
public class GetPathsAndInitData {
    private final String ffmpegPath;
    private final String ffprobePath;
}
