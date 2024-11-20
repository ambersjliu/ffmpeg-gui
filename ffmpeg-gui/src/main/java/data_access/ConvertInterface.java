package data_access;

import entity.AudioJob;
import entity.VideoJob;

public interface ConvertInterface {
    void convertVideo(VideoJob job);

    void convertAudio(AudioJob job);
}
