package use_case.get_paths_and_init;

/**
 * Interface define the interactor for get ffmpeg file use case.
 */

public interface GetPathsAndInitInputBoundary {
    /**
     * Execute logic in interactor.
     * @param input Data for the use case
     */
    void execute(GetPathsAndInitData input);
}
