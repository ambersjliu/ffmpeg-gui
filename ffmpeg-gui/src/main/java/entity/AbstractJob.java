package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * An Abstract Class defining jobs for file.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class AbstractJob {
    private String inputFileName;
    private String outputFileName;
    private String outputFormat;
    private double duration;
    private double startTime;
}
