package data_access;

import entity.AbstractJob;

/**
 * Interface to convert both video and audio.
 */

public interface ConvertInterface {
    /**
     * Builds and executes the given job.
     * @param job the job to be executed
     */
    void convert(AbstractJob job);
}
