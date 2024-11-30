package use_case.convert_video;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Output Data for convert video use case.
 */

@Getter
@AllArgsConstructor
public class ConvertVideoFileOutputData {
    private final boolean successfulSave;
    private final String successMessage;

}
