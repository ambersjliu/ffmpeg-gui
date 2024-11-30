package data_access;

import entity.AudioJob;
import entity.VideoJob;

/**
 * Interface to convert both video and audio.
 */

public interface ConvertInterface {
    /**
     * Convert video.
     * @param job video job that contains video attributes.
     */
    void convertVideo(VideoJob job);

    /**
     * Convert audio.
     * @param job audio job that contains audio attributes.
     */
    void convertAudio(AudioJob job);
}
