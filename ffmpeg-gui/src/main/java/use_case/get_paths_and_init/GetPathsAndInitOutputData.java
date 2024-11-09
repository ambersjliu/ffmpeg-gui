package use_case.get_paths_and_init;

import data_access.FFmpegService;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class GetPathsAndInitOutputData {
    private FFmpegService ffmpegService;
    private final boolean useCaseFailed;
}
