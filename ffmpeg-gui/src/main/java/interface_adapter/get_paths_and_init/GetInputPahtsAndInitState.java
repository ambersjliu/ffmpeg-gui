package interface_adapter.get_paths_and_init;

public class GetInputPahtsAndInitState {
    private String ffmpegPath;
    private String ffprobePath;
    private String pathError;

    public String getFfmpegPath() {
        return ffmpegPath;
    }


    public String getPathError() {
        return pathError;
    }

    public String getFfprobePath() {
        return ffprobePath;
    }


    public void setPathError(String pathError) {
        this.pathError = pathError;
    }

    public void setFfprobePath(String ffprobePath) {
        this.ffprobePath = ffprobePath;
    }

    public void setFfmpegPath(String ffmpegPath) {
        this.ffmpegPath = ffmpegPath;
    }
}
