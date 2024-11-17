package use_case.add_input_file;


public interface AddInputFileOutputBoundary {
    /**
     * Prepares the success view for the Add Input File Use Case, only if the file has a video stream.
     * @param outputData the output data
     */
    void prepareVideoSuccessView(AddInputFileOutputVideoData outputData);

    /**
     * Prepares the success view for the Add Input File Use Case, only if the file has only audio streams.
     * @param outputData the output data
     */
    void prepareAudioSuccessView(AddInputFileOutputAudioData outputData);

    /**
     * Prepares the failure view for the Add Input File Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
