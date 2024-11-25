package use_case.convert_audio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConvertAudioFileOutputData {
    private final boolean successfulSave;
    private final String successMessage;
}
