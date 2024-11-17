package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public abstract class AbstractJob {
    private String inputFileName;
    private String outputFileName;
    private double duration;
    private double startTime;
}
