package use_case.get_paths_and_init;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Output dAta for the interactor of the get ffmpeg path use case.
 */

@Getter
@AllArgsConstructor
public class GetPathsAndInitOutputData {
    private final boolean useCaseFailed;
}
