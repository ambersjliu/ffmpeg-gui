package use_case.convert_audio;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Output data of the interactor.
 */

@Getter
@AllArgsConstructor
public class ConvertAudioFileOutputData {
    private final boolean successfulSave;
    private final String successMessage;
}
