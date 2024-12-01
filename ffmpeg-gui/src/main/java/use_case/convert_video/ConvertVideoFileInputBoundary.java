package use_case.convert_video;

/**
 * Interface defines interactor for convert video use case.
 */

public interface ConvertVideoFileInputBoundary {
    /**
     * Execute logic for convert video use case.
     * @param videoFileData input file data
     */
    void execute(ConvertVideoFileData videoFileData);

}
